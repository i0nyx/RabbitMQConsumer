package by.intexsoft.repositories;

import by.intexsoft.pojo.Call;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends CassandraRepository<Call, Integer> {
}
