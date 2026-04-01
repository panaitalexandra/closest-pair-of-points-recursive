import java.util.*;

public class Algorithm {
    public static List<Point> findClosestPair(List<Point> points) {
        if (points.size() < 2) return new ArrayList<>();

        // Pasul 1: Ordonare dupa abscisa
        List<Point> pointsX = new ArrayList<>(points);
        Collections.sort(pointsX);

        return solve(pointsX);
    }

    private static List<Point> solve(List<Point> pointsX) {
        int n = pointsX.size();
        if (n <= 3) return bruteForce(pointsX);

        // Pasul 2: Divizare
        int mid = n / 2;
        Point midPoint = pointsX.get(mid);
        List<Point> s1 = new ArrayList<>(pointsX.subList(0, mid));
        List<Point> s2 = new ArrayList<>(pointsX.subList(mid, n));

        // Pasul 3: Recursivitate
        List<Point> res1 = solve(s1);
        List<Point> res2 = solve(s2);

        double d1 = res1.get(0).dist(res1.get(1));
        double d2 = res2.get(0).dist(res2.get(1));
        double deltaStar = Math.min(d1, d2);
        List<Point> closestPair = (d1 < d2) ? res1 : res2;

        // Pasul 4: Banda verticala (Strip)
        List<Point> strip = new ArrayList<>();
        for (Point p : pointsX)
            if (Math.abs(p.x - midPoint.x) < deltaStar)
                strip.add(p);

        // Comparare in banda (|yA - yB| < deltaStar)
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++)
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < deltaStar; j++) {
                double d = strip.get(i).dist(strip.get(j));
                if (d < deltaStar) {
                    deltaStar = d;
                    closestPair = Arrays.asList(strip.get(i), strip.get(j));
                }
            }
        return closestPair;
    }

    private static List<Point> bruteForce(List<Point> pts) {
        double min = Double.MAX_VALUE;
        List<Point> pair = new ArrayList<>();
        for (int i = 0; i < pts.size(); i++)
            for (int j = i + 1; j < pts.size(); j++) {
                double d = pts.get(i).dist(pts.get(j));
                if (d < min) {
                    min = d;
                    pair = Arrays.asList(pts.get(i), pts.get(j));
                }
            }
        return pair;
    }
}