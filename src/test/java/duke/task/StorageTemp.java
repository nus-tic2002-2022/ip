package duke.task;

import duke.Storage;

import java.util.List;

public class StorageTemp extends Storage {
    public List<String> stored;

    public StorageTemp() {
        super("StorageTemp.txt");
    }

    @Override
    public void store(List<String> lines) {
        stored = lines;
    }
}
