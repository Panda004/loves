package hu.targetshooting;

import hu.targetshooting.controller.ShotService;
import hu.targetshooting.model.service.Console;
import hu.targetshooting.model.service.DataApi;
import hu.targetshooting.model.service.DataParser;
import hu.targetshooting.model.service.FileReader;

import java.util.Scanner;

public class App {

    private final ShotService service;
    private final Console console;

    private App() {
        DataApi dataApi = new DataApi(new FileReader("verseny.txt"),
                new DataParser());
        service = new ShotService(dataApi.getData());
        console = new Console(new Scanner(System.in));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2.feladat");
        System.out.println("Az egymást követően többször találó versnyzők: " + service.getTwoSucessShotsIds());
        System.out.println("3. feladat");
        System.out.println("A legtöbb lövést leadó versenyző rajtszáma: "+ service.getLongestShotSequenceId());
        System.out.println("5.feladat");
        System.out.println("Adjon meg egy rajtszámot! ");
        int id = console.read();
        System.out.println("5.a feladat: Célt érő lövések:" + service.getSuccessShotIndexes(id));
        System.out.println("5.d feladat: A versenyző pontszáma:" + service.getScoreById(id));
    }
}
