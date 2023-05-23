package persistencee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

	public static List<String> readFileHour() throws IOException{
		List<String> listLines;
		listLines = Files.readAllLines(Paths.get("src/data/hoursToSimulation.csv"));
		return listLines;		
	}
	
	public static List<String> readFileClients() throws IOException{
		List<String> listLines;
		listLines = Files.readAllLines(Paths.get("src/data/clientsToSimulation.csv"));
		return listLines;		
	}
	
	public static List<String> readFileTimeToEat() throws IOException{
		List<String> listLines;
		listLines = Files.readAllLines(Paths.get("src/data/tiemposComida.csv"));
		return listLines;		
	}
	
	public static List<String> readFileTimeWaiter() throws IOException{
		List<String> listLines;
		listLines = Files.readAllLines(Paths.get("src/data/meseros.csv"));
		return listLines;		
	}
	
	public static String[] splitLine(String line, String separator){
		return line.split(separator);
	}

	public double toDouble(String line) {
		return Double.parseDouble(line);
	}

	public void print(List<String> listData) {
		for (int i = 0; i < listData.size(); i++) {
			if (listData.get(i) != null) {
				System.out.println(listData.get(i).toString());
			}
		}
	}
}