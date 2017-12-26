package messenger.model;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Антон on 28.11.2017.
 */
@XmlRootElement
public class File {

    @Id
    private UUID id;

    private String name;
    public File(){

    }

    public File(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
