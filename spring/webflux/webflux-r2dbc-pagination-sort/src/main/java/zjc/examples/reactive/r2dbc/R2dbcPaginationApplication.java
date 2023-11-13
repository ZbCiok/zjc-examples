package zjc.examples.reactive.r2dbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjc.examples.reactive.r2dbc.entity.Product;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class R2dbcPaginationApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(R2dbcPaginationApplication.class, args);
	}


	// setup postgresql, table product

	@Value("classpath:init.sql")
	private Resource initSql;

	@Autowired
	private R2dbcEntityTemplate entityTemplate;

	@Override
	public void run(String... args) throws Exception {
		String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
		this.entityTemplate
				.getDatabaseClient()
				.sql(query)
				.then()
				.then(insertProducts())
				.subscribe();
	}

	private Mono<Void> insertProducts(){
		return Flux.range(1, 100)
				.map(i -> Product.create(null, "product - " + i, ThreadLocalRandom.current().nextInt(1, 500)))
				.flatMap(this.entityTemplate::insert)
				.doOnComplete(() -> System.out.println("Inserted all records"))
				.then();
	}
}
