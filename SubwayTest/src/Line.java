import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    private String name;
    private List<Station> stations;
    private Map<Station, Integer> stationDistances; // 存储站点之间的距离

    public Line(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
        this.stationDistances = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addStation(Station station, int distanceToPrevious) {
        if (!stations.isEmpty()) {
            stationDistances.put(stations.get(stations.size() - 1), distanceToPrevious);
        }
        stations.add(station);
    }

    public int getDistanceBetweenStations(Station start, Station end) {
        if (start == end) {
            return 0;
        }
        int totalDistance = 0;
        for (int i = stations.indexOf(start); i < stations.indexOf(end); i++) {
            totalDistance += stationDistances.get(stations.get(i));
        }
        return totalDistance;
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", stations=" + stations +
                ", stationDistances=" + stationDistances +
                '}';
    }
}
