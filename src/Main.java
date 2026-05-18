public class Main {
    public static void main(String[] args) {
        SupplementDatabase myDatabase = new SupplementDatabase();
        MainDashboard dashboard = new MainDashboard(myDatabase);
        dashboard.showDashboard();
    }
}