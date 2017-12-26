package messenger.service;

import messenger.database.HashMapClass;
import messenger.model.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Антон on 28.11.2017.
 */
public class FileService {
    private Map<UUID,File> files = HashMapClass.getFiles();

    public FileService() {
        //messages.put(1L, new Message(1, "Hello 1", "Author_1"));
        //messages.put(2L, new Message(2, "Hello 2", "Author_2"));

    }

    public List<File> getAllFiles() {
        files.values();
        return new ArrayList<File>(files.values());
    }


    /*
    public File getFiles(long id) {
        return files.get(id);
    }*/

    public File addFile(UUID id, File file) {
        files.put(id, file);
        return file;
    }
}
