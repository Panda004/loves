package hu.targetshooting.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ResultWriter {

    private final String filename;

    public ResultWriter(String filename) {
        this.filename = filename;
    }

    public void writeAll(List<String> lines) {
        try {
            Files.write(Path.of(filename), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
