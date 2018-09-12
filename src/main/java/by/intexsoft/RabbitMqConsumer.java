package by.intexsoft;

import by.intexsoft.config.RabbitMqConf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class RabbitMqConsumer {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RabbitMqConf.class);
    }
}
