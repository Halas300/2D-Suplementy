import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatagoryWindow {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> productList;
    private List<AbstractSupplement> supplementsList;
    public CatagoryWindow(String categoryName, List<AbstractSupplement> supplements) {
        this.frame = new JFrame("Kategorie: " + categoryName);
        this.listModel = new DefaultListModel<>();
        this.supplementsList = supplements;
        for (AbstractSupplement supp : supplements) {
            listModel.addElement(supp.getName());
        }

        this.productList = new JList<>(listModel);

        productList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = productList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        AbstractSupplement selectedSupp = supplementsList.get(index);
                        FinalSupplementDetailWindow detail = new FinalSupplementDetailWindow(selectedSupp);
                        detail.showWindow();
                    }
                }
            }
        });
    }

    public void showWindow() {
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Vyberte suplement", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(new JScrollPane(productList), BorderLayout.CENTER);
        JButton backButton = new JButton("Zpět");
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}