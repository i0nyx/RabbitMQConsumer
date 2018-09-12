package by.intexsoft.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@AllArgsConstructor
public class RabbitMqListener implements MessageListener {

    private CallService callService;

    @Override
    public void onMessage(Message message) {
        callService.save(message);
    }
}
