import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class Drawing extends JPanel {
    private List<Point> points = new ArrayList<>();
    private List<Point> closestPair = new ArrayList<>();
    private Consumer<String> logger;

    public Drawing(Consumer<String> logger) {
        this.logger = logger;
        setBackground(new Color(250, 250, 252));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    public void calculate() {
        if (points.size() < 2) {
            logger.accept("> Need at least 2 points!");
            return;
        }
        closestPair = Algorithm.findClosestPair(points);
        if (!closestPair.isEmpty()) {
            double d = closestPair.get(0).dist(closestPair.get(1));
            logger.accept(String.format("> Closest distance: %.2f pixels.", d));
            logger.accept("> Points: " + closestPair.get(0) + " and " + closestPair.get(1));
        }
        repaint();
    }

    public void generateRandom(int count) {
        reset();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            points.add(new Point(r.nextInt(getWidth() - 40) + 20, r.nextInt(getHeight() - 40) + 20));
        }
        logger.accept("> Generated " + count + " random points.");
        repaint();
    }

    public void reset() {
        // Corectie: Initializarea listei ca ArrayList permite clear()
        this.points = new ArrayList<>();
        this.closestPair = new ArrayList<>();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenam toate punctele
        g2.setColor(new Color(50, 151, 147));
        for (Point p : points) {
            g2.fillOval((int)p.x - 3, (int)p.y - 3, 6, 6);
        }

        // Evidentiem perechea cea mai apropiata
        if (closestPair.size() == 2) {
            Point p1 = closestPair.get(0);
            Point p2 = closestPair.get(1);

            g2.setColor(new Color(102, 102, 243)); // Roz accent
            g2.setStroke(new BasicStroke(2));
            g2.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);

            g2.fillOval((int)p1.x - 5, (int)p1.y - 5, 10, 10);
            g2.fillOval((int)p2.x - 5, (int)p2.y - 5, 10, 10);
        }
    }
}