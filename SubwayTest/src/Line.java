import java.util.ArrayList;
import java.util.List;

public class Line {
    String name;
    List<Station> stations;

    Line(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
    }

    void addStation(Station station) {
        this.stations.add(station);
    }

}
