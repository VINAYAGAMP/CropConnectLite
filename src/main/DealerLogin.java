package main;

import java.sql.*;
import java.util.Scanner;

public class DealerLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Dealer Login ===");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id, name FROM dealers WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("name"));
                System.out.println("Your Dealer ID: " + rs.getInt("id"));
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
    }
}
