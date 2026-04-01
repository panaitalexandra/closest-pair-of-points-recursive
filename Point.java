import java.util.Objects;

public class Point implements Comparable<Point> {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Distanta euclidiana intre doua puncte
    public double dist(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    @Override
    public int compareTo(Point o) {
        if (Math.abs(this.x - o.x) > 1e-9) {
            return Double.compare(this.x, o.x);
        }
        return Double.compare(this.y, o.y);
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
}