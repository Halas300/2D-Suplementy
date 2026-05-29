import java.util.List;
/**
 * Třída pro produkt, dědí všechny vlastnosti z AbstractSupplement.
 */
public class MuscleSupplement extends AbstractSupplement {

    public MuscleSupplement(String name, String category, String scientificScore,
                            String summary, String dosage, String timing,
                            String howItWorks, List<String> benefits, String mythAndFact, String specificWarning, String imagePath) {

        super(name, category, scientificScore, summary, dosage, timing, howItWorks, benefits, mythAndFact, specificWarning, imagePath);
    }
}