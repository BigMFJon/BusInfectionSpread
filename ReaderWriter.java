//package project;
//
//import java.util.List;
//
//public class Simulation {
//    public static void main(String[] args) {
//        List<AgeGroupProbability> probabilities = ProbabilityParser.parseProbabilities("path/to/probability.csv");
//        List<Passenger> passengers = ProbabilityParser.parsePassengers("passengers.csv");
//
//        for (Passenger passenger : passengers) {
//            double infectionProbability = findProbabilityForPassenger(passenger, probabilities);
//            System.out.println("Passenger ID: " + passenger.getTravelerId() +
//                    " has infection probability: " + infectionProbability);
//        }
//    }
//
//
//    public static double findProbabilityForPassenger(Passenger passenger, List<AgeGroupProbability> probabilities) {
//        for (AgeGroupProbability group : probabilities) {
//            if (group.isInRange(passenger.getAge()) && group.isMask() == passenger.isWearsMask()) {
//                return group.getProbability();
//            }
//        }
//        return 0; // Default value if no match found
//    }
//
//}

package project;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java.util.List;

public class ReaderWriter {

    public static void main(String[] args) throws IOException {
        PassengerParser simulationReaderWriter = new PassengerParser();
        List<Passenger> passengers = simulationReaderWriter.parsePassengers("src/project/updatedPassengers.csv");

        //simulationReaderWriter.readData("src/project/passengers.csv");
        //simulationReaderWriter.processPassengers(probabilities);
        //simulationReaderWriter.writeOutData("src/project/updated_passengers.csv");

        passengers.forEach(System.out::println);
        //System.out.println(passengers);
    }
}