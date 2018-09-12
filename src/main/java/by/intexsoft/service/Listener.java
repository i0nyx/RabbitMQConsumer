package by.intexsoft.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static by.intexsoft.constant.QueueConst.QUEUE_ONE;
import static by.intexsoft.constant.QueueConst.QUEUE_TWO;

@Component
@Slf4j
@AllArgsConstructor
public class Listener {

    private CallService callService;

    @RabbitListener(queues = QUEUE_ONE)
    public void messageListenerOne(Message message) {
        log.info("listen queue one " + new String(message.getBody()));
    }

    @RabbitListener(queues = QUEUE_TWO)
    public void messageListenerTwo(Message message) {
        log.info("listen queue two " + new String(message.getBody()));
        callService.save(message);
    }
}
