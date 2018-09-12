package by.intexsoft.service.impl;

import by.intexsoft.pojo.Call;
import by.intexsoft.repositories.CallRepository;
import by.intexsoft.service.CallService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CallServiceImpl implements CallService {

    private CallRepository callRepository;

    @Override
    public void save(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Call call = objectMapper.readValue(message.getBody(), Call.class);
            callRepository.insert(call);
            log.info("save CALL success");
        } catch (IOException e) {
            log.error("json error " + e);
        }
    }
}
