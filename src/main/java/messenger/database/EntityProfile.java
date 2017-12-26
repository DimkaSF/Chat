package messenger.database;

import messenger.model.Profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Дмитрий on 02.12.2017.
 */
@Entity(name = "profile")
public class EntityProfile {
    Profile profile;
}
