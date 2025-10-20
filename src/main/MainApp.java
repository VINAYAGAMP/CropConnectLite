package main;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== CropConnectLite Backend Console ===");
            System.out.println("1. Register Farmer");
            System.out.println("2. Register Dealer");
            System.out.println("3. Farmer Login");
            System.out.println("4. Dealer Login");
            System.out.println("5. Record Crop Transaction");
            System.out.println("6. View Transactions");
            System.out.println("7. Generate Transaction Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Farmer.main(new String[]{});
                    break;
                case 2:
                    Dealer.main(new String[]{});
                    break;
                case 3:
                    FarmerLogin.main(new String[]{});
                    break;
                case 4:
                    DealerLogin.main(new String[]{});
                    break;
                case 5:
                    Transaction.main(new String[]{});
                    break;
                case 6:
                    TransactionViewer.main(new String[]{});
                    break;
                case 7:
                    TransactionReport.main(new String[]{});
                    break;
                case 0:
                    System.out.println("Exiting CropConnectLite. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }
}
