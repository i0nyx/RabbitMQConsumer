package by.intexsoft.repositories;

import by.intexsoft.pojo.Call;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface uses methods inherited from the class {@link CassandraRepository}.
 */
@Repository
public interface CallRepository extends CassandraRepository<Call, Integer> {
}
