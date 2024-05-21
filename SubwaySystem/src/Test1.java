import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        SubwaySystem subwaySystem = new SubwaySystem();
        try (BufferedReader br = new BufferedReader(
                new FileReader("\"C:\\Users\\35111\\Downloads\\大作业\\subway.txt\""))) {
            String line;
            String currentLine = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("号线站点间距")) {
                    currentLine = line.split("号线站点间距")[0];
                    subwaySystem.addLine(currentLine);
                } else if (line.contains("---") || line.contains("—")) {
                    String separator = line.contains("---") ? "---" : "—";
                    String[] parts = line.split(separator);
                    String station1 = parts[0].trim();

                    String station2 = parts[1].split("\t")[0].trim();
                    double distance = Double.parseDouble(parts[1].split("\t")[1].trim());
                    subwaySystem.addStation(currentLine, station1, distance);
                    subwaySystem.addStation(currentLine, station2, distance);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(subwaySystem);
        Set<String> transferStations = subwaySystem.getTransferStations();
        System.out.println("Transfer Stations:");
        for (String station : transferStations) {
            System.out.println(station);
        }
        List<String> l1 = subwaySystem.findStationsWithinDistance("洪山广场", 4);
        System.out.println(l1);
        subwaySystem.test3();
    }
}
