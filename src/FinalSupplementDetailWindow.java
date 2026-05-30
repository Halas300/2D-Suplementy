import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Největší okno, kde se zobrazuje vše ohledně konkrétního produktu.
 */
public class FinalSupplementDetailWindow {
    private JFrame frame;
    private AbstractSupplement supplement;

    public FinalSupplementDetailWindow(AbstractSupplement supplement) {
        this.supplement = supplement;
        this.frame = new JFrame("Detail: " + supplement.getName());
    }
    /**
     * Hlavní metoda, která zobrazí okno;.
     */
    public void showWindow() {
        frame.setSize(1000, 750);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        /**
         * Horní panel pro zobrazení obrázku a nadpisů.
         */
        JPanel headerPanel = new JPanel(new BorderLayout(15, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        headerPanel.setBackground(Color.WHITE);
        JLabel imageLabel = new JLabel();
        try {
            /**
             * Načítání fotky ze složky images a propočítání poměru stran, aby obrázek vypadal normálně.
             */
            BufferedImage img = ImageIO.read(new File("images/" + supplement.getImagePath()));
            /**
             * Zjištění původní šířky a výšku obrázku a přepočet
             */
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;
            int targetHeight = 120;
            int targetWidth = (int) (targetHeight * aspectRatio);

            if (targetWidth > 150) {
                targetWidth = 150;
                targetHeight = (int) (targetWidth / aspectRatio);
            }
            /**
             * Finální vygenrování správných rozměrů
             */
            Image scaledImg = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            imageLabel.setIcon(new ImageIcon(scaledImg));

        } catch (Exception e) {
            /**
             * Když tam obrázek není, vypíše se že chybý obrázek.
             */
            imageLabel.setText("Není obrázek");
            imageLabel.setPreferredSize(new Dimension(120, 120));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
        headerPanel.add(imageLabel, BorderLayout.WEST);

        /**
         * Panel pro název a kategorii + nastavení složení, barvy pozadí atd.
         */
        JPanel textTitlePanel = new JPanel();
        textTitlePanel.setLayout(new BoxLayout(textTitlePanel, BoxLayout.Y_AXIS));
        textTitlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel(supplement.getName());
        /**
         * Vytvoření nápisu s názvem produktu.
         */
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        textTitlePanel.add(titleLabel);
        textTitlePanel.add(Box.createVerticalStrut(5));
        /**
        * Nápis pro kategorii produktu.
        */
        JLabel categoryLabel = new JLabel("Kategorie: " + supplement.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryLabel.setForeground(Color.GRAY);
        textTitlePanel.add(categoryLabel);
        headerPanel.add(textTitlePanel, BorderLayout.CENTER);
        /**
         * Panel pro vědecké skore (Jak je to vědecky prokázané).
         */
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setOpaque(false);
        /**
         * Zde je nadpis.
         */
        JLabel scoreTitleLabel = new JLabel("Vědecká průkaznost:");
        scoreTitleLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        scoreTitleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scorePanel.add(scoreTitleLabel);
        /**
         * Zde je to skore.
         */
        JLabel scoreTextLabel = new JLabel(supplement.getScientificScore());
        scoreTextLabel.setFont(new Font("Arial", Font.BOLD, 12));
        scoreTextLabel.setForeground(new Color(0, 100, 0));
        scoreTextLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scorePanel.add(scoreTextLabel);
        headerPanel.add(scorePanel, BorderLayout.EAST);
        frame.add(headerPanel, BorderLayout.NORTH);
        /**
         * Vytvoření hlavní části okna, ve které jsou tři části - Info a Benefity, Dávkování a Upozornění
         */
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Info a Benefity", createInfoTab());
        tabbedPane.addTab("Dávkování", createDosageTab());
        tabbedPane.addTab("Upozornění", createWarningsTab());
        frame.add(tabbedPane, BorderLayout.CENTER);
        /**
         * Tlačítko pro návrat zpět na seznam
         */
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        JButton backButton = new JButton("Zpět na seznam");
        backButton.addActionListener(e -> frame.dispose());
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Metoda pro část Info a Benefity.
     */
    private JPanel createInfoTab() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(new Color(255, 253, 245));
        /**
         * Vytvoření obalovacího panelu vzdělávací blok.
         */
        JPanel eduBlockPanel = new JPanel();
        eduBlockPanel.setLayout(new BoxLayout(eduBlockPanel, BoxLayout.Y_AXIS));
        eduBlockPanel.setBorder(BorderFactory.createTitledBorder(" Vzdělávací blok"));
        JPanel summaryPanel = new JPanel(new BorderLayout(5, 5));
        /**
         * Část pro shrnutí (načtení textu a nastavení).
         */
        JLabel summaryTitle = new JLabel("Shrnutí ve zkratce");
        summaryTitle.setFont(new Font("Arial", Font.BOLD, 14));
        summaryPanel.add(summaryTitle, BorderLayout.NORTH);
        JTextArea summaryArea = new JTextArea(supplement.getSummary());
        summaryArea.setWrapStyleWord(true);
        summaryArea.setLineWrap(true);
        summaryArea.setOpaque(false);
        summaryArea.setEditable(false);
        summaryPanel.add(summaryArea, BorderLayout.CENTER);
        eduBlockPanel.add(summaryPanel);
        eduBlockPanel.add(Box.createVerticalStrut(10));
        /**
         * Část pro vysvětlení jak to funguje (stejný jako u shrnutí ve zkratce).
         */
        JPanel worksPanel = new JPanel(new BorderLayout(5, 5));
        JLabel worksTitle = new JLabel("Jak to funguje");
        worksTitle.setFont(new Font("Arial", Font.BOLD, 14));
        worksPanel.add(worksTitle, BorderLayout.NORTH);
        JTextArea worksArea = new JTextArea(supplement.getHowItWorks());
        worksArea.setWrapStyleWord(true);
        worksArea.setLineWrap(true);
        worksArea.setOpaque(false);
        worksArea.setEditable(false);
        worksPanel.add(worksArea, BorderLayout.CENTER);
        eduBlockPanel.add(worksPanel);
        eduBlockPanel.add(Box.createVerticalStrut(10));
        /**
         * Část pro benefity (vypisuje se v bodech).
         */
        JPanel benefitsPanel = new JPanel(new BorderLayout(5, 5));
        JLabel benefitsTitle = new JLabel("Hlavní benefity");
        benefitsTitle.setFont(new Font("Arial", Font.BOLD, 14));
        /**
         * Vytvoření panelu, výber benefitu a vypsání s tečkou.
         */
        benefitsPanel.add(benefitsTitle, BorderLayout.NORTH);
        JPanel benefitsListPanel = new JPanel();
        benefitsListPanel.setLayout(new BoxLayout(benefitsListPanel, BoxLayout.Y_AXIS));
        for (String benefit : supplement.getBenefits()) {
            benefitsListPanel.add(new JLabel("• " + benefit));
        }
        benefitsPanel.add(benefitsListPanel, BorderLayout.CENTER);
        eduBlockPanel.add(benefitsPanel);
        eduBlockPanel.add(Box.createVerticalStrut(10));
        /**
         * Část pro mýty a fakta (výpis a nastavení části + textu).
         */
        JPanel mythsPanel = new JPanel();
        mythsPanel.setLayout(new BoxLayout(mythsPanel, BoxLayout.Y_AXIS));
        mythsPanel.setBorder(BorderFactory.createTitledBorder("Mýty a fakta"));
        JPanel mythRow = new JPanel(new BorderLayout(10, 0));
        mythRow.setOpaque(false);
        JTextArea mythArea = new JTextArea(supplement.getMythAndFact());
        mythArea.setWrapStyleWord(true);
        mythArea.setLineWrap(true);
        mythArea.setOpaque(false);
        mythArea.setEditable(false);
        mythArea.setFont(new Font("Arial", Font.BOLD, 12));
        mythRow.add(mythArea, BorderLayout.CENTER);
        mythsPanel.add(mythRow);
        eduBlockPanel.add(mythsPanel);
        infoPanel.add(eduBlockPanel);
        return infoPanel;
    }
    /**
     * Metoda pro Dávkování. (Pokládání panelů a textů je tu stejný jako nahoře)
     */
    private JPanel createDosageTab() {
        JPanel dosagePanel = new JPanel();
        dosagePanel.setLayout(new BoxLayout(dosagePanel, BoxLayout.Y_AXIS));
        dosagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        dosagePanel.setBackground(new Color(255, 253, 245));
        /**
         * Vytvoření obalovacího panelu pro doporučené dávkování.
         */
        JPanel dosageBlockPanel = new JPanel();
        dosageBlockPanel.setLayout(new BoxLayout(dosageBlockPanel, BoxLayout.Y_AXIS));
        dosageBlockPanel.setBorder(BorderFactory.createTitledBorder(" Detaily dávkování"));
        JPanel dosageSection = new JPanel(new BorderLayout(5, 5));
        dosageSection.setOpaque(false);
        /**
         * Část s dávkováním (Nastavení části a textu)
         */
        JLabel dosageLabel = new JLabel("Doporučené dávkování");
        dosageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dosageSection.add(dosageLabel, BorderLayout.NORTH);
        JTextArea dosageText = new JTextArea(supplement.getDosage());
        dosageText.setWrapStyleWord(true);
        dosageText.setLineWrap(true);
        dosageText.setOpaque(false);
        dosageText.setEditable(false);
        dosageText.setFont(new Font("Arial", Font.PLAIN, 13));
        dosageSection.add(dosageText, BorderLayout.CENTER);
        dosageBlockPanel.add(dosageSection);
        dosageBlockPanel.add(Box.createVerticalStrut(20));
        JPanel timingSection = new JPanel(new BorderLayout(5, 5));
        timingSection.setOpaque(false);
        /**
         * Část s načasováním (Nastavení části a textu)
         */
        JLabel timingLabel = new JLabel("Ideální čas");
        timingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timingSection.add(timingLabel, BorderLayout.NORTH);
        JTextArea timingText = new JTextArea(supplement.getTiming());
        timingText.setWrapStyleWord(true);
        timingText.setLineWrap(true);
        timingText.setOpaque(false);
        timingText.setEditable(false);
        timingText.setFont(new Font("Arial", Font.PLAIN, 13));
        timingSection.add(timingText, BorderLayout.CENTER);
        dosageBlockPanel.add(timingSection);
        dosagePanel.add(dosageBlockPanel);
        /**
         * VerticalGlue zaplní prázdné místo dole, takže se text nevycentruje na střed
         */
        dosagePanel.add(Box.createVerticalGlue());
        return dosagePanel;
    }
    /**
     * Metoda pro Upozornění (pozadí je naschvál čevenější, aby to působilo varovně).
     */
    private JPanel createWarningsTab() {
        JPanel warningsPanel = new JPanel();
        warningsPanel.setLayout(new BoxLayout(warningsPanel, BoxLayout.Y_AXIS));
        warningsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        warningsPanel.setBackground(new Color(255, 245, 245));
        JPanel warnSection = new JPanel(new BorderLayout(5, 5));
        warnSection.setOpaque(false);
        JLabel warnLabel = new JLabel("Zdravotní upozornění a rizika");
        warnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        warnLabel.setForeground(new Color(150, 0, 0));
        warnSection.add(warnLabel, BorderLayout.NORTH);
        JTextArea warningsText = new JTextArea(supplement.getSpecificWarning());
        warningsText.setWrapStyleWord(true);
        warningsText.setLineWrap(true);
        warningsText.setOpaque(false);
        warningsText.setEditable(false);
        warningsText.setFont(new Font("Arial", Font.PLAIN, 14));
        warnSection.add(warningsText, BorderLayout.CENTER);
        warningsPanel.add(warnSection);
        warningsPanel.add(Box.createVerticalGlue());
        return warningsPanel;
    }
}