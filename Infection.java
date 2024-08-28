package project;

import info.gridworld.actor.Actor;

import java.util.HashMap;
import java.util.Map;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.Random;

public class Infection extends Actor {

    private Map<Integer, Integer> infectionCounts = new HashMap<>();

    @Override
    public void act() {
        reportInfections();
    }

    public void trackInfection(Passenger passenger) {
        int id = passenger.getTravelerId();
        infectionCounts.put(id, infectionCounts.getOrDefault(id, 0) + 1);
    }

    private void reportInfections() {
        System.out.println("Infection report:");
        for (Map.Entry<Integer, Integer> entry : infectionCounts.entrySet()) {
            System.out.println("Passenger ID: " + entry.getKey() + " Infections: " + entry.getValue());
        }
    }
}
