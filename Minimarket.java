import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Minimarket {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private static final String CAPTCHA = "VisualStudioCode";

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/minimarket";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessful = false;
        boolean inputValid = false;

        do {
            System.out.println("=== LOGIN ===");
            if (login(scanner)) {
                String generatedCaptcha = generateCaptcha();
                System.out.println("Captcha: " + generatedCaptcha);

                if (validateCaptcha(scanner, generatedCaptcha)) {
                    loginSuccessful = true;
                } else {
                    System.out.println("Captcha salah. Silakan login kembali.");
                }
            } else {
                System.out.println("Username atau password salah. Silakan login kembali.");
            }
        } while (!loginSuccessful);

        do {
            try {
                System.out.println("\n===MINIMARKET MENU===");
                System.out.println("1. Create Database");
                System.out.println("2. Read Database");
                System.out.println("3. Update Database");
                System.out.println("4. Delete Database");
                System.out.println("5. Exit");
                System.out.print("Pilih menu (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createDatabase();
                        break;
                    case 2:
                        readDatabase();
                        break;
                    case 3:
                        updateDatabase();
                        break;
                    case 4:
                        deleteDatabase();
                        break;
                    case 5:
                        inputValid = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage() + "\n");
                scanner.nextLine(); // Membersihkan newline
            }
        } while (!inputValid);

        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Password: ");
        String enteredPassword = scanner.nextLine();
        return USERNAME.equals(enteredUsername) && PASSWORD.equals(enteredPassword);
    }

    private static String generateCaptcha() {
        return CAPTCHA;
    }

    private static boolean validateCaptcha(Scanner scanner, String generatedCaptcha) {
        System.out.print("Masukkan Captcha: ");
        String enteredCaptcha = scanner.nextLine();
        return generatedCaptcha.equalsIgnoreCase(enteredCaptcha);
    }

    private static void createDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO pelanggan (namaPelanggan, noHP, alamat) VALUES (?, ?, ?)")) {
    
            System.out.println("\n=== CREATE DATABASE ===");
            DataPelanggan dataPelanggan = inputDataPelanggan();
    
            // Periksa apakah nama pelanggan tidak null atau kosong
            if (dataPelanggan.getNamaPelanggan() == null || dataPelanggan.getNamaPelanggan().isEmpty()) {
                System.out.println("Error: Nama pelanggan tidak boleh null atau kosong.");
                return;
            }
    
            preparedStatement.setString(1, dataPelanggan.getNamaPelanggan());
            preparedStatement.setString(2, dataPelanggan.getNoHP());
            preparedStatement.setString(3, dataPelanggan.getAlamat());
    
            preparedStatement.executeUpdate();
    
            System.out.println("Data pelanggan berhasil ditambahkan.");
    
        } catch (SQLException e) {
            System.out.println("Error saat penyisipan: " + e.getMessage());
        }
    }
    

    private static void readDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM pelanggan");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\n=== READ DATABASE ===");
            while (resultSet.next()) {
                String namaPelanggan = resultSet.getString("namaPelanggan");
                String noHP = resultSet.getString("noHP");
                String alamat = resultSet.getString("alamat");

                System.out.println("Nama Pelanggan: " + namaPelanggan);
                System.out.println("No. HP: " + noHP);
                System.out.println("Alamat: " + alamat);
                System.out.println("++++++++++++++++++++++++");
            }

        } catch (SQLException e) {
            System.out.println("Error saat pengambilan data: " + e.getMessage());
        }
    }

    private static void updateDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE pelanggan SET noHP = ?, alamat = ? WHERE namaPelanggan = ?")) {

            System.out.println("\n=== UPDATE DATABASE ===");
            DataPelanggan dataPelanggan = inputDataPelanggan();

            preparedStatement.setString(1, dataPelanggan.getNoHP());
            preparedStatement.setString(2, dataPelanggan.getAlamat());
            preparedStatement.setString(3, dataPelanggan.getNamaPelanggan());

            preparedStatement.executeUpdate();

            System.out.println("Data pelanggan berhasil diperbarui.");

        } catch (SQLException e) {
            System.out.println("Error saat pembaruan: " + e.getMessage());
        }
    }

    private static void deleteDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM pelanggan WHERE namaPelanggan = ?")) {

            System.out.println("\n=== DELETE DATABASE ===");
            System.out.print("Masukkan nama pelanggan yang akan dihapus: ");
            Scanner scanner = new Scanner(System.in);
            String namaPelanggan = scanner.nextLine();

            preparedStatement.setString(1, namaPelanggan);

            preparedStatement.executeUpdate();

            System.out.println("Data pelanggan berhasil dihapus.");

        } catch (SQLException e) {
            System.out.println("Error saat penghapusan: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
    }

    private static DataPelanggan inputDataPelanggan() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nama Pelanggan : ");
        String namaPelanggan = scanner.nextLine();

        System.out.print("No. HP         : ");
        String noHP = scanner.nextLine();

        System.out.print("Alamat         : ");
        String alamat = scanner.nextLine();

        return new DataPelanggan(namaPelanggan, noHP, alamat);
    }
}
