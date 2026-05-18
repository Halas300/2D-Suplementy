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