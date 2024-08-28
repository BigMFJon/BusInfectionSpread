package project;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import info.gridworld.grid.BoundedGrid;


    public class SimulationRunner {
        public static void main(String[] args) throws IOException {


            ActorWorld actorWorld = new ActorWorld();

            // Add buses
            Bus bus1 = new Bus(180);
            Bus bus2 = new Bus(0);
            Bus bus3 = new Bus(270);
            Bus bus4 = new Bus(90);

            BusStop busStop1 = new BusStop("S1");
            BusStop busStop2 = new BusStop("S2");
            BusStop busStop3 = new BusStop("S3");
            BusStop busStop4 = new BusStop("S4");

            actorWorld.add(new Location(0, 0), busStop1);
            actorWorld.add(new Location(0, 9), busStop2);
            actorWorld.add(new Location(9, 9), busStop3);
            actorWorld.add(new Location(9, 0), busStop4);
            actorWorld.add(new Location(0, 0), bus1);
            actorWorld.add(new Location(9, 9), bus2);
            actorWorld.add(new Location(0, 9), bus3);
            actorWorld.add(new Location(9, 0), bus4);

            // Parse passengers from CSV
//            PassengerParser parser = new PassengerParser();
//            List<Passenger> passengers = parser.parsePassengerss("src/project/updatedPassengers.csv");

            // Add passengers to the grid
//            for (Passenger passenger : passengers) {
//                Location location = getInitialLocationForBusStop(passenger.getHomeBusStopId());
//                actorWorld.add(location, passenger);
//            }

            // Add infection center
            //Infection infectionCenter = new Infection();
            //actorWorld.add(new Location(5, 5), infectionCenter);

            actorWorld.show();
        }

        private static Location getInitialLocationForBusStop(String busStopId) {
            // Map bus stop IDs to grid locations (adjust these mappings as necessary)
            switch (busStopId) {
                case "S1":
                    return new Location(0, 0);
                case "S2":
                    return new Location(0, 9);
                case "S3":
                    return new Location(9, 9);
                case "S4":
                    return new Location(9, 0);
                default:
                    return new Location(0, 0);  // Default location if no match found
            }
        }

}
