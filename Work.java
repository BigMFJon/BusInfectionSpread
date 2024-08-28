package project;

import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.HashMap;
import java.util.Map;

public class Work extends Rock {

    Map<Passenger, Integer> atWork = new HashMap<>();
    TimeStepTracker currentTimeStep = new TimeStepTracker();
    Bus bus;
    Location location = this.getLocation();

    public void Work(Passenger passenger, int timeStep) {
        if (passenger.isReachedDestination()){
            passenger.setAtWork(true);
            atWork.put(passenger, passenger.getDurationWork() + currentTimeStep.getCurrentTimeStep());
            //System.out.println("Passenger " + passenger.getTravelerId() + " started work at time step " + timeStep);
        }

    }

    @Override
    public void act() {
        for (Map.Entry<Passenger, Integer> e : atWork.entrySet()) {
            Passenger passenger = e.getKey();
            int time = e.getValue();
            if (time == currentTimeStep.getCurrentTimeStep()) {
                passenger.setAtWork(false);
                //System.out.println("Passenger " + passenger.getTravelerId() + " completed work at time step " + time);
            }
        }
    }
}
