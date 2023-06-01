package model.business;

public class Calification {

	private int id;
	private int score;
	private Plate plate;

	public Calification() {
	}

	public Calification(int id, int score, Plate plate) {
		this.id = id;
		this.score = score;
		this.plate = plate;
	}

	public int getIdConsumption() {
		return id;
	}

	public void setIdConsumption(int idConsumption) {
		this.id = idConsumption;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	@Override
	public String toString() {
		return "Calification{" +"id=" + id +", rating=" + score +", product=" + plate +'}';
	}
}