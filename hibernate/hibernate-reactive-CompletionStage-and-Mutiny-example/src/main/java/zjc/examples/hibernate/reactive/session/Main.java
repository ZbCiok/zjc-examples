/* Hibernate, Relational Persistence for Idiomatic Java
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright: Red Hat Inc. and Hibernate Authors
 */
package zjc.examples.hibernate.reactive.session;

import jakarta.persistence.Tuple;

import java.time.LocalDate;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static java.lang.System.out;
import static java.time.Month.*;
import static org.hibernate.reactive.stage.Stage.SessionFactory;

/**
 * Demonstrates the use of Hibernate Reactive with the
 * {@link java.util.concurrent.CompletionStage}-based
 * API.
 *
 * Here we use stateless sessions and handwritten SQL.
 */
public class Main {

	// The first argument can be used to select a persistence unit.
	// Check resources/META-INF/persistence.xml for available names.
	public static void main(String[] args) {
		out.println( "== CompletionStage API Example ==" );

		// obtain a factory for reactive sessions based on the
		// standard JPA configuration properties specified in
		// resources/META-INF/persistence.xml
		SessionFactory factory =
				createEntityManagerFactory( "postgresql-example" )
						.unwrap(SessionFactory.class);

		// define some test data
		Author author1 = new Author("Iain M. Banks");
		Author author2 = new Author("Neal Stephenson");
		Book book1 = new Book("1-85723-235-6", "Feersum Endjinn", author1, LocalDate.of(1994, JANUARY, 1));
		Book book2 = new Book("0-380-97346-4", "Cryptonomicon", author2, LocalDate.of(1999, MAY, 1));
		Book book3 = new Book("0-553-08853-X", "Snow Crash", author2, LocalDate.of(1992, JUNE, 1));
		author1.getBooks().add(book1);
		author2.getBooks().add(book2);
		author2.getBooks().add(book3);

		try {
			out.println( "==================== insert( author1, author2, book1, book2, book3 ) ==");
			factory.withStatelessSession(
							// persist the Authors with their Books in a transaction
							session -> session.withTransaction(
									tx -> session.insert( author1, author2, book1, book2, book3 )
							)
					)
					// wait for it to finish
					.toCompletableFuture().join();

			out.println( "==================== session.get( Book.class, book1.getId() )");
			factory.withStatelessSession(
							// retrieve a Book
							session -> session.get( Book.class, book1.getId() )
									// print its title
									.thenAccept( book -> out.println( book.getTitle() + " ----- is a great book!" ) )
					)
					.toCompletableFuture().join();

			out.println( "==================== session.get( Author.class, author2.getId() )");
			factory.withStatelessSession(
							// retrieve an Author
							session -> session.get( Author.class, author2.getId() )
									// lazily fetch their books
									.thenCompose( author -> session.fetch( author.getBooks() )
											// print some info
											.thenAccept( books -> {
												out.println("----- " + author.getName() + " wrote " + books.size() + " books" );
												books.forEach( book -> out.println( book.getTitle() ) );
											} )
									)
					)
					.toCompletableFuture().join();

			out.println( "==================== session.get( Book.class, book1.getId() )");
			factory.withStatelessSession(
							// retrieve the Author lazily from a Book
							session -> session.get( Book.class, book1.getId() )
									// fetch a lazy field of the Book
									.thenCompose( book -> session.fetch( book.getAuthor() )
											// print the lazy field
											.thenAccept( author -> out.printf("----- " + "%s wrote '%s'\n", author.getName(), book1.getTitle() ) )
									)
					)
					.toCompletableFuture().join();

			out.println( "==================== session.createNativeQuery( select * from books order by title desc, Book.class )");
			factory.withStatelessSession(
							// query the entire Book entities
							session -> session.createNativeQuery("select * from books order by title desc", Book.class )
									.getResultList()
									.thenAccept( books -> books.forEach(
											b -> out.printf("----- " + "%s: %s\n", b.getIsbn(), b.getTitle() )
									) )
					)
					.toCompletableFuture().join();

			out.println( "==================== session.createNativeQuery( select book.title, author.name from books book join authors author on book.author_id = author.id order by book.title desc )");
			factory.withStatelessSession(
							// query the Book titles
							session -> session.createNativeQuery(
											"select book.title, author.name from books book join authors author on book.author_id = author.id order by book.title desc",
											Tuple.class
									)
									.getResultList()
									.thenAccept( tuples -> tuples.forEach(
											tuple -> out.printf( "----- " + "%s (%s)\n", tuple.get( 0 ), tuple.get( 1 ) )
									) )
					)
					.toCompletableFuture().join();

			out.println( "==================== session.delete( book2 )");
			factory.withStatelessSession(
							session -> session.withTransaction(
									// delete a detached Book
									tx -> session.delete( book2 )
							)
					)
					.toCompletableFuture().join();

			out.println( "==================== delete all the Books; delete all the Authors" );
			factory.withStatelessSession(
							session -> session.withTransaction(
									// delete all the Books
									tx -> session.createNativeQuery( "delete from books" ).executeUpdate()
											//delete all the Authors
											.thenCompose( v -> session.createNativeQuery( "delete from authors" ).executeUpdate() )
							)
					)
					.toCompletableFuture().join();
		}
		finally {
			// remember to shut down the factory
			factory.close();
		}
	}
}
