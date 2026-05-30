public class Main {
    /**
     * Spouštěcí celé aplikace.
     */
    public static void main(String[] args) {
        /**
         * Vytvoření databáze, která si načte všechno z JSONu.
         */
        SupplementDatabase myDatabase = new SupplementDatabase();
        /**
         * Vytvoření hlavní obrazovku.
         */
        MainDashboard dashboard = new MainDashboard(myDatabase);
        dashboard.showDashboard();
    }
}