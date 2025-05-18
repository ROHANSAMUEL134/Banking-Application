import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Display {

    public static void show(Customer customer) {
        System.out.println("Name : " + customer.getName());
        System.out.println("AccNo : " + customer.getAccNo());
        System.out.println("Balance : " + customer.viewbalance());
    }

    public static String decryption(String password) {
        String decryptString = "";
        int n = password.length();
        int key = 3;

        for (int i = 0; i < n; i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                decryptString += (char) (((ch - '0' - key + 10) % 10) + '0');
            } else if (Character.isUpperCase(ch)) {
                decryptString += (char) (((ch - 'A' - key + 26) % 26) + 'A');
            } else if (Character.isLowerCase(ch)) {
                decryptString += (char) (((ch - 'a' - key + 26) % 26) + 'a');
            } else {
                decryptString += ch;
            }
        }
        return decryptString;
    }

    public static void display() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your accNo");
            int accNo = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter your password");
            String password = scanner.nextLine();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3030/bank_app", "rohan", "Rohan@134");

            String sql = "SELECT name, password, balance FROM customers WHERE accNo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, accNo);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Your accNo is wrong or does not exist.");
            } else {
                String encryptedPassword = rs.getString("password");
                String decryptedPassword = decryption(encryptedPassword);

                if (decryptedPassword.equals(password)) {
                    Customer temp = new Customer();
                    temp.setAccNo(accNo);
                    temp.setName(rs.getString("name"));
                    temp.setPassword(encryptedPassword);
                    temp.setBalance(rs.getDouble("balance")); 

                    show(temp);
                } else {
                    System.out.println("Your Password is Wrong!");
                }
            }
            con.close();

        } catch (NumberFormatException e) {
            System.out.println("Enter a valid account number.");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
