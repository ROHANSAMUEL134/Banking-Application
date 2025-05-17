import java.util.Scanner;

public class Main{
    static int accNo=0;
    public static  void thank(){
        System.out.print("Thank you for visiting..");
    }
    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);

        int loop=1;
        while(loop==1){
            System.out.println("welcome to Banking System");
            System.out.println("1. Add Account");
            System.out.println("2. Display customer details");
            System.out.println("3. Do Transaction");
            System.out.println("4. History");
            System.out.println("press anyother key to exit");
            int choice=Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1 -> {
                    System.out.println("enter your name");
                    String name=scanner.nextLine();
                    System.out.println("enter password");
                    String password=scanner.nextLine();
                    while(Validation.nameCheck(name)!=true){
                        System.out.println("please enter valid name");
                        name=scanner.nextLine();

                    }
                    
                    while (Validation.passwordCheck(password)!=true){
                        System.out.println("give Strong password");
                        password=scanner.nextLine();
                    }
                    Create.create(name,password,accNo++);
                }
                case 2 -> {
                    Display.display();

                }
                case 3 -> {
                    Transaction.transfer();
                }
                case 4->{
                    Transaction.history();
                }
                default -> {
                    Main.thank();
                    loop=0;
                    
                }
                
            }
            
            // System.out.println("Do you want to continue?");
            // System.out.println("1. YES");
            // System.out.println("2. NO");
            // enter=scanner.nextInt();
        
        }
        scanner.close();
    }
}
