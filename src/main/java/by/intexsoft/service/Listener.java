package by.intexsoft.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static by.intexsoft.constant.QueueConst.QUEUE_ONE;
import static by.intexsoft.constant.QueueConst.QUEUE_TWO;

/**
 * A class that implements methods that listen for RabbitMQ queues
 */
@Component
@Slf4j
@AllArgsConstructor
public class Listener {
    private CallService callService;

    /**
     * Method listen RabbitMQ queue and send {@link Message} in log
     *
     * @param message Data received from the queue
     */
    @RabbitListener(queues = QUEUE_ONE)
    public void messageListenerOne(Message message) {
        log.info("listen queue one " + new String(message.getBody()));
    }

    /**
     * Method listen RabbitMQ queue and send {@link Message} in log
     * and in {@link CallService}
     *
     * @param message Data received from the queue
     */
    @RabbitListener(queues = QUEUE_TWO)
    public void messageListenerTwo(Message message) {
        log.info("listen queue two " + new String(message.getBody()));
        callService.save(message);
    }
}
