package by.intexsoft.service;

import org.springframework.amqp.core.Message;

public interface CallService {

    void save(Message message);
}
