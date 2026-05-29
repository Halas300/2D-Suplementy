import java.util.List;
/**
 * Abstraktní třída, která funguje jako hlavní šablona pro každý produkt v aplikaci.
 */
public abstract class AbstractSupplement implements CatalogItem {
    /**
     * Tady jsou všechny kategorie z jsonu
     */
    protected String name;
    protected String category;
    protected String scientificScore;;;
    protected String summary;
    protected String dosage;
    protected String timing;
    protected String howItWorks;
    protected List<String> benefits;
    protected String mythAndFact;
    protected String specificWarning;
    protected String imagePath;

    public AbstractSupplement(String name, String category, String scientificScore,
                              String summary, String dosage, String timing,
                              String howItWorks, List<String> benefits, String mythAndFact, String specificWarning,String imagePath) {
        this.name = name;
        this.category = category;
        this.scientificScore = scientificScore;
        this.summary = summary;
        this.dosage = dosage;
        this.timing = timing;
        this.howItWorks = howItWorks;
        this.benefits = benefits;
        this.mythAndFact = mythAndFact;
        this.specificWarning = specificWarning;
        this.imagePath = imagePath;
    }

    /**
     * Gettery
     */
    @Override public String getName() {
        return name;
    }
    @Override public String getCategory() {
        return category;
    }
    @Override public String getSummary() {
        return summary;
    }
    public String getScientificScore() {
        return scientificScore;
    }
    public String getDosage() {
        return dosage;
    }
    public String getTiming() {
        return timing;
    }
    public String getHowItWorks() {
        return howItWorks;
    }
    public List<String> getBenefits() {
        return benefits;
    }
    public String getMythAndFact() {
        return mythAndFact;
    }
    public String getSpecificWarning() {
        return specificWarning;
    }
    public String getImagePath() {
        return imagePath;
    }
}