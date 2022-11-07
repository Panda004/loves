package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShotService {

    private final List<ShotResult> results;

    public ShotService(List<ShotResult> results) {
        this.results = results;
    }
    public String getTwoSucessShotsIds() {
        return results.stream()
                .filter(ShotResult::hasTwoSuccesShotsInRow)
                .map(ShotResult::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

    }
    public int getLongestShotSequenceId(){
        return results.stream()
                .max(Comparator.comparing(ShotResult::getShotCount))
                .map(ShotResult::getId)
                .get();
    }

    public String getSuccessShotIndexes(int id){
        return getShotResultById(id).getSuccessShotIndexes();
    }

    public int getScoreById(int id){
        return getShotResultById(id).getScore();
    }

    private ShotResult getShotResultById(int id) {
        return results.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .get();
    }



}
