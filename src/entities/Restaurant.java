package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistencee.FileManager;

public class Restaurant {

	private List<Day> daysInSimulation;
	private List<Plate> listOfPlates;
	private List<Calification> califications = new ArrayList<>();
	private List<String> listDataHours = new ArrayList<String>();
	private List<String> listDataClients = new ArrayList<String>();
	private List<String> listTimes = new ArrayList<String>();
	private List<String> listTimesWaiters = new ArrayList<String>();
	private static Restaurant restaurant = new Restaurant();

	public static Restaurant getInstance() {
		return restaurant;
	}

	private Restaurant() {
	}

	public void start(int hoursToSimulate) {
		manageFile();
		addPlates();
		generateDays(hoursToSimulate);
		startSimulation();
	}

	private void generateReport() {
		System.out.println("\n REPORTE FINAL \n" );
		int cantOfBandeja = 0, cantOfCuchuco = 0, cantOfPaella = 0, cantOfArroz = 0;
		double totalRatingBandeja = 0, totalRatingCuchuco = 0, totalRatingPaella = 0, totalRatingArroz = 0;
		for (Calification calification : califications) {
			try {
				switch (calification.getPlate().getIdPlate()) {
				case 0:
					cantOfBandeja++;
					totalRatingBandeja += calification.getScore();
					break;
				case 1:
					cantOfCuchuco++;
					totalRatingCuchuco += calification.getScore();
					break;
				case 2:
					cantOfPaella++;
					totalRatingPaella += calification.getScore();
					break;
				case 3:
					cantOfArroz++;
					totalRatingArroz += calification.getScore();
					break;
				}
			} catch (Exception e) {
			}

		}
		
		double  netWorthBandeja = (cantOfBandeja*18000)*0.25;
		double  netWorthCuchuco = (cantOfCuchuco*12000)*0.25;
		double 	netWorthValencia = (cantOfPaella*20000)*0.25;
		double 	netWorthArroz = (cantOfArroz*17000)*0.25;
		
		System.out.println("Bandeja Paisa: " + cantOfBandeja + "\t Calificación: " + totalRatingBandeja / cantOfBandeja);
		System.out.println("Cuchuco de Trigo con Espinazo : " + cantOfCuchuco + "\t Calificación: " + totalRatingCuchuco / cantOfCuchuco);
		System.out.println("Paella a la Valenciana: " + cantOfPaella + "\t Calificación:  " + totalRatingPaella / cantOfPaella);
		System.out.println("Arroz con Pollo: " + cantOfArroz + "\t Calificación:  " + totalRatingArroz / cantOfArroz);
		System.out.println();
		System.out.println("Bandeja: "+netWorthBandeja+ " Cuchuco: " + netWorthCuchuco + " Valencia: "+ netWorthValencia+ " Arroz: " + netWorthArroz);
		if(netWorthBandeja > netWorthCuchuco && netWorthBandeja > netWorthValencia && netWorthBandeja > netWorthArroz ) {
			System.out.println("El plato con mayor utilidad es la Bandeja Paisa con: "+ netWorthBandeja);
		} else if (netWorthCuchuco >  netWorthValencia && netWorthCuchuco >  netWorthArroz ) {
			System.out.println("El plato con mayor utilidad es el Cuchuco con: "+ netWorthCuchuco);
		} else if (netWorthValencia > netWorthArroz) {
			System.out.println("El plato con mayor utilidad es la Paella con: "+ netWorthValencia);
		} else {
			System.out.println("El plato con mayor utilidad es la Arroz con pollo con: "+ netWorthArroz);
		}
		System.out.println();
	}

	public void addCalification(Calification calification) {
		califications.add(calification);
	}

	private void addPlates() {
		listOfPlates = new ArrayList<>();
		listOfPlates.add(new Plate(0, "Bandeja Paisa", Math.random()));
		listOfPlates.add(new Plate(1, "Paella a la Valenciana", Math.random()));
		listOfPlates.add(new Plate(2, "Arroz con Pollo", Math.random()));
		listOfPlates.add(new Plate(3, "Cuchuco de Trigo con Espinazo", Math.random()));
	}

	private void startSimulation() {
		System.out.println("Dias simulados: " + daysInSimulation.size());
		System.out.println();
		for (Day day : daysInSimulation) {
			System.out.println("Dia No: " + day.getId());
			System.out.println("Horas trabajadas: " + day.getWorkedHours());
			System.out.println("Clientes en el dia: " + day.getTotalClients());
			Table tableOne = new Table("Mesa 1 ");
			Table tableTwo = new Table("Mesa 2 ");
			Table tableThree = new Table("Mesa 3 ");
			Table tableFour = new Table("Mesa 4 ");
			Table tableFive = new Table("Mesa 5 ");

			tableOne.start();
			tableTwo.start();
			tableThree.start();
			tableFour.start();
			tableFive.start();
			while (tableOne.isAlive() || tableTwo.isAlive() || tableThree.isAlive() || tableFour.isAlive() || tableFive.isAlive()) {
			}
		}
		generateReport();
	}

	private void generateDays(int hoursToSimulate) {
		daysInSimulation = new ArrayList<>();
		int auxHours = 0;
		int auxDay = 1;
		List<Double> hours = generateHours();
		List<Double> totalOfConsumers = generateClients();
		while (auxHours < hoursToSimulate) {
			daysInSimulation.add(new Day(auxDay, (int) (Math.round(hours.get(auxDay))), (int) (Math.round(totalOfConsumers.get(auxDay)))));
			auxHours += (int) (Math.round(hours.get(auxDay)));
			auxDay++;
		}
	}

	public void manageFile() {
		try {
			List<String> file = FileManager.readFileHour();
			for (int i = 0; i < file.size(); i++) {
				listDataHours.add(createHour(FileManager.splitLine(file.get(i), ",")));
			}

			List<String> fileTwo = FileManager.readFileClients();
			for (int i = 0; i < fileTwo.size(); i++) {
				listDataClients.add(numberOfClient(FileManager.splitLine(fileTwo.get(i), ",")));
			}

			List<String> fileThree = FileManager.readFileTimeToEat();
			for (int i = 0; i < fileThree.size(); i++) {
				listTimes.add(timeToEat(FileManager.splitLine(fileThree.get(i), ",")));
			}
			List<String> fileFour = FileManager.readFileTimeWaiter();
			for (int i = 0; i < fileFour.size(); i++) {
				listTimesWaiters.add(getListWaiters(FileManager.splitLine(fileThree.get(i), ",")));
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public String getListWaiters(String []in) {
		return (in[0]);
	}
	
	public String timeToEat(String []in) {
		return (in[0]);
	}

	public String createHour(String []in) {
		return (in[0]);
	}

	private ArrayList<Double> generateHours() {		
		ArrayList<Double> hours = new ArrayList<Double>();
		for (int i = 0; i < listDataHours.size(); i++) {
			hours.add(Double.parseDouble(listDataHours.get(i)));
		}
		return hours;
	}

	public String numberOfClient(String []in) {
		return (in[0]);
	}

	private ArrayList<Double> generateClients() {		
		ArrayList<Double> clients = new ArrayList<Double>();
		for (int i = 0; i < listDataClients.size(); i++) {
			clients.add(Double.parseDouble(listDataClients.get(i)));
		}
		return clients;
	}

	public List<Day> getDays() {
		return daysInSimulation;
	}

	public void setDays(List<Day> days) {
		this.daysInSimulation = days;
	}

	public List<Plate> getPlates() {
		return listOfPlates;
	}

	public void setPlates(List<Plate> plates) {
		this.listOfPlates = plates;
	}

	public List<Calification> getConsumptions() {
		return califications;
	}

	public void setConsumptions(List<Calification> califications) {
		this.califications = califications;
	}

	public List<Plate> getListOfPlates() {
		return listOfPlates;
	}

	public void setListOfPlates(List<Plate> listOfPlates) {
		this.listOfPlates = listOfPlates;
	}

	public List<String> getListData() {
		return listDataHours;
	}

	public void setListData(List<String> listData) {
		this.listDataHours = listData;
	}

	public List<String> getListDataClients() {
		return listDataClients;
	}

	public void setListDataClients(List<String> listDataClients) {
		this.listDataClients = listDataClients;
	}

	public List<String> getListTimes() {
		return listTimes;
	}

	public void setListTimes(List<String> listTimes) {
		this.listTimes = listTimes;
	}

	public List<String> getListTimesWaiters() {
		return listTimesWaiters;
	}

	public void setListTimesWaiters(List<String> listTimesWaiters) {
		this.listTimesWaiters = listTimesWaiters;
	}
}