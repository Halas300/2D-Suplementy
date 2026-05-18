import java.util.List;

public abstract class AbstractSupplement implements CatalogItem {
    protected String name;
    protected String category;
    protected String scientificScore;
    protected String summary;
    protected String dosage;
    protected String timing;
    protected String howItWorks;
    protected List<String> benefits;
    protected String mythAndFact;
    protected String specificWarning;

    public AbstractSupplement(String name, String category, String scientificScore,
                              String summary, String dosage, String timing,
                              String howItWorks, List<String> benefits, String mythAndFact, String specificWarning) {
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
    }


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
    public abstract String getSpecificWarning();
}