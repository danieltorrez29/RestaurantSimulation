package test;

import model.business.Restaurant;

public class Run {


    public static void main(String[] args) {
		Restaurant restaurant = Restaurant.getInstance();
		restaurant.init(30);
	}
}