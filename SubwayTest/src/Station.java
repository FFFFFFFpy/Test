import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Station {
    String name;
    Set<Line> lines;

    Station(String name) {
        this.name = name;
        this.lines = new HashSet<>();
    }

    void addLine(Line line) {
        this.lines.add(line);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Station station = (Station) obj;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
