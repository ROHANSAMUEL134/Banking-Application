
import java.util.ArrayList;
import java.util.HashMap;

public class Create{
    static  ArrayList<Customer> customers=new ArrayList<>();
    static HashMap<Integer,Customer> allCustomer=new HashMap<>();
    public static String encryption(String password){
        int n=password.length();
        String encryptString="";

        int key=3;
        for(int i=0;i<n;i++){
            if(Character.isDigit(password.charAt(i))){
                encryptString+=(char)(((password.charAt(i)-'0'+key)%10)+'0');
            }
            else if(Character.isUpperCase(password.charAt(i))){
                encryptString+=(char)(((password.charAt(i)-'A'+key)%26)+'A');
            }
            else if(Character.isLowerCase(password.charAt(i))){

                encryptString+=(char)(((password.charAt(i)-'a'+key)%26)+'a');
            }
            else{
                encryptString+=password.charAt(i);
            }
            
        }
        return encryptString;
    }

    public static void create(String name,String password,int accNo){
        Customer obj=new Customer();
        obj.setAccNo(accNo);
        obj.setName(name);
        String encryptString=encryption(password);
        obj.setPassword(encryptString);
        customers.add(obj);
        
        allCustomer.put(accNo,obj);

        System.out.println("Account Created Successfully");
        System.out.println("account no"+accNo);
        System.out.println("name:"+name);
    }
}
