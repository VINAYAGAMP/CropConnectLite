package main;

import java.sql.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TransactionViewer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== View Transactions ===");
        System.out.println("1. View by Farmer ID");
        System.out.println("2. View by Dealer ID");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        String sql = (choice == 1)
            ? "SELECT * FROM transactions WHERE farmer_id = ?"
            : "SELECT * FROM transactions WHERE dealer_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            System.out.println("=== Transactions ===");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("Crop: " + rs.getString("crop_name"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price per unit: ₹" + rs.getDouble("price_per_unit"));
                System.out.println("Total: ₹" + rs.getDouble("total_amount"));
                System.out.println("Date: " + rs.getTimestamp("transaction_date"));
                System.out.println("----------------------");
            }

            if (!hasData) {
                System.out.println("No transactions found.");
                return;
            }

            System.out.print("Export to file? (yes/no): ");
            String export = sc.nextLine();

            if (export.equalsIgnoreCase("yes")) {
                // Re-run query to write to file
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                try (PrintWriter writer = new PrintWriter(new FileWriter("transactions_report.txt"))) {
                    writer.println("Crop\tQuantity\tPrice\tTotal\tDate");
                    while (rs.next()) {
                        writer.printf("%s\t%d\t%.2f\t%.2f\t%s%n",
                            rs.getString("crop_name"),
                            rs.getInt("quantity"),
                            rs.getDouble("price_per_unit"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("transaction_date").toString()
                        );
                    }
                    System.out.println("✅ Report saved to transactions_report.txt");
                } catch (Exception e) {
                    System.out.println("File export error: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }
}
