package project;

import info.gridworld.actor.Actor;

import com.opencsv.bean.CsvBindByName;
import info.gridworld.grid.Location;

public class Passenger extends Actor {
    @CsvBindByName(column = "travelerId")
    int travelerId;
    @CsvBindByName(column = "homeBusStopId")
    String homeBusStopId;
    @CsvBindByName(column = "workBusStopId")
    String workBusStopId;
    @CsvBindByName(column = "age")
    int age;
    @CsvBindByName(column = "wearsMask")
    boolean wearsMask;
    @CsvBindByName(column = "infectionStatus")
    boolean infectionStatus;
    @CsvBindByName(column = "tHomeWork")
    int tHomeWork;
    @CsvBindByName(column = "durationWork")
    int durationWork;
    @CsvBindByName (column = "probability")
    double probability;
    private boolean onBus = false;
    private boolean reachedDestination = false;
    String currentBusStopId;
    boolean atWork = false;
    boolean queued = false;
    private String state;
    private Integer workFinishedAt;
    private Integer arrivedHomeAt;



    public Passenger (int travelerId, boolean infectionStatus, String homeBusStopId, String workBusStopId, int tHomeWork, int durationWork, double probability) {
        this.travelerId = travelerId;
        this.infectionStatus = infectionStatus;
        this.homeBusStopId = homeBusStopId;
        this.workBusStopId = workBusStopId;
        this.tHomeWork = tHomeWork;
        this.durationWork = durationWork;
        this.probability = probability;
        this.queued = false;
        this.onBus = false;
        this.reachedDestination = false;
        this.atWork = false;
        this.state = "GOING_TO_WORK";
    }

   // public Passenger(int travelerId, int age, boolean wearsMask, boolean infectionStatus, String homeBusStopId, String workBusStopId, int tHomeWork, int durationWork) {}
   @Override
   public String toString() {
       return "Passenger[travelerId=" + travelerId +
               ", homeBusStopId=" + homeBusStopId +
               ", workBusStopId=" + workBusStopId +
               ", age=" + age +
               ", wearsMask=" + wearsMask +
               ", infectionStatus=" + infectionStatus +
               ", tHomeWork=" + tHomeWork +
               ", durationWork=" + durationWork +
               ", probability=" + probability + "]";
   }


    public String getHomeBusStopId() {
        return homeBusStopId;
    }

    public String getWorkBusStopId() {
        return workBusStopId;
    }

    public int getTravelerId() {
        return travelerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWearsMask() {
        return wearsMask;
    }

    public boolean isInfected() {
        return infectionStatus;
    }

    public void setInfected(boolean infectionStatus) {
        this.infectionStatus = infectionStatus;
        setColor(infectionStatus ? java.awt.Color.RED : java.awt.Color.GREEN);
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public boolean isOnBus() {
        return onBus;
    }

    public void setOnBus(boolean onBus) {
        this.onBus = onBus;
    }

    public String getCurrentBusStopId() {
        return currentBusStopId;
    }

    public void setCurrentBusStopId(String currentBusStopId) {
        this.currentBusStopId = currentBusStopId;
    }

    public boolean isReachedDestination() {
        return reachedDestination;
    }

    public void setReachedDestination(boolean reachedDestination) {
        this.reachedDestination = reachedDestination;
    }

    public void setQueued(boolean queued) { this.queued = queued;}

    public boolean isQueued() { return queued; }

    public int getDurationWork() { return durationWork; }

    public void setAtWork(boolean atWork) {
        this.atWork = atWork;
    }

    @Override
    public void act() {
        if (onBus) {
            interactWithOthers();
        }
    }

    private void interactWithOthers() {
        // Infection spread logic is handled by the bus
    }


    public String getRoute() {
        if (state.equals("GOING_TO_WORK")) {
            return homeBusStopId + workBusStopId;
        } else if (state.equals("GOING_HOME")) {
            return workBusStopId + homeBusStopId;
        }
        return "";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void markArrivedHome(int currentTimeStep) {
        this.arrivedHomeAt = currentTimeStep;
        this.state = "AT_HOME";
    }

    public void setWorkFinishedAt(int currentTimeStep) {
        this.state = "GOING_HOME";
    }

}