
import java.util.Scanner;

public class Display{
    public static  void show(Customer customer){
        System.out.println("name:"+customer.getName());
        System.out.println("accNo:"+customer.getAccNo());
        System.out.println("balance"+customer.viewbalance());

    }
    public static String decryption(String password){
        String decryptString="";
        int n=password.length();
        int key=3;
        for(int i=0;i<n;i++){
            if(Character.isDigit(password.charAt(i))){
                decryptString+=(char)(((password.charAt(i)-'0'-key+26)%26)+'0');
            }
            else if(Character.isUpperCase(password.charAt(i))){
                decryptString+=(char) (((password.charAt(i)-'A'-key+26)%26)+'A');
            }
            else if(Character.isLowerCase(password.charAt(i))){
                decryptString+=(char)(((password.charAt(i)-'a'-key+26)%26)+'a');
            }
            else{
                decryptString+=(password.charAt(i));
            }
           
        }
        return decryptString;

    }
    public static  void display(){
            Scanner scanner = new Scanner(System.in);
            try{
                 
                System.out.println("enter your accNo");
                int accNo=Integer.parseInt(scanner.nextLine());
                System.out.println("enter your password");
                String password=scanner.nextLine();
                 
                
                
                
                if(!Create.allCustomer.containsKey(accNo)){
                    System.out.println("Your accNo wrong");
                }
                
                else{
                    Customer temp=Create.allCustomer.get(accNo);
                    String decrptpassword=Decryption.decrypt(temp.getPassword());
                    System.out.println(decrptpassword);

                    if(decrptpassword.equals(password)){
                        System.out.println("new code testing");
                        Display.show(temp);
                    }
                    else{
                        System.out.println("your Password is Wrong");
                    }
                    
                }
            
        } catch (NumberFormatException e) {
            System.out.println("enter valid account number");

        }
        catch(Exception e){
            System.out.println("exception occuredd"+e.getMessage());
        }
        
    }

}