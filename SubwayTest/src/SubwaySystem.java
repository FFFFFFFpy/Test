import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubwaySystem {
    Map<String, Station> stationMap;
    Map<String, Line> lineMap;

    SubwaySystem(String filePath) throws IOException {
        this.stationMap = new HashMap<>();
        this.lineMap = new HashMap<>();

        // 从文件中读取数据
        readFromFile(filePath);
    }

    void readFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 3) {
                String[] stationNames = parts[0].split("---");
                double distance = Double.parseDouble(parts[2]);

                // 创建或获取站点
                Station fromStation = this.stationMap.computeIfAbsent(stationNames[0], name -> new Station(name));
                Station toStation = this.stationMap.computeIfAbsent(stationNames[1], name -> new Station(name));

                // 创建或获取线路
                Line currentLine = this.lineMap.computeIfAbsent(stationNames[0] + "-" + stationNames[1], name -> new Line(name));
                currentLine.addStation(fromStation, new HashMap<>(Map.of(toStation, distance)));
                currentLine.addStation(toStation, new HashMap<>(Map.of(fromStation, distance)));

                // 添加站点到线路
                fromStation.addLine(currentLine);
                toStation.addLine(currentLine);
            }
        }
        reader.close();
    }
