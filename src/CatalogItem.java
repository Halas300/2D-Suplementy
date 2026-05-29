/**
 * Interface, který zaručuje, že každý produkt v katalogu bude mít název, kategorii a shrnutí.
 */
public interface CatalogItem {
    String getName();
    String getCategory();
    String getSummary();
}