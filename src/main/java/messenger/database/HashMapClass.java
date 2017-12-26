package messenger.database;

import messenger.model.File;
import messenger.model.Message;
import messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Антон on 14.11.2017.
 */
public class HashMapClass {
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<Long, Profile> profiles = new HashMap<>();
    private static Map<UUID, File> files = new HashMap<>();

    public static Map <Long, Message> getMessages(){
        return messages;
    }
    public static Map <Long, Profile> getProfiles(){
        return profiles;
    }
    public static Map <UUID, File> getFiles() { return files; }
}
