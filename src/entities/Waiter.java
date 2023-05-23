package entities;

public class Waiter extends Thread {
	
	private int id;
	private int timeToServe;
	private boolean service;
	
	public Waiter(int id, int timeToServe) {
		this.id = id;
		this.timeToServe = timeToServe;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			service = true;
			while (service) {
				System.out.println("El mesero "+ id +" está sirviendo...");
				Thread.sleep(timeToServe);
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
	public int getTimeToServe() {
		return timeToServe;
	}
	public void setTimeToServe(int timeToServe) {
		this.timeToServe = timeToServe;
	}

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}
}