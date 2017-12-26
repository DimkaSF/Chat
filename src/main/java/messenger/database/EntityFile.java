package messenger.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Дмитрий on 02.12.2017.
 */
@Entity(name = "file")
public class EntityFile {
    @Id
    @GeneratedValue
    Long Id;
}

