package by.intexsoft.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class for Cassandra configuration using JavaConfig
 */
@Configuration
@PropertySource("classpath:cassandra.properties")
@ComponentScan("by.intexsoft")
@EnableCassandraRepositories(basePackages = {"by.intexsoft.repositories"})
@AllArgsConstructor
public class CassandraConfig extends AbstractCassandraConfiguration {
    private final Environment env;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKeyspaceName() {
        return env.getProperty("cassandra.keyspace");
    }

    /**
     * Create a {@link CassandraClusterFactoryBean}.
     */
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(env.getProperty("cassandra.host"));
        cluster.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("cassandra.port"))));
        return cluster;
    }

    /**
     * Create a {@link CassandraMappingContext}
     */
    @Bean
    public CassandraMappingContext cassandraMappingContext() {
        return new CassandraMappingContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getStartupScripts() {
        return Collections.singletonList("CREATE TABLE IF NOT EXISTS " + getKeyspaceName() + ".call(id UUID PRIMARY KEY, date timestamp, message text);");
    }

}
