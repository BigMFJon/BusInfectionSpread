package project;


import info.gridworld.actor.Rock;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
//import java.util.LinkedList;
//import java.util.ListIterator;

public class BusStop extends Rock {
    private String busStopId;
    private LinkedList<Passenger> queue;
    //private Map<String, Queue<Passenger>> waitingPassengers; // Map of destination bus stop ID to queue of passengers

    public BusStop(String busStopId) {
        this.busStopId = busStopId;
        this.queue = new LinkedList<>();
    }

    public void addPassenger(Passenger passenger) {
        queue.addLast(passenger); // Adds to the end of the queue
    }

    // Method to board passengers based on bus's route and capacity
    public LinkedList<Passenger> boardPassengers(String route, int capacity) {
        LinkedList<Passenger> boardingPassengers = new LinkedList<>();
        ListIterator<Passenger> iterator = queue.listIterator();

        while (iterator.hasNext() && boardingPassengers.size() < capacity) {
            Passenger passenger = iterator.next();
            if (passenger.getRoute().equals(route)) {
                boardingPassengers.add(passenger);
                iterator.remove(); // Remove from the queue after boarding
            }
        }

        return boardingPassengers; // Return the list of passengers who boarded
    }



//    public BusStop(String busStopId) {
//        this.busStopId = busStopId;
//        this.waitingPassengers = new HashMap<>();
//
//        switch (busStopId) {
//            case "S1":
//                waitingPassengers.put("S1S2", new LinkedList<>());
//                waitingPassengers.put("S1S4", new LinkedList<>());
//                break;
//            case "S2":
//                waitingPassengers.put("S2S3", new LinkedList<>());
//                waitingPassengers.put("S2S1", new LinkedList<>());
//                break;
//            case "S3":
//                waitingPassengers.put("S3S2", new LinkedList<>());
//                waitingPassengers.put("S3S4", new LinkedList<>());
//                break;
//            case "S4":
//                waitingPassengers.put("S4S1", new LinkedList<>());
//                waitingPassengers.put("S4S3", new LinkedList<>());
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown Bus Stop ID: " + busStopId);
//        }
//
//    }
//
//    public void addPassenger(Passenger passenger) {
//        String route = passenger.getRoute();
//        Queue<Passenger> queue = waitingPassengers.get(route);
//        if (queue != null) {
//            queue.add(passenger);
//        }
//    }
//
//
//        public void processPassengers(Passenger passenger, int currentTimeStep) {
//            // Add passenger to the appropriate queue based on their current state
//            addPassenger(passenger);
//
//            // Check if the passenger has finished work and is now waiting to return home
//            if (passenger.getState().equals("AT_WORK")) {
//                passenger.setState("WAITING_TO_RETURN_HOME");
//                passenger.setWorkFinishedAt(currentTimeStep + passenger.getDurationWork());
//            }
//
//            // If passenger is going home, mark as arrived home to end day simulation
//            if (passenger.getState().equals("GOING_HOME") && passenger.getRoute().equals(passenger.getWorkBusStopId() + passenger.getHomeBusStopId())) {
//                passenger.markArrivedHome(currentTimeStep);
//            }
//        }
//
//
//
//    public Passenger getNextPassenger(String destination) {
//        Queue<Passenger> queue = waitingPassengers.get(destination);
//        return (queue != null) ? queue.poll() : null;
//    }
//
//    public boolean isQueueEmpty(String destination) {
//        Queue<Passenger> queue = waitingPassengers.get(destination);
//        return (queue == null || queue.isEmpty());
//    }
//
//    public void boardBus(Bus bus, String destination) {
//        Queue<Passenger> queue = waitingPassengers.get(destination);
//        if (queue == null) return;
//
//        Queue<Passenger> remainingPassengers = new LinkedList<>();
//        while (!queue.isEmpty() && bus.hasCapacity()) {
//            Passenger passenger = queue.poll();
//            if (passenger != null) {
//                bus.addPassenger(passenger);
//                passenger.setOnBus(true);
//                passenger.removeSelfFromGrid();
//            } else {
//                remainingPassengers.add(passenger);
//            }
//        }
//        queue.addAll(remainingPassengers);
//    }
//
//    @Override
//    public void act() {
//        Random random = new Random();
//        for (Queue<Passenger> queue : waitingPassengers.values()) {
//            Passenger[] passengersArray = queue.toArray(new Passenger[0]);
//            handleInfection(passengersArray, random);
//        }
//    }
//
//    private void handleInfection(Queue<Passenger> queue, Random random) {
//        List<Passenger> passengers = new ArrayList<>(queue);
//        for (Passenger passenger : passengers) {
//            for (int i = 0; i < 5; i++) {
//                Passenger otherPassenger = passengers.get(random.nextInt(passengers.size()));
//                if (passenger != otherPassenger) {
//                    if (!passenger.isInfected() && otherPassenger.isInfected()) {
//                        double chance = Math.random();
//                        if (chance < passenger.getProbability()) {
//                            passenger.setInfected(true);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
    public String getBusStopId() {
        return busStopId;
    }
}
