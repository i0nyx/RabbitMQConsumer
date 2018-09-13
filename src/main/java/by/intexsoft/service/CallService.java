package by.intexsoft.service;

import by.intexsoft.pojo.Call;
import org.springframework.amqp.core.Message;
import org.springframework.data.cassandra.CassandraInternalException;

/**
 * An interface describing methods for working with an object{@link Call}
 */
public interface CallService {
    /**
     * The method converts {@link Message} into a {@link Call} and save in a database
     *
     * @param message Data received from the queue
     * @throws CassandraInternalException if the {@link Call} object is not stored in the database
     * @throws Exception                  if {@link Message} do not convert to json
     */
    void save(Message message);
}
