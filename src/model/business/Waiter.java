package model.business;

public class Waiter extends Thread {
	
	private int id;
	private int ID_BASE=1;
	private double timeToServe;
	private boolean service;
	
	public Waiter() {
		this.id = ID_BASE++;
		this.timeToServe = 10000;
		service = false;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			service = true;
			while (service) {
				System.out.println("El mesero "+ id +" esta sirviendo...");
				Thread.sleep((long) 100);
				break;
			}
			service = false;	
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setTimeToServe(Double timeToServe) {
		this.timeToServe = timeToServe;
	}

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

	public double getTimeToServe() {
		return timeToServe;
	}

	public void setTimeToServe(double timeToServe) {
		this.timeToServe = timeToServe;
	}
}