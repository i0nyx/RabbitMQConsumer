package by.intexsoft.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import static by.intexsoft.constant.QueueConst.QUEUE_ONE;
import static by.intexsoft.constant.QueueConst.QUEUE_TWO;

/**
 * Class for configuration RabbitMQ using JavaConfig
 */
@Configuration
@ComponentScan("by.intexsoft")
@PropertySource("classpath:rabbitmq.properties")
@AllArgsConstructor
@EnableRabbit
public class RabbitMqConfig {

    private Environment env;

    /**
     * Create a {@link CachingConnectionFactory}.
     *
     * @return the {@link CachingConnectionFactory}
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(env.getProperty("rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("rabbitmq.password"));
        return connectionFactory;
    }

    /**
     * Create a {@link RabbitAdmin}.
     *
     * @return the {@link AmqpAdmin}
     * @see #connectionFactory()
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * Create a {@link Queue}.
     */
    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE);
    }

    /**
     * @return the {@link Queue}
     */
    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO);
    }

    /**
     * Create a {@link SimpleRabbitListenerContainerFactory}
     *
     * @see #connectionFactory()
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }
}
