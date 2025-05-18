import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static int accNo = 0;

    public static void thank() {
        System.out.print("Thank you for visiting..");
    }

    public static void initializeAccNo() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "yourpassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(accNo) AS maxAcc FROM customers");
            if (rs.next()) {
                accNo = rs.getInt("maxAcc") + 1;
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error initializing account number from DB.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeAccNo();  

        int loop = 1;
        while (loop == 1) {
            System.out.println("Welcome to Banking System");
            System.out.println("1. Add Account");
            System.out.println("2. Display customer details");
            System.out.println("3. Do Transaction");
            System.out.println("4. History");
            System.out.println("Press any other key to exit");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                Main.thank();
                break;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();

                    while (!Validation.nameCheck(name)) {
                        System.out.println("Please enter valid name:");
                        name = scanner.nextLine();
                    }

                    while (!Validation.passwordCheck(password)) {
                        System.out.println("Give strong password:");
                        password = scanner.nextLine();
                    }

                    Create.create(name, password, accNo++);
                }

                case 2 -> {
                    Display.display();
                }

                case 3 -> {
                    Transaction.transfer();
                }

                case 4 -> {
                    Transaction.history();
                }

                default -> {
                    Main.thank();
                    loop = 0;
                }
            }
        }

        scanner.close();
    }
}
