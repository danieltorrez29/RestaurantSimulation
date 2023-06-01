package model.business;

public class Plate {
    private int idPlate;
    private String plateName;
    private double probability;

    public Plate(int id, String namePlate) {
        this.idPlate = id;
        this.plateName = namePlate;
    }

    public Plate() {
    }

    public int getIdPlate() {
        return idPlate;
    }

    public void setIdPlate(int id) {
        this.idPlate = id;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setName(String plateName) {
        this.plateName = plateName;
    }

    public double getRatingProbability() {
        return probability;
    }

    public void setRatingProbability(double probability) {
        this.probability = probability;
    }
}