package project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class PassengerParser {

    private List<String[]> passengerData = new ArrayList<>();

    public void readData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                passengerData.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processPassengers(List<AgeGroupProbability> probabilities) {
        if (!passengerData.isEmpty()) {
            String[] header = passengerData.get(0);
            String[] newHeader = new String[header.length + 1];
            System.arraycopy(header, 0, newHeader, 0, header.length);
            newHeader[header.length] = "probability";
            passengerData.set(0, newHeader);

            for (int i = 1; i < passengerData.size(); i++) {
                String[] row = passengerData.get(i);
                Passenger passenger = createPassengerFromRow(row);
                double infectionProbability = findProbabilityForPassenger(passenger, probabilities);
                String[] newRow = new String[row.length + 1];
                System.arraycopy(row, 0, newRow, 0, row.length);
                newRow[row.length] = String.valueOf(infectionProbability);
                passengerData.set(i, newRow);
            }
        }
    }

    public void writeOutData(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (String[] row : passengerData) {
                writer.println(String.join(",", row));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Passenger createPassengerFromRow(String[] row) {
        int travelerId = Integer.parseInt(row[0]);
        int age = Integer.parseInt(row[3]);
        boolean wearsMask = Boolean.parseBoolean(row[4]);
//        boolean infectionStatus = Boolean.parseBoolean(row[5]);
        boolean infectionStatus = row[5].equals("Infected");
        String homeBusStopId = row[1];
        String workBusStopId = row[2];
        int tHomeWork = Integer.parseInt(row[6]);
        int durationWork = Integer.parseInt(row[7]);
        double probability = Double.parseDouble(row[8]);
//        System.out.println(" wearsMask: " + wearsMask);
        System.out.println("Passenger ID: " + travelerId + ", Age: " + age + ", Wears Mask: " + wearsMask + ", Infection Status: " + infectionStatus + ", Home Bus Stop: " + homeBusStopId + ", Work Bus Stop: " + workBusStopId + ", Time Home-Work: " + tHomeWork + ", Duration Work: " + durationWork);
        return new Passenger(travelerId, infectionStatus, homeBusStopId, workBusStopId, tHomeWork, durationWork, probability); //(travelerId, age, wearsMask, infectionStatus, homeBusStopId, workBusStopId, tHomeWork, durationWork);
    }

    private double findProbabilityForPassenger(Passenger passenger, List<AgeGroupProbability> probabilities) {
        //System.out.println("Passenger Age: " + passenger.getAge() + ", Wears Mask: " + passenger.isWearsMask());
        for (AgeGroupProbability group : probabilities) {
            //System.out.println("Checking Age Range: " + group.getLowerBound() + "-" + group.getUpperBound() + ", Mask: " + group.isMask() + ", Probability: " + group.getProbability());
            if (group.isInRange(passenger.getAge()) && group.isMask() == passenger.isWearsMask()) {
                return group.getProbability();
                //System.out.println(group.getProbability());
            }
        }
        return 0;// Default value if no match found
    }

    public static List<Passenger> parsePassengers(String filePath) {
        List<Passenger> passengers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int travelerId = Integer.parseInt(values[0]);
                String homeBusStopId = values[1];
                String workBusStopId = values[2];
                int age = Integer.parseInt(values[3]);
                boolean wearsMask = Boolean.parseBoolean(values[4]);
                boolean infectionStatus = values[5].equals("Infected");
                int tHomeWork = Integer.parseInt(values[6]);
                int durationWork = Integer.parseInt(values[7]);
                double probability = Double.parseDouble(values[8]);

                Passenger passenger = new Passenger(travelerId, infectionStatus, homeBusStopId, workBusStopId, tHomeWork, durationWork, probability);
                passengers.add(passenger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<Passenger>) passengers;
    }


    public static List<Passenger> parsePassengerss(String filePath) throws IOException {

        try (FileReader reader = new FileReader(filePath)) {
            return new CsvToBeanBuilder<Passenger>(reader)
                    .withType(Passenger.class)
                    .build()
                    .parse();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("File not found: " + filePath);
        }

    }

}