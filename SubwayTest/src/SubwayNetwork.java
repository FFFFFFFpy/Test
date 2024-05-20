import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
                Line currentLine = this.lineMap.computeIfAbsent(stationNames[0] + "-" + stationNames[1],
                        name -> new Line(name));
                currentLine.addStation(fromStation, new HashMap<>(Map.of(toStation, distance)));
                currentLine.addStation(toStation, new HashMap<>(Map.of(fromStation, distance)));

                // 添加站点到线路
                fromStation.addLine(currentLine);
                toStation.addLine(currentLine);
            }
        }
        reader.close();
    }

    // 1) 识别所有地铁中转站
    Set<Map.Entry<String, Set<String>>> findTransferStations() {
        Set<Map.Entry<String, Set<String>>> transferStations = new HashSet<>();
        for (Station station : stationMap.values()) {
            if (station.lines.size() > 1) {
                transferStations.add(
                        Map.entry(station.name, new HashSet<>(station.lines.stream().map(Line::getName).toList())));
            }
        }
        return transferStations;
    }

    // 2) 输出线路距离小于n的所有站点集合
    Set<Map.Entry<String, Map.Entry<String, Double>>> findNearbyStations(String stationName, double maxDistance) {
        Set<Map.Entry<String, Map.Entry<String, Double>>> nearbyStations = new HashSet<>();
        Station targetStation = stationMap.get(stationName);
        if (targetStation == null) {
            throw new IllegalArgumentException("Invalid station name.");
        }
        for (Line line : targetStation.lines) {
            for (Map.Entry<Station, Double> entry : line.stationDistances.get(targetStation).entrySet()) {
                if (entry.getValue() <= maxDistance) {
                    nearbyStations.add(Map.entry(entry.getKey().name, Map.entry(line.name, entry.getValue())));
                }
            }
        }
        return nearbyStations;
    }

    // 3) 返回连接起点和终点的所有路径
    Set<List<Station>> findAllPaths(String startStationName, String endStationName) {
        Set<List<Station>> paths = new HashSet<>();
        Station startStation = stationMap.get(startStationName);
        Station endStation = stationMap.get(endStationName);
        if (startStation == null || endStation == null) {
            throw new IllegalArgumentException("Invalid station names.");
        }
        Set<Station> visited = new HashSet<>();
        Stack<Station> stack = new Stack<>();
        stack.push(startStation);
        findAllPathsHelper(stack, visited, endStation, paths);
        return paths;
    }
