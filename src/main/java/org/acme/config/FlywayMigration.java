package org.acme.config;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

/**
 * The Flyway Migration workaround due to the incompatible of Hibernate Reactive and Flyway
 *
 * @see
 * <a href="https://github.com/quarkusio/quarkus/issues/10716">https://github.com/quarkusio/quarkus/issues/10716</a>
 */
@ApplicationScoped
public class FlywayMigration {
	@ConfigProperty(name = "quarkus.flyway.migrate-at-start")
	boolean migrateAtStart;

	@ConfigProperty(name = "quarkus.flyway.clean-at-start")
	boolean cleanAtStart;
	@ConfigProperty(name = "quarkus.datasource.reactive.url")
	String datasourceUrl;
	@ConfigProperty(name = "quarkus.datasource.username")
	String datasourceUsername;
	@ConfigProperty(name = "quarkus.datasource.password")
	String datasourcePassword;

	@ConfigProperty(name = "quarkus.flyway.schemas")
	String schemas;

	@ConfigProperty(name = "quarkus.flyway.out-of-order")
	boolean outOfOrder;

	public void runFlywayMigration(@Observes StartupEvent event) {
		if (migrateAtStart) {
			Flyway flyway = Flyway.configure()
				.dataSource(datasourceUrl.replaceFirst("(vertx-reactive:)*", "jdbc:"), datasourceUsername, datasourcePassword)
				.cleanDisabled(false)
				.outOfOrder(outOfOrder)
				.validateOnMigrate(false)
				.schemas(schemas)
				.load();
			if (cleanAtStart) {
				flyway.clean();
			}
			flyway.migrate();
		}
	}
}
