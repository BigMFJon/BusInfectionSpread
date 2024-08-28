package project;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BooleanInfection {
    public static void main(String[] args) {
        String inputFilePath = "src/project/updated_passengers.csv";
        String outputFilePath = "src/project/updatedPassengers.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            // Read the header
            String header = br.readLine();
            bw.write(header + "\n");

            // Process each line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Convert infectionStatus
                if (values[5].equals("None")) {
                    values[5] = "false";
                } else if (values[5].equals("Infected")) {
                    values[5] = "true";
                }
                // Write the modified line to the output file
                bw.write(String.join(",", values) + "\n");
            }

            System.out.println("The 'infectionStatus' column has been successfully converted to boolean and saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
