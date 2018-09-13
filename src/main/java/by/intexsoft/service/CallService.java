package by.intexsoft.service;

import by.intexsoft.pojo.Call;
import org.springframework.amqp.core.Message;

/**
 * An interface describing methods for working with an object{@link Call}
 */
public interface CallService {
    /**
     * The method converts {@link Message} into a {@link Call} and save in a database
     *
     * @param message
     * @throws org.springframework.data.cassandra.CassandraInternalException
     * @throws Exception
     */
    void save(Message message);
}
