package project;

public class AgeGroupProbability {

    private int lowerBound;
    private int upperBound;
    private boolean mask;
    private double probability;

    public AgeGroupProbability(int lowerBound, int upperBound, boolean mask, double probability) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.mask = mask;
        this.probability = probability;
    }

    public boolean isInRange(int age) {
        return age >= lowerBound && age <= upperBound;
    }

    public double getProbability() {
        return probability;
    }

    public boolean isMask() {
        return mask;
    }
    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
