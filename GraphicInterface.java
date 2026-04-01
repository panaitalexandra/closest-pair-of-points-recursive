import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GraphicInterface extends JFrame {
    private JTextArea logArea;
    private static final Color COLOR_PASTEL_PINK = new Color(214, 228, 227);
    private static final Color COLOR_ROSE_ACCENT = new Color(50, 151, 147);

    public GraphicInterface() {
        setTitle("✨Closest Pair✨");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        Drawing drawingArea = new Drawing(msg -> logArea.append(msg + "\n"));
        drawingArea.setBorder(new LineBorder(COLOR_PASTEL_PINK, 5, true));

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300, 750));
        sidebar.setBackground(COLOR_PASTEL_PINK);
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton btnCalc = createGirlyButton("FIND CLOSEST PAIR", COLOR_ROSE_ACCENT);
        btnCalc.addActionListener(e -> drawingArea.calculate());

        JButton btnReset = createGirlyButton("RESET CANVAS", new Color(235, 235, 235));
        btnReset.addActionListener(e -> { drawingArea.reset(); logArea.setText(""); });

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Activity Log"));

        sidebar.add(btnCalc);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(btnReset);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(logScroll);

        add(sidebar, BorderLayout.WEST);
        add(drawingArea, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createGirlyButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(280, 50));
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphicInterface::new);
    }
}