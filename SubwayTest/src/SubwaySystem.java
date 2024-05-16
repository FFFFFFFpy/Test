import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwaySystem {
    Map<String, Station> stationMap;
    Map<String, Line> lineMap;
    List<Edge> edges;

    SubwaySystem(String filePath) throws IOException {
        this.stationMap = new HashMap<>();
        this.lineMap = new HashMap<>();
        this.edges = new ArrayList<>();

        // 从文件中读取数据
        readFromFile(filePath);
    }

    void readFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String stationAName = parts[0].trim();
                String stationBName = parts[1].trim();
                int distance = Integer.parseInt(parts[2].trim());

                Station stationA = this.stationMap.computeIfAbsent(stationAName, name -> new Station(name));
                Station stationB = this.stationMap.computeIfAbsent(stationBName, name -> new Station(name));

                Line line = this.lineMap.values().stream()
                        .filter(l -> l.stations.contains(stationA) && l.stations.contains(stationB))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                "Line not found for stations: " + stationAName + " and " + stationBName));

                Edge edge = new Edge(stationA, stationB, distance);
                this.edges.add(edge);
            }
        }
        reader.close();
    }

    // ...其他方法保持不变

}
