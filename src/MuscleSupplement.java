import java.util.List;

public class MuscleSupplement extends AbstractSupplement {

    public MuscleSupplement(String name, String category, String scientificScore,
                            String summary, String dosage, String timing,
                            String howItWorks, List<String> benefits, String mythAndFact, String specificWarning, String imagePath) {

        super(name, category, scientificScore, summary, dosage, timing, howItWorks, benefits, mythAndFact, specificWarning, imagePath);
    }

    @Override
    public String getSpecificWarning() {
        return specificWarning;
    }
}