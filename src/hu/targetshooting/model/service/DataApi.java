package hu.targetshooting.model.service;


import hu.targetshooting.model.domain.ShotResult;

import java.util.List;

public class DataApi {

    private final FileReader reader;
    private final DataParser parser;

    public DataApi(FileReader reader, DataParser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    public List<ShotResult> getData() {
        return parser.parse(reader.read());
    }
}
