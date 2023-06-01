package model.business;

import model.utilities.MiddleSquare;

import java.util.ArrayList;
import java.util.List;

public class Table extends Thread {

	private int idTable;
	public static int ID_BASE = 1;
	private int waitTime;
	private boolean freeTable;
	private ArrayList<Client> listClients;
	private Waiter waiter;

	private double amountTip = 0;

	public Table() {
		this.idTable = ID_BASE++;
		this.freeTable = false;
		this.listClients = new ArrayList<>();
	}

	@Override
	public void run() {
		List<Waiter> listWaiters = Restaurant.getInstance().getListWaiters();
		super.run();
		try {
			freeTable = true;
			for (int i = 0; i < 30; i++) {
				this.addToTable();
				this.startEat();
				listClients.removeAll(listClients);
				for (Waiter waiter : listWaiters) {
					if (!waiter.isService()) {
						waiter.setService(true);
						waiter.setTimeToServe(Restaurant.getInstance().getTimesWaitersRandom().generateNi());
						this.setWaiter(waiter);
						waiter.run();
					}
				}
				Thread.sleep(this.getTime()+1000);
				aggregateCalification();
				addTip();
				freeTable = false;
			} 
			ID_BASE = 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void aggregateCalification(){
		Calification calification = new Calification();
		Plate plate = Restaurant.getInstance().getListOfPlates().get((int) new MiddleSquare(0,6).generateNi());
		Restaurant.getInstance().addNewSale(plate.getPrice());
		calification.setPlate(plate);
		int rating = (int) new MiddleSquare(1,5).generateNi();
		plate.getRatings().add(rating);
		plate.addAmountPurchased();
		calification.setScore(rating);
		System.out.println("Calificacion de la mesa: " + idTable + " Plato : " + calification.getPlate().getPlateName() + " Puntaje: " + calification.getScore());
		Restaurant.getInstance().addCalification(calification);
		calification.setPlate(Restaurant.getInstance().probabilityChoosePlate());
	}

	private void addTip(){
		Restaurant.getInstance().addNewTip(Restaurant.getInstance().obtainTip());
	}

	public void addToTable() {
		int randomTable = (int) new MiddleSquare(1,7).generateNi();
		for (int i = 0; i < randomTable; i++) {
			Restaurant.getRestaurant().increaseClients();
			listClients.add(new Client(Restaurant.getInstance().getTimesToEatRandom().generateNi()));
		}
		System.out.println("Mesa Numero: "+idTable+" con "+ listClients.size()+ " clientes");
	}
	
	public int getTime() {
		double aux = 0;
		for (Client listClient : listClients) {
			aux += listClient.getTime();
		}
		return (int) aux;
	}

	public void startEat() {
		for (Client listClient : listClients) {
			listClient.start();
			System.out.println(listClient.toString());
		}
	}

	public void remove() {
		for (int i = 0; i < listClients.size(); i++) { 
			if (!listClients.get(i).isEating()) {
				listClients.remove(i);
			}
		}
	}

	public int getIdTable() {
		return idTable;
	}
	
	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}
	
	public int getWaitTime() {
		return waitTime;
	}
	
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public boolean isOccuppied() {
		return freeTable;
	}
	
	public void setOccuppied(boolean isOccuppied) {
		this.freeTable = isOccuppied;
	}
	
	public ArrayList<Client> getListClients() {
		return listClients;
	}

	public void setListClients(ArrayList<Client> listClients) {
		this.listClients = listClients;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public boolean isFreeTable() {
		return freeTable;
	}

	public void setFreeTable(boolean freeTable) {
		this.freeTable = freeTable;
	}

	public double getAmountTip() {
		return amountTip;
	}

	public void setAmountTip(double amountTip) {
		this.amountTip = amountTip;
	}
}