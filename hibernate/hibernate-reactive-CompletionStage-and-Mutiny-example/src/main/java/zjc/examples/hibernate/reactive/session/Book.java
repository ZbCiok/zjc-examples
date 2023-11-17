/* Hibernate, Relational Persistence for Idiomatic Java
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright: Red Hat Inc. and Hibernate Authors
 */
package zjc.examples.hibernate.reactive.session;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Subselect("select author_id, isbn, published, title, id from books")
@SQLInsert(sql = "insert into books (author_id, isbn, published, title, id) values ($1, $2, $3, $4, $5)")
@SQLUpdate(sql = "")
@SQLDelete(sql = "delete from books where id=$1")
@Table(name="books")
public class Book {
	@Id @GeneratedValue
	private Integer id;

	@Size(min=13, max=13)
	private String isbn;

	@NotNull @Size(max=100)
	private String title;

	@Basic(fetch = LAZY)
	@NotNull @Past
	private LocalDate published;

	@NotNull
	@ManyToOne(fetch = LAZY)
	private Author author;

	public Book() {}

	public Book(String isbn, String title, Author author, LocalDate published) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.published = published;
	}

	public Integer getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public LocalDate getPublished() {
		return published;
	}
}
