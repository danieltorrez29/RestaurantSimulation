package model.business;

import model.utilities.MiddleSquare;

public class Day {
	private int id;
    private int workedHours;
    private int totalClients;

    public static int ID_BASE = 1;

    private MiddleSquare random;

    public Day(int workedHours, int totalClients) {
        this.id = ID_BASE++;
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
