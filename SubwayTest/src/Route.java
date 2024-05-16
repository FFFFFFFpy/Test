import java.util.ArrayList;
import java.util.List;

public class Route {
    Station start;
    Station end;
    List<Station> path;

    Route(Station start, Station end) {
        this.end = end;
        this.start = start;
        this.path = new ArrayList<>();
    }
}
