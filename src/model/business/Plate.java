package model.business;

public class Plate {
    private int idPlate;
    private String plateName;
    private double probability;
    private double price;

    private int amountPurchased;

    public Plate(int idPlate, String plateName, double price) {
        this.idPlate = idPlate;
        this.plateName = plateName;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(int amountPurchased) {
        this.amountPurchased = amountPurchased;
    }
}