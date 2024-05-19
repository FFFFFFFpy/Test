import java.util.HashMap;
import java.util.Map;

public class Line {
    String name;
    Map<Station, Map<Station, Double>> stationDistances;

    Line(String name) {
        this.name = name;
        this.stationDistances = new HashMap<>();
    }

    void addStation(Station station, Map<Station, Double> distances) {
        this.stationDistances.put(station, distances);
    }
}
