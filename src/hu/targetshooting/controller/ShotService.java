package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.ArrayList;
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

    public long countSuccessShots(int id) {
        return getShotResultById(id).countSuccessShots();
    }

    public  int getLongestSuccessSequenceSize(int id) {
        return getShotResultById(id).getLongestSuccessSequenceSize();
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

    public List<String> getFinalResult() {
        List<String> lines = new ArrayList<>();
        List<ShotResult> finalResult =createFinalResult();
        int prevOrder = 0, prevScore = 0;
        for (int i = 0; i < finalResult.size(); i++) {
            ShotResult actualResult = finalResult.get(i);
            int order = actualResult.getScore() == prevScore ? prevOrder : i + 1;
            lines.add(order + actualResult.toString());
            prevScore = actualResult.getScore();
            prevOrder = order;
        }
        return lines;
    }

    private List<ShotResult> createFinalResult() {
        return results.stream()
                .sorted(Comparator.comparing(ShotResult::getScore).reversed())
                .collect(Collectors.toList());

    }
}
