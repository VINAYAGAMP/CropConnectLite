

## README.md — CropConnectLite (Backend Console Edition)

```markdown
# CropConnectLite — Backend Console System

CropConnectLite is a modular, console-based backend system built in Java with MySQL integration. It simulates a real-world crop trading platform where farmers register and dealers purchase crops, all managed through a clean command-line interface.

## Features

- Farmer Registration with auto-generated ID
- Dealer Registration with auto-generated ID
- Secure login for farmers and dealers (SHA-256 password hashing)
- Crop purchase transactions linking farmer and dealer
- Transaction reporting with crop and date filters
- Export transaction history to `.txt` file
- Centralized launcher via `MainApp.java`

## Tech Stack

- Language: Java (JDK 21)
- Database: MySQL
- IDE: Eclipse
- Libraries: MySQL Connector/J (`mysql-connector-java-8.0.17.jar` or newer)

## Project Structure

```
CropConnectLite/
├── src/main/
│   ├── DBConnection.java
│   ├── Farmer.java
│   ├── Dealer.java
│   ├── Transaction.java
│   ├── TransactionViewer.java
│   ├── TransactionReport.java
│   ├── FarmerLogin.java
│   ├── DealerLogin.java
│   ├── PasswordUtil.java
│   └── MainApp.java
├── schema.sql
└── transactions_report.txt (generated)
```

## Setup Instructions

1. Clone the repo
   ```bash
   git clone https://github.com/VINAYAGAMP/CropConnectLite.git
   ```

2. Import into Eclipse
   - File → Import → Existing Projects → Select `CropConnectLite`

3. Add MySQL Connector JAR
   - Right-click project → Build Path → Add External JARs
   - Choose `mysql-connector-java-8.0.xx.jar`

4. Create MySQL Database
   ```sql
   CREATE DATABASE cropconnectlite;
   USE cropconnectlite;
   -- Run schema.sql to create tables
   ```

5. Update DB Credentials in `DBConnection.java`
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/cropconnectlite";
   private static final String USER = "your_mysql_user";
   private static final String PASSWORD = "your_mysql_password";
   ```

6. Run `MainApp.java`
   - Choose actions from the menu to register, login, transact, and report

## Sample Output

```
=== CropConnectLite Backend Console ===
1. Register Farmer
2. Register Dealer
3. Farmer Login
4. Dealer Login
5. Record Crop Transaction
6. View Transactions
7. Generate Transaction Report
0. Exit
```

## Security Notes

- Passwords are hashed using SHA-256 before storing in the database
- No plain-text passwords are saved or compared

## License

This project is open-source and free to use for educational and professional purposes.

## Author

Vinayagam  
Backend Developer | Java + MySQL | Console Systems  
Inspired by real-world deployment and interview readiness
