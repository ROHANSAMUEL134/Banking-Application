import java.util.ArrayList;
import java.util.List;

public class Customer{
    private String name;
    private int accNo;
    private int balance;
    private String password;
    private ArrayList<TransactionHistory> history=new ArrayList<>();
    public Customer(){
        this.balance=1000;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAccNo(){
        return this.accNo;
    }
    public void  setAccNo(int accNo){
        System.out.println(accNo);
        this.accNo=accNo;
    }
   public  void addAmount(int amount){
        this.balance+=amount;
   }
   public void reduceAmount(int amount){

    this.balance-=amount;
   }
   public int viewbalance(){
    return this.balance;
   }
   public void setPassword(String password){
    this.password=password;
   }
   public String getPassword(){
    return this.password;
   } 

   public List<TransactionHistory> getHistory(){
    return this.history;
   } 
   public void addHistory(TransactionHistory hist){
    this.history.add(hist);
   }

}
