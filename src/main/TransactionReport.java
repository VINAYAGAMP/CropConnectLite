package main;

import java.sql.*;
import java.util.Scanner;

public class TransactionReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Dealer ID: ");
        int dealerId = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Crop Name (or leave blank): ");
        String cropName = sc.nextLine();

        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = sc.nextLine();

        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = sc.nextLine();

        String sql = "SELECT COUNT(*) AS total_tx, SUM(total_amount) AS total_spent FROM transactions WHERE dealer_id = ? AND transaction_date BETWEEN ? AND ?";
        if (!cropName.isEmpty()) {
            sql += " AND crop_name = ?";
        }

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dealerId);
            ps.setString(2, startDate + " 00:00:00");
            ps.setString(3, endDate + " 23:59:59");
            if (!cropName.isEmpty()) {
                ps.setString(4, cropName);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("=== Transaction Report ===");
                System.out.println("Total Transactions: " + rs.getInt("total_tx"));
                System.out.println("Total Amount Spent: ₹" + rs.getDouble("total_spent"));
            } else {
                System.out.println("No transactions found.");
            }
        } catch (Exception e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }
}
