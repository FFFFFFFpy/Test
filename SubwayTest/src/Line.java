import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    String name;
    List<Station> stations;
    Map<Station, Map<Station, Integer>> stationDistances;

    Line(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
        this.stationDistances = new HashMap<>();
    }

    void addStation(Station station) {
        this.stations.add(station);
    }

    void addDistance(Station from, Station to, int distance) {
        this.stationDistances.computeIfAbsent(from, s -> new HashMap<>()).put(to, distance);
        this.stationDistances.computeIfAbsent(to, s -> new HashMap<>()).put(from, distance);
    }
}
