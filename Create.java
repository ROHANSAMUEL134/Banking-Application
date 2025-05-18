import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

public class Create {
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<Integer, Customer> allCustomer = new HashMap<>();

    public static String encryption(String password) {
        int n = password.length();
        String encryptString = "";
        int key = 3;

        for (int i = 0; i < n; i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                encryptString += (char) (((ch - '0' + key) % 10) + '0');
            } else if (Character.isUpperCase(ch)) {
                encryptString += (char) (((ch - 'A' + key) % 26) + 'A');
            } else if (Character.isLowerCase(ch)) {
                encryptString += (char) (((ch - 'a' + key) % 26) + 'a');
            } else {
                encryptString += ch;
            }
        }
        return encryptString;
    }

    public static void create(String name, String password, int accNo) {
        try {
            String encryptString = encryption(password);
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3030/bank_app", "rohan", "Rohan@134");
            String sql = "INSERT INTO customers (accNo, name, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, accNo);
            stmt.setString(2, name);
            stmt.setString(3, encryptString);
            stmt.executeUpdate();
            con.close();

            Customer obj = new Customer();
            obj.setAccNo(accNo);
            obj.setName(name);
            obj.setPassword(encryptString);
            customers.add(obj);
            allCustomer.put(accNo, obj);

            System.out.println("Account Created Successfully");
            System.out.println("Account No: " + accNo);
            System.out.println("Name: " + name);

        } catch (Exception e) {
            System.out.println("Error creating account.");
            e.printStackTrace();
        }
    }
}
