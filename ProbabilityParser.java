package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProbabilityParser {

    public static List<AgeGroupProbability> parseProbabilities(String filePath) {
        List<AgeGroupProbability> probabilityList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String[] ageRange = values[1].trim().split("-"); // Split the age range (e.g., "30-39")

                int lowerBound = Integer.parseInt(ageRange[0].trim());
                int upperBound = Integer.parseInt(ageRange[1].trim());
                boolean mask = Boolean.parseBoolean(values[2].trim());
                double probability = Double.parseDouble(values[3].trim());

//                System.out.println("lowerBound: " + lowerBound + " upperBound: " + upperBound + " mask: " + mask + " probability: " + probability);
                AgeGroupProbability ageGroup = new AgeGroupProbability(lowerBound, upperBound, mask, probability);
                probabilityList.add(ageGroup);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return probabilityList;
    }
}