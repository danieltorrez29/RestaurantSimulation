package entities;

public class Day {
	private int id;
    private int workedHours;
    private int totalClients;

    public Day(int id, int workedHours, int totalClients) {
        this.id = id;
        this.workedHours = workedHours;
        this.totalClients = totalClients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public int getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }
}
