package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.List;

public class ShotService {

    private final List<ShotResult> results;

    public ShotService(List<ShotResult> results) {
        this.results = results;
    }
}
