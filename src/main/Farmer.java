package main;

import java.sql.*;
import java.util.Scanner;

public class Farmer {
    private String name;
    private String email;
    private String password;
    private String location;

    public Farmer(String name, String email, String password, String location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public int saveAndGetId() {
        int farmerId = -1;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO farmers (name, email, password, location) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, location);
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    farmerId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving farmer: " + e.getMessage());
        }
        return farmerId;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Farmer Registration ===");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Location: ");
        String location = sc.nextLine();

        Farmer farmer = new Farmer(name, email, password, location);
        int id = farmer.saveAndGetId();
        if (id != -1) {
            System.out.println("Farmer registered successfully! ID: " + id);
        } else {
            System.out.println("Registration failed.");
        }
    }
}
