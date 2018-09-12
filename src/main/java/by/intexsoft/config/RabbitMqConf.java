package by.intexsoft.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import static by.intexsoft.constant.QueueConst.QUEUE_ONE;
import static by.intexsoft.constant.QueueConst.QUEUE_TWO;

@Configuration
@ComponentScan("by.intexsoft")
@PropertySource("classpath:rabbitmq.properties")
@AllArgsConstructor
@EnableRabbit
public class RabbitMqConf {

    private Environment env;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(env.getProperty("rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("rabbitmq.password"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(@Autowired CachingConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(@Autowired CachingConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
