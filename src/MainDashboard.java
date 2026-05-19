import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainDashboard {
    private JFrame frame;
    private SupplementDatabase db;

    public MainDashboard(SupplementDatabase db) {
        this.db = db;
        this.frame = new JFrame("Katalog Suplementů");
    }

    public void showDashboard() {
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JButton aiButton = new JButton("Zeptat se AI poradce");
        aiButton.setPreferredSize(new Dimension(300, 50));
        topPanel.add(aiButton);
        aiButton.addActionListener(e -> new AIWindow().showWindow());
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 3, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        for (String category : db.getCategories()) {
            JButton catButton = new JButton(category);
            catButton.setPreferredSize(new Dimension(100, 80));

            catButton.addActionListener(e -> {
                List<AbstractSupplement> items = db.getSupplementsByCategory(category);

                CatagoryWindow catWin = new CatagoryWindow(category, items);
                catWin.showWindow();
            });

            centerPanel.add(catButton);
        }

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}