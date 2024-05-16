import java.util.ArrayList;
import java.util.List;

public class Station {
    String name;
    List<Line> lines;

    Station(String name) {
        this.name = name;
        this.lines = new ArrayList<>();
    }

    void addLine(Line line) {
        this.lines.add(line);
    }
}