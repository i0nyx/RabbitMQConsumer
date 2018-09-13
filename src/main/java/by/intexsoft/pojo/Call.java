package by.intexsoft.pojo;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * Class of calls with property id, date, message
 */
@Table
@Data
public class Call {

    @PrimaryKey
    private UUID id;
    private Date date;
    private String message;

}
