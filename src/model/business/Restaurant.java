package model.business;

import java.util.ArrayList;
import java.util.List;

import model.utilities.ExceptionPlate;
import model.utilities.MiddleSquare;

public class Restaurant {

    private List<Day> daysInSimulation;
    MiddleSquare pseudo;
    private List<Plate> listOfPlates;
    private List<Calification> ratings;
    private List<Integer> listDataHours;
    private List<Integer> listDataClients;
    private List<Double> listTimesToEat;
    private List<Double> listTimesWaiters;

    private List<Statistics> statistics;

    private List<Table> tables;
    private List<Waiter> listWaiters;
    private static Restaurant restaurant = new Restaurant();
    private MiddleSquare hoursRandom;
    private MiddleSquare clientsRandom;
    private MiddleSquare timesToEatRandom;
    private MiddleSquare timesWaitersRandom;
    private int countClients = 0;
    private int totalSales;
    private double totalTips;
    private double totalProfits;

    public static Restaurant getInstance() {
        return restaurant;
    }

    private Restaurant() {
        initializeRandomValues();
        daysInSimulation = new ArrayList<>();
        listOfPlates = new ArrayList<>();
        ratings = new ArrayList<>();
        listDataHours = new ArrayList<>();
        listDataClients = new ArrayList<>();
        listTimesToEat = new ArrayList<>();
        listTimesWaiters = new ArrayList<>();
        listWaiters = new ArrayList<>();
        tables = new ArrayList<>();
        statistics = new ArrayList<>();
    }

    private void initializeRandomValues() {
        hoursRandom = new MiddleSquare(4,7);
        clientsRandom = new MiddleSquare(45, 65);
        timesToEatRandom = new MiddleSquare(10, 15);
        timesWaitersRandom = new MiddleSquare(4, 10);
    }

    public void init(int daysOfSimulation,int numberWaiters,int tables) {
        manageValues(daysOfSimulation);
        addPlates();
        addEmployees(numberWaiters);
        addTables(tables);
        generateDays(daysOfSimulation);
        startSimulation();
    }

    private void addEmployees(int numberWaiters) {
        for (int i = 0; i < numberWaiters; i++) {
            listWaiters.add(new Waiter());
        }
    }

    private void addTables(int numberTables) {
        for (int i = 0; i < 5; i++) {
            tables.add(new Table());
        }
    }
    private void addProfit(int profit) {
        totalProfits += profit;
    }


    private void generateReport() {
        System.out.println("\n REPORTE FINAL \n");
        int cantOfBandeja = 0, cantOfCuchuco = 0, cantOfPaella = 0, cantOfArroz = 0;
        double totalRatingBandeja = 0, totalRatingCuchuco = 0, totalRatingPaella = 0, totalRatingArroz = 0;

        for (Calification calification : ratings) {
            try {
                switch (calification.getPlate().getIdPlate()) {
                    case 0 -> {
                        cantOfBandeja++;
                        totalRatingBandeja += calification.getScore();
                    }
                    case 1 -> {
                        cantOfCuchuco++;
                        totalRatingCuchuco += calification.getScore();
                    }
                    case 2 -> {
                        cantOfPaella++;
                        totalRatingPaella += calification.getScore();
                    }
                    case 3 -> {
                        cantOfArroz++;
                        totalRatingArroz += calification.getScore();
                    }
                }
            } catch (Exception e) {

            }

        }

        double netWorthBandeja = (cantOfBandeja * 18000) * 0.25;
        double netWorthCuchuco = (cantOfCuchuco * 12000) * 0.25;
        double netWorthValencia = (cantOfPaella * 20000) * 0.25;
        double netWorthArroz = (cantOfArroz * 17000) * 0.25;

        System.out.println("Bandeja Paisa: " + cantOfBandeja + "\t Calificaci�n: " + totalRatingBandeja / cantOfBandeja);
        System.out.println("Cuchuco de Trigo con Espinazo : " + cantOfCuchuco + "\t Calificaci�n: " + totalRatingCuchuco / cantOfCuchuco);
        System.out.println("Paella a la Valenciana: " + cantOfPaella + "\t Calificaci�n:  " + totalRatingPaella / cantOfPaella);
        System.out.println("Arroz con Pollo: " + cantOfArroz + "\t Calificaci�n:  " + totalRatingArroz / cantOfArroz);
        System.out.println();
        System.out.println("Bandeja: " + netWorthBandeja + " Cuchuco: " + netWorthCuchuco + " Valencia: " + netWorthValencia + " Arroz: " + netWorthArroz);
        if (netWorthBandeja > netWorthCuchuco && netWorthBandeja > netWorthValencia && netWorthBandeja > netWorthArroz) {
            System.out.println("El plato con mayor utilidad es la Bandeja Paisa con: " + netWorthBandeja);
        } else if (netWorthCuchuco > netWorthValencia && netWorthCuchuco > netWorthArroz) {
            System.out.println("El plato con mayor utilidad es el Cuchuco con: " + netWorthCuchuco);
        } else if (netWorthValencia > netWorthArroz) {
            System.out.println("El plato con mayor utilidad es la Paella con: " + netWorthValencia);
        } else {
            System.out.println("El plato con mayor utilidad es la Arroz con pollo con: " + netWorthArroz);
        }
        System.out.println();
    }

    public void addCalification(Calification calification) {
        ratings.add(calification);
    }

    private void addPlates() {
        listOfPlates.add(new Plate(0, "Filete de ternera con salsa de champiñones",42000));
        listOfPlates.add(new Plate(1, "Salmón a la parrilla con salsa de limón",37000));
        listOfPlates.add(new Plate(2, "Pollo al curry con arroz basmati",26000));
        listOfPlates.add(new Plate(3, "Lasaña de carne",31000));
        listOfPlates.add(new Plate(4, "Risotto de champiñones y parmesano",38000));
        listOfPlates.add(new Plate(5, "Costillas de cerdo BBQ con puré de papas",36000));
    }

    private void startSimulation() {
        System.out.println("Dias simulados: " + daysInSimulation.size() + "\n");
        for (Day day : daysInSimulation) {
            System.out.println("Dia No: " + day.getId());
            System.out.println("Horas trabajadas: " + day.getWorkedHours());
            System.out.println("Clientes en el dia: " + day.getTotalClients());
            for (Table table : tables) {
                table.start();
            }
        }
        generateReport();
    }

    private void generateDays(int daysOfSimulation) {
        int days = 0;
        while (days < daysOfSimulation) {
            daysInSimulation.add(new Day(listDataHours.get(days), listDataClients.get(days)));;
            days ++;
        }

    }
    public void manageValues(int daysOfSimulation) {
        for (int i = 0; daysOfSimulation > i; i++) {
            listDataHours.add((int) hoursRandom.generateNi());
            listDataClients.add((int) clientsRandom.generateNi());
            listTimesToEat.add(timesToEatRandom.generateNi());
            listTimesWaiters.add(timesWaitersRandom.generateNi());
        }
    }

    public void addNewSale(Double price){
        this.totalSales += price;
    }

    public void addNewTip(Double tip){
        this.totalTips += tip;
    }

    public Plate probabilityChoosePlate() {
        double random = new MiddleSquare(0, 1).generateNi();
        Plate plate = new Plate();
        if (random > 0 && random <= 1.0 / 5.0) {
            plate = listOfPlates.get(0);
        } else if (random > 1.0 / 5.0 && random <= 3.0 / 8.0) {
            plate = listOfPlates.get(1);
        } else if (random > 3.0 / 8.0 && random <= 1.0 / 2.0) {
            plate = listOfPlates.get(2);
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 8.0) {
            plate = listOfPlates.get(3);
        } else if (random > 5.0 / 8.0 && random <= 7.0 / 8.0) {
            plate = listOfPlates.get(4);
        } else if (random > 7.0 / 8.0 && random <= 1.0) {
            plate = listOfPlates.get(5);
        }
        return plate;
    }

    public double calculateTotalTip(){
        for (Table table: tables) {
            this.totalTips += table.getAmountTip();
        }
        return totalTips;
    }

    public double obtainTimeToPrepare(int idPlate) throws ExceptionPlate {
        double timePlate = 0;
        switch (idPlate){
            case 1:
                timePlate = obtainTimeSteak();
                break;
            case 2:
                timePlate = obtainTimeChick();
                break;
            case 3:
                timePlate = obtainTimeSalmon();
                break;
            case 4:
                timePlate = obtainTimeMeat();
                break;
            case 5:
                timePlate = obtainTimeMushrooms();
                break;
            case 6:
                timePlate = obtainTimeRib();
                break;
            default:
                throw new ExceptionPlate("El plato no se encuentra en el menú");
        }
        return timePlate;
    }

    public double obtainTimeSteak(){
        int timeSteak = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 6.0) {
            timeSteak = 8;
        } else if (random > 1.0 / 6.0 && random <= 1.0 / 2.0) {
            timeSteak = 10;
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 6.0) {
            timeSteak = 12;
        } else if (random > 5.0 / 6.0 && random <= 1) {
            timeSteak = 15;
        }
        return timeSteak;
    }

    public double obtainTimeChick(){
        int timeChick = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 6.0) {
            timeChick = 12;
        } else if (random > 1.0 / 6.0 && random <= 1.0 / 2.0) {
            timeChick = 16;
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 6.0) {
            timeChick = 20;
        } else if (random > 5.0 / 6.0 && random <= 1) {
            timeChick = 22;
        }
        return timeChick;
    }

    public double obtainTimeSalmon(){
        int timeSalmon = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 6.0) {
            timeSalmon = 8;
        } else if (random > 1.0 / 6.0 && random <= 1.0 / 2.0) {
            timeSalmon = 16;
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 6.0) {
            timeSalmon = 12;
        } else if (random > 5.0 / 6.0 && random <= 1) {
            timeSalmon = 15;
        }
        return timeSalmon;
    }

    public double obtainTimeMeat(){
        int timeMeat = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 3.0) {
            timeMeat = 15;
        } else if (random > 1.0 / 3.0 && random <= 2.0 / 3.0) {
            timeMeat = 20;
        } else if (random > 2.0 / 3.0 && random <= 1) {
            timeMeat = 25;
        }
        return timeMeat;
    }

    public double obtainTimeMushrooms(){
        int timeMushrooms = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 6.0) {
            timeMushrooms = 17;
        } else if (random > 1.0 / 6.0 && random <= 1.0 / 2.0) {
            timeMushrooms = 22;
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 6.0) {
            timeMushrooms = 25;
        } else if (random > 5.0 / 6.0 && random <= 1) {
            timeMushrooms = 30;
        }
        return timeMushrooms;
    }

    public double obtainTimeRib(){
        int timeRib = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 1.0 / 6.0) {
            timeRib = 14;
        } else if (random > 1.0 / 6.0 && random <= 1.0 / 2.0) {
            timeRib = 28;
        } else if (random > 1.0 / 2.0 && random <= 5.0 / 6.0) {
            timeRib = 22;
        } else if (random > 5.0 / 6.0 && random <= 1) {
            timeRib = 25;
        }
        return timeRib;
    }


    public double obtainTip(){
        int tip = 0;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 4.0 / 8.0) {
            tip = 0;
        } else if (random > 4.0 / 8.0 && random <= 5.0 / 8.0) {
            tip = 5000;
        } else if (random > 5.0 / 8.0 && random <= 7.0 / 8.0) {
            tip = 10000;
        } else if (random > 7.0 / 8.0 && random <= 1) {
            tip = 15000;
        }
        return tip;
    }

    public boolean choosePlateByMinorPrice(){
        boolean isChosen = false;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 8.0 / 9.0) {
            isChosen = true;
        } else if (random > 8.0 / 9.0 && random <= 1) {
            isChosen = false;
        }
        return isChosen;
    }
    public boolean choosePlateByMajorPrice(){
        boolean isChosen = false;
        double random = new MiddleSquare(0, 1).generateNi();
        if (random > 0 && random <= 2.0 / 3.0) {
            isChosen = true;
        } else if (random > 2.0 / 3.0 && random <= 1) {
            isChosen = false;
        }
        return isChosen;
    }
    public double obtainAverageRating(int idPlate){
        double total = 0;
        int sizeRatings = listOfPlates.get(idPlate).getRatings().size();
        for (int i = 0; i < sizeRatings ; i++) {
            total += listOfPlates.get(idPlate).getRatings().get(i);
        }
        return  total / sizeRatings;
    }

    public double calculateProfits(int idPlate) {
        double total = 0;
        for (int i = 0; i < listOfPlates.size(); i++) {
            if (listOfPlates.get(i).getIdPlate() == idPlate) {
                total = listOfPlates.get(i).getAmountPurchased() * listOfPlates.get(i).getPrice();
            }
        }
        return total * 0.25;
    }

    public double obtainAmount(int idPlate) {
        double total = 0;
        for (int i = 0; i < listOfPlates.size(); i++) {
            if (listOfPlates.get(i).getIdPlate() == idPlate) {
                total = listOfPlates.get(i).getAmountPurchased();
            }
        }
        return total;
    }

    public void calculateStatistics(){
        for (int i = 0; i < listOfPlates.size(); i++) {
            statistics.add(new Statistics(listOfPlates.get(i).getPlateName(),obtainAmount(i),
                    obtainAverageRating(i),calculateProfits(i)));
        }
    }


    public void addWaiter(Waiter waiter){
        listWaiters.add(waiter);
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void increaseClients(){
        this.countClients++;
    }
    public List<Day> getDaysInSimulation() {
        return daysInSimulation;
    }
    public void setDaysInSimulation(List<Day> daysInSimulation) {
        this.daysInSimulation = daysInSimulation;
    }
    public MiddleSquare getPseudo() {
        return pseudo;
    }
    public void setPseudo(MiddleSquare pseudo) {
        this.pseudo = pseudo;
    }

    public List<Plate> getListOfPlates() {
        return listOfPlates;
    }

    public void setListOfPlates(List<Plate> listOfPlates) {
        this.listOfPlates = listOfPlates;
    }

    public List<Calification> getRatings() {
        return ratings;
    }

    public void setRatings(List<Calification> ratings) {
        this.ratings = ratings;
    }

    public List<Integer> getListDataHours() {
        return listDataHours;
    }

    public void setListDataHours(List<Integer> listDataHours) {
        this.listDataHours = listDataHours;
    }

    public List<Integer> getListDataClients() {
        return listDataClients;
    }

    public void setListDataClients(List<Integer> listDataClients) {
        this.listDataClients = listDataClients;
    }

    public List<Double> getListTimesToEat() {
        return listTimesToEat;
    }

    public void setListTimesToEat(List<Double> listTimesToEat) {
        this.listTimesToEat = listTimesToEat;
    }

    public List<Double> getListTimesWaiters() {
        return listTimesWaiters;
    }

    public void setListTimesWaiters(List<Double> listTimesWaiters) {
        this.listTimesWaiters = listTimesWaiters;
    }

    public static Restaurant getRestaurant() {
        return restaurant;
    }

    public static void setRestaurant(Restaurant restaurant) {
        Restaurant.restaurant = restaurant;
    }

    public MiddleSquare getHoursRandom() {
        return hoursRandom;
    }

    public void setHoursRandom(MiddleSquare hoursRandom) {
        this.hoursRandom = hoursRandom;
    }

    public MiddleSquare getClientsRandom() {
        return clientsRandom;
    }

    public void setClientsRandom(MiddleSquare clientsRandom) {
        this.clientsRandom = clientsRandom;
    }

    public MiddleSquare getTimesToEatRandom() {
        return timesToEatRandom;
    }

    public void setTimesToEatRandom(MiddleSquare timesToEatRandom) {
        this.timesToEatRandom = timesToEatRandom;
    }

    public MiddleSquare getTimesWaitersRandom() {
        return timesWaitersRandom;
    }

    public void setTimesWaitersRandom(MiddleSquare timesWaitersRandom) {
        this.timesWaitersRandom = timesWaitersRandom;
    }

    public int getCountClients() {
        return countClients;
    }

    public void setCountClients(int countClients) {
        this.countClients = countClients;
    }

    public List<Waiter> getListWaiters() {
        return listWaiters;
    }

    public void setListWaiters(List<Waiter> listWaiters) {
        this.listWaiters = listWaiters;
    }

    public double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(double totalTips) {
        this.totalTips = totalTips;
    }
}