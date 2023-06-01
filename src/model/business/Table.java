package model.business;

import model.utilities.MiddleSquare;

import java.util.ArrayList;
import java.util.List;

public class Table extends Thread {

	private int idTable;
	public static int ID_BASE = 1;
	private String tableName;
	private int waitTime;
	private boolean freeTable;
	private ArrayList<Client> listClients;
	private Waiter waiter;

	public Table(String nombre) {
		this.idTable = ID_BASE++;
		this.tableName = nombre;
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
				Calification calification = new Calification();
				this.addToTable();
				this.startEat();
				calification.setPlate(Restaurant.getInstance().probabilityChoosePlate());
				int countOfClients = listClients.size();
				listClients.removeAll(listClients);
				for (Waiter waiter : listWaiters) {
					if (!waiter.isService()) {
						waiter.setService(false);
						waiter.setTimeToServe(Restaurant.getInstance().getTimesWaitersRandom().generateNi());
						this.setWaiter(waiter);
						waiter.run();
						waiter.setService(true);
					}
				}
				Thread.sleep(this.getTime()+1000);
				calification.setScore((int) new MiddleSquare(1,5).generateNi());
				System.out.println("Calificacion de la mesa: " + tableName + "Plato : " + calification.getPlate().getPlateName() + " Puntaje: " + calification.getScore());
				
				if(countOfClients == 2) {
					Calification calificationTwo = new Calification();
					calificationTwo.setPlate(Restaurant.getInstance().getListOfPlates().get((int) new MiddleSquare(0,6).generateNi()));
					calificationTwo.setScore((int) new MiddleSquare(1,5).generateNi());
					System.out.println("Calificacion de la mesa: " + tableName + "Plato : " + calificationTwo.getPlate().getPlateName() + " Puntaje: " + calificationTwo.getScore());
					Restaurant.getInstance().addCalification(calificationTwo);
					
				} else if (countOfClients == 3) {
					Calification calificationThree = new Calification();
					calificationThree.setPlate(Restaurant.getInstance().getListOfPlates().get((int) new MiddleSquare(0,6).generateNi()));
					calificationThree.setScore((int) new MiddleSquare(1,5).generateNi());
					System.out.println("Calificacion de la mesa: " + tableName + "Plato : " + calificationThree.getPlate().getPlateName() + " Puntaje: " + calificationThree.getScore());
					Restaurant.getInstance().addCalification(calificationThree);
				}
				freeTable = false;
				Restaurant.getInstance().addCalification(calification);
			} 
			ID_BASE = 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToTable() {
		int ramdomTable = (int) new MiddleSquare(1,7).generateNi();
		for (int i = 0; i < ramdomTable; i++) {
			Restaurant.getRestaurant().increaseClients();
			listClients.add(new Client(Restaurant.getInstance().getListTimesToEat().get(5)));
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
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	
}