package project;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.*;
import java.util.Random;

public class Bus extends Bug {
    private int number;
    public String home;
    public String destination;
    private final int CAPACITY = 20;
    private LinkedList<Passenger> passengers;

    public Bus(int number) {
        this.number = number;
        // Example "S1S2"
        this.passengers = new LinkedList<>();
    }

    setDestination(String destination) {
        this.destination = destination;
    }

    public void interactWithBusStop(BusStop busStop) {
        LinkedList<Passenger> newPassengers = busStop.boardPassengers(currentRoute, capacity - passengers.size());
        passengers.addAll(newPassengers); // Board new passengers
    }

    @Override
    public void move() {
        Grid<Actor> gr = this.getGrid();
        if (gr != null) {
            Location currentLocation = this.getLocation();
            Location next = currentLocation.getAdjacentLocation(this.getDirection());
            if (gr.isValid(next)) {
                this.moveTo(next);
            }
        }
    }




    @Override
    public void turn () {this.setDirection(this.getDirection() + 180);}

    public void act() {
        Random random = new Random();
        Passenger[] passengersArray = passengers.toArray(new Passenger[0]);
        handleInfection(passengersArray, random);

        swapHomeAndDestination(currentTimeStep);
        // Movement logic for the bus
        move();
        if (this.canMove()) {
            this.move();
        } else {
            this.turn();
            disembarkPassengers();
            boardPassengers();
        }
    }

    public void changeRoute(String route) {

    }

    public void swapHomeAndDestination(int currentTimeStep) {
        if (currentTimeStep % 10 == 0) {
            String temp = this.home;
            this.home = this.destination;
            this.destination = temp;
        }
    }


    public void boardPassengers(BusStop busStop) {
        Grid<Actor> gr = this.getGrid();
        if (gr == null) return;

        Location currentLocation = this.getLocation();
        //String currentLocationId = currentLocation.toString(); // Assuming the location string is used as the bus stop ID

        if (home.equals(busStop.getBusStopId())) {
            for (Passenger passenger : BusStop) = destinationRoute;
        } else {
            route = homeRoute;
        }
        for (Actor actor : gr.getNeighbors(currentLocation)) {
            if (actor instanceof Passenger) {
                Passenger passenger = (Passenger) actor;
                if (hasCapacity() && !passenger.isOnBus() && passenger.getHomeBusStopId().equals(currentLocationId)) {
                    addPassenger(passenger);
                    passenger.setOnBus(true);
                    passenger.removeSelfFromGrid(); // Remove from grid representation
                    busStop.removePassengerFromQueue(passenger); // Remove from the bus stop queue
                    System.out.println("Passenger " + passenger.getTravelerId() + " boarded the bus at " + currentLocationId);
                }
            }
        }
    }

    public void disembarkPassengers() {
        Grid<Actor> gr = getGrid();
        if (gr == null) return;

        Location currentLocation = getLocation();
        List<Passenger> toRemove = new ArrayList<>();

        for (Passenger passenger : passengers) {
            if (passenger.getWorkBusStopId().equals(currentLocation.toString())) {
                toRemove.add(passenger);
                passenger.setOnBus(false);
                passenger.setReachedDestination(true);
                gr.put(currentLocation, passenger); // Place passenger at their work stop
            }
        }

        passengers.removeAll(toRemove);
    }

    private void handleInfection(Passenger[] passengersArray, Random random) {
        for (Passenger passenger : passengersArray) {
            // Each passenger interacts with 10 other passengers
            for (int i = 0; i < 10; i++) {
                Passenger otherPassenger = passengersArray[random.nextInt(passengersArray.length)];
                if (passenger != otherPassenger) {
                } if (!passenger.isInfected() && otherPassenger.isInfected()) {
                    double chance = Math.random();
                    if (chance < passenger.getProbability()) {
                        passenger.setInfected(true);
                    }
                }
            }
        }
    }


//    private static final int CAPACITY = 20;
//    private List<Passenger> passengers = new ArrayList<>();
//    Location location = new Location(0, 0);
//    String route;
//
//    public int direction;
//    public String homeRoute;

//    public int number;
//
//    private static final int SPEED = 1;
//
//    public Bus(int number) {
//        this.number = number;
//        //this.home = home;
//        //this.destination = destination;
//        this.adjustRouteAndDirection();
//        //this.setDirection(this.direction);
//    }
//
    private void setDestination() {
        switch (this.number) {
            case 1:
                this.home = "S1";
                this.destination = "S2";
                break;
            case 2:
                this.home = "S2";
                this.destination = "S3";
                break;
            case 3:
                this.home = "S3";
                this.destination = "S4";
                break;
            case 4:
                this.home = "S4";
                this.destination = "S1";
                break;
            default:
                throw new IllegalArgumentException("Invalid bus number: " + this.number);
        }
    }
//
//    public boolean hasCapacity() { return this.passengers.size() < CAPACITY; }
//
//

//
//
//

//
//
//
//
//
//
//    public void addPassenger(Passenger passenger) {
//        if (this.passengers.size() < Bus.CAPACITY) {
//            this.passengers.add(passenger);
//        }
//    }
//    public boolean canMove() {
//        Grid<Actor> gr = this.getGrid();
//        if (gr == null) {
//            return false;
//        } else {
//            Location loc = this.getLocation();
//            Location next = loc.getAdjacentLocation(this.getDirection());
//            return gr.isValid(next);
//        }
//    }
//
//
//

//
//
//    public void reportInfectionStatus() {
//        // Example reporting logic: print the number of infected passengers
//        long infectedCount = passengers.stream().filter(Passenger::isInfected).count();
//        System.out.println("Bus infection report: " + infectedCount + " infected passengers.");
//    }

}