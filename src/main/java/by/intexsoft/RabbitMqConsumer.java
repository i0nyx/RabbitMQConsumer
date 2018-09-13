package by.intexsoft;

import by.intexsoft.config.RabbitMqConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RabbitMqConsumer {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RabbitMqConfig.class);
    }
}
