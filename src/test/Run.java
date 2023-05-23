package test;

import entities.Restaurant;

public class Run {


    public static void main(String[] args) {
		Restaurant restaurant = Restaurant.getInstance();
		restaurant.start(60);
	}
}