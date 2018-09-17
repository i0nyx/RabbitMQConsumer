import by.intexsoft.pojo.Call;
import by.intexsoft.repositories.CallRepository;
import by.intexsoft.service.CallService;
import by.intexsoft.service.impl.CallServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import java.util.Date;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * The class tests the methods of the class {@link CallService}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CallService.class, LoggerFactory.class})
public class CallServiceTest {
    private CallService callService;
    private CallRepository callRepository;

    /**
     * Install the stub {@link CallRepository} and create an object {@link CallService}
     */
    @Before
    public void setUp() {
        callRepository = mock(CallRepository.class);
        callService = new CallServiceImpl(callRepository);
    }

    /**
     * Check the insertion object {@link Call}in the database
     */
    @Test
    public void testSuccessSend() {
        String jsonMessage = "{\"id\":\"5a1ca3ad-dfb1-449d-878b-d073448895e3\",\"date\":1536847181302,\"message\":\"Test message\"}";
        Message message = mock(Message.class);
        when(message.getBody()).thenReturn(jsonMessage.getBytes());
        when(callRepository.insert((Call) any())).thenReturn(buildCall());
        callService.save(message);
        verify(callRepository).insert((Call) any());
    }

    /**
     * Check the call to the log method
     */
    @Test
    public void testFailSend() {
        Message message = mock(Message.class);
        mockStatic(LoggerFactory.class);
        Logger log = mock(Logger.class);
        when(LoggerFactory.getLogger(CallService.class)).thenReturn(log);
        callService.save(message);
        verify(log);
    }

    private Call buildCall() {
        return Call.builder().id(randomUUID()).message("Test").date(new Date()).build();
    }

}
