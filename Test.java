import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Station {
    private String name;
    private Set<Line> lines;

    public Station(String name) {
        this.name = name;
        this.lines = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public Set<Line> getLines() {
        return lines;
    }
}

class Line {
    private String number;

    public Line(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}

class SubwaySystem {
    private Map<String, Line> linesMap;
    private Map<String, Station> stationsMap;

    public SubwaySystem() {
        linesMap = new HashMap<>();
        stationsMap = new HashMap<>();
    }

    public void buildSystem(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String stationName = parts[0];
                String lineNumber = parts[1];
                double distance = Double.parseDouble(parts[2]);

                Station station = stationsMap.computeIfAbsent(stationName, Station::new);
                Line lineObj = linesMap.computeIfAbsent(lineNumber, Line::new);

                station.addLine(lineObj);
            }
        }
    }

    public Set<Station> getTransferStations() {
        Set<Station> transferStations = new HashSet<>();
        for (Station station : stationsMap.values()) {
            if (station.getLines().size() > 1) {
                transferStations.add(station);
            }
        }
        return transferStations;
    }
}

public class Test {
    public static void main(String[] args) {
        SubwaySystem subwaySystem = new SubwaySystem();
        String filePath = "subway.txt"; // 地铁线路文件路径
        try {
            subwaySystem.buildSystem(filePath);
            Set<Station> transferStations = subwaySystem.getTransferStations();
            System.out.println("所有的地铁中转站：");
            for (Station station : transferStations) {
                List<String> lines = station.getLines().stream().map(Line::getNumber).collect(Collectors.toList());
                System.out.println("<" + station.getName() + "，<" + String.join(",", lines) + ">>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}