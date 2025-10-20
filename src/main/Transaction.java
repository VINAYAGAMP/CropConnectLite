package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Transaction {
    private int farmerId;
    private int dealerId;
    private String cropName;
    private int quantity;
    private double pricePerUnit;

    public Transaction(int farmerId, int dealerId, String cropName, int quantity, double pricePerUnit) {
        this.farmerId = farmerId;
        this.dealerId = dealerId;
        this.cropName = cropName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public boolean record() {
        double totalAmount = quantity * pricePerUnit;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO transactions (farmer_id, dealer_id, crop_name, quantity, price_per_unit, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, farmerId);
            ps.setInt(2, dealerId);
            ps.setString(3, cropName);
            ps.setInt(4, quantity);
            ps.setDouble(5, pricePerUnit);
            ps.setDouble(6, totalAmount);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Error recording transaction: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Crop Purchase Transaction ===");
        System.out.print("Farmer ID: ");
        int farmerId = sc.nextInt();
        System.out.print("Dealer ID: ");
        int dealerId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Crop Name: ");
        String cropName = sc.nextLine();
        System.out.print("Quantity (kg): ");
        int quantity = sc.nextInt();
        System.out.print("Price per kg: ");
        double pricePerUnit = sc.nextDouble();

        Transaction tx = new Transaction(farmerId, dealerId, cropName, quantity, pricePerUnit);
        if (tx.record()) {
            System.out.println("Transaction recorded successfully.");
        } else {
            System.out.println("Transaction failed.");
        }
    }
}
