package main;

import java.sql.*;
import java.util.Scanner;

public class Dealer {
    private String name;
    private String email;
    private String password;
    private String companyName;

    public Dealer(String name, String email, String password, String companyName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
    }

    public int saveAndGetId() {
        int dealerId = -1;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO dealers (name, email, password, company_name) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, companyName);
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    dealerId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving dealer: " + e.getMessage());
        }
        return dealerId;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Dealer Registration ===");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Company Name: ");
        String companyName = sc.nextLine();

        Dealer dealer = new Dealer(name, email, password, companyName);
        int id = dealer.saveAndGetId();
        if (id != -1) {
            System.out.println("Dealer registered successfully! ID: " + id);
        } else {
            System.out.println("Registration failed.");
        }
    }
}
