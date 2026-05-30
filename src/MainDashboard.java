import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 * Třída MainDashboard slouží jako hlavní obrazovka celé aplikace s kategoriemi .
 */
public class MainDashboard {
    private JFrame frame;
    private SupplementDatabase db;

    public MainDashboard(SupplementDatabase db) {
        this.db = db;
        this.frame = new JFrame("Katalog Suplementů");
    }
    /**
     * Hlavní metoda, která poskládá celou obrazovku.
     * Je nastaveno tak, aby okno bylo přes celou obrazovku
     */
    public void showDashboard() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 250));
        /**
         * HPanel pro tlačítko, který volá umělá inteligence.
         * Pokusil jsem se to nastavit tak, aby to vypadalo hezky a moderně (např. ab to nebylo nalepene na kraji, nemělo hnusné rámečky atd.)
         */
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
        JButton aiButton = new JButton("Zeptat se AI poradce");
        aiButton.setPreferredSize(new Dimension(400, 70));
        aiButton.setFont(new Font("Arial", Font.BOLD, 20));
        aiButton.setBackground(new Color(255, 255, 255));
        aiButton.setFocusPainted(false);
        topPanel.add(aiButton);
        /**
         * Akce pro AI tlačítko, které vytvoří AI okno
         */
        aiButton.addActionListener(e -> new AIWindow(db).showWindow());
        frame.add(topPanel, BorderLayout.NORTH);
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        /**
         * Panel, který tvoří velké mezery mezi jednotlivými tlačítky.
         */
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 3, 50, 50));
        /**
         * Cyklus, který z databáze vyrobí obdélníkové tlačítko
         */
        for (String category : db.getCategories()) {
            JButton catButton = new JButton(category);
            catButton.setFont(new Font("Arial", Font.BOLD, 18));
            catButton.setBackground(Color.WHITE);
            catButton.setFocusPainted(false);
            catButton.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
            catButton.setPreferredSize(new Dimension(280, 280));
            /**
             * Přidání obrázku na tlačítko (Text se automaticky zařadí pod obrázek).
             * Pokud se obrázek nenačne, zůstane tam text
             * Obrázek najdu pomocí metody dole.
             */
            String imageFileName = getCategoryImageFileName(category);
            try {
                BufferedImage img = ImageIO.read(new File("images/" + imageFileName));
                Image scaledImg = img.getScaledInstance(280, 140, Image.SCALE_SMOOTH);
                catButton.setIcon(new ImageIcon(scaledImg));
                catButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                catButton.setHorizontalTextPosition(SwingConstants.CENTER);
                catButton.setIconTextGap(25);
            } catch (Exception ex) {
                System.out.println("Obrázek pro kategorii nenačten: " + imageFileName);
            }
            /**
             * Akce pro kliknutí, který vytáhne z databáze seznam produktů a otevře se nové okno.
             */
            catButton.addActionListener(e -> {
                List<AbstractSupplement> items = db.getSupplementsByCategory(category);
                CatagoryWindow catWin = new CatagoryWindow(category, items);
                catWin.showWindow();
            });
            centerPanel.add(catButton);
        }
        wrapperPanel.add(centerPanel);
        frame.add(wrapperPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    /**
     * Metoda, která na základě jména kategorie vrátí název souboru s uloženým obrázkem.
     */
    private String getCategoryImageFileName(String category) {
        switch (category) {
            case "Síla a Budování svalů": return "sila.jpg";
            case "Energie a Předtréninkovky": return "energie.jpg";
            case "Vitamíny a Minerály": return "vitaminy.jpg";
            case "Spánek a Zklidnění": return "spanek.jpg";
            case "Mozek": return "mozek.jpg";
            case "Klouby": return "kloub.jpg";
            default: return "";
        }
    }
}