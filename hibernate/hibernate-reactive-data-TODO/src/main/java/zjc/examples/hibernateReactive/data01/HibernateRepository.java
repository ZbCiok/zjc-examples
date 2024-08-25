package zjc.examples.hibernateReactive.data01;

import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;
import org.hibernate.reactive.mutiny.Mutiny;
import reactor.core.publisher.Mono;

public class HibernateRepository {
    private final Mutiny.SessionFactory sessionFactory = (Mutiny.SessionFactory) Persistence.createEntityManagerFactory("postgresql").unwrap(Mutiny.SessionFactory.class);

    @NotNull
    public final Mono findAll() {
        Object var10000 = this.sessionFactory.withSession((Function)null.INSTANCE).convert().with((Function)UniReactorConverters.toMono());
        Intrinsics.checkNotNullExpressionValue(var10000, "sessionFactory.withSessi…actorConverters.toMono())");
        return (Mono)var10000;
    }
}
