package entities;

import java.util.ArrayList;

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
		this.listClients = new ArrayList<Client>();
	}

	@Override
	public void run() {
		Waiter waiter1 = new Waiter(1, 0);
		Waiter waiter2 = new Waiter(2, 0);
		waiter1.setService(false);
		waiter2.setService(false);
		super.run();
		try {
			freeTable = true;
			for (int i = 0; i < 30; i++) {
				Calification calification = new Calification();
				int randomPlateOne = 0;
				int randomPlateTwo = 0;
				int randomPlateThree = 0;
				while(randomPlateOne == randomPlateTwo || randomPlateTwo == randomPlateThree || randomPlateThree == randomPlateOne  ){
					 randomPlateOne = (int) (Math.random() *(4 -  0) + 0);
					 randomPlateTwo = (int) (Math.random() *(4 -  0) + 0);
					 randomPlateThree = (int) (Math.random() *(4 -  0) + 0);
				}
				this.addToTable();
				this.startEat();
				calification.setPlate(Restaurant.getInstance().getPlates().get(randomPlateOne));
				int countOfClients = listClients.size();
				listClients.removeAll(listClients);
				//clientes 
				waiter1.setTimeToServe(Integer.parseInt(Restaurant.getInstance().getListTimesWaiters().get((int) (Math.random() * 2999) + 1)));
				if(!waiter2.isService()) {  
					waiter1.setTimeToServe(Integer.parseInt(Restaurant.getInstance().getListTimesWaiters().get((int) (Math.random() * 2999) + 1)));
					this.setWaiter(waiter1);
					waiter1.setService(true);
					waiter1.run();
					waiter1.setService(false);
				}
				if(!waiter1.isService()) {
					waiter2.setTimeToServe(Integer.parseInt(Restaurant.getInstance().getListTimesWaiters().get((int) (Math.random() * 2999) + 1)));
					waiter2.setService(true);
					this.setWaiter(waiter2);
					waiter2.run();
					waiter2.setService(false);
				}
				Thread.sleep(this.getTime()+100);
				calification.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
				System.out.println("Calificacion de la mesa: " + tableName + "Plato : " + calification.getPlate().getPlateName() + " Puntaje: " + calification.getScore());
				
				if(countOfClients == 2) {
					Calification calificationTwo = new Calification();
					calificationTwo.setPlate(Restaurant.getInstance().getPlates().get(randomPlateTwo));
					calificationTwo.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
					System.out.println("Calificacion de la mesa: " + tableName + "Plato : " + calificationTwo.getPlate().getPlateName() + " Puntaje: " + calificationTwo.getScore());
					Restaurant.getInstance().addCalification(calificationTwo);
					
				} else if (countOfClients == 3) {
					Calification calificationThree = new Calification();
					calificationThree.setPlate(Restaurant.getInstance().getPlates().get(randomPlateThree));
					calificationThree.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
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
		for (int i = 0; i < (int)(Math.random()*2 + 1); i++) {
			listClients.add(new Client(Integer.parseInt(Restaurant.getInstance().getListTimes().get((int) (Math.random() * 2000) + 1))));
		}
		System.out.println("Mesa Numero: "+idTable+" con "+ listClients.size()+ " clientes");
	}
	
	public int getTime() {
		double aux = 0;
		for (int i = 0; i < listClients.size(); i++) {
			aux += listClients.get(i).getTime(); 
		}
		return (int) aux;
	}

	public void startEat() {
		for (int i = 0; i < listClients.size(); i++) {
			listClients.get(i).start();
			System.out.println(listClients.get(i).toString());
		}
	}

	public void remove() {
		for (int i = 0; i < listClients.size(); i++) { 
			if (listClients.get(i).isEating() == false) {
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