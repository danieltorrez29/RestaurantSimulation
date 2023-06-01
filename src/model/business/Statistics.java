package model.business;

public class Statistics {

    private String name;
    private double amountSold;
    private double rating;
    private double profit;

    public Statistics(String name, double amountSold, double rating, double profit) {
        this.name = name;
        this.amountSold = amountSold;
        this.rating = rating;
        this.profit = profit;
    }

    public Statistics() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(double amountSold) {
        this.amountSold = amountSold;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
