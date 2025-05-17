
import java.time.LocalDateTime;
import java.util.Scanner;
public class Transaction{
    public static void history(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        try  {
            
                System.out.println("Enter account no:");
                int accNo=Integer.parseInt(scanner.nextLine());
                System.out.println("Enter password:");
                String password=scanner.nextLine();
                Customer temp=null;
                for(Customer cust:Create.customers){
                    String decrypString=Display.decryption(cust.getPassword());
                    if(cust.getAccNo()==accNo && decrypString.equals(password)){
                        temp=cust;
                        break;
                    }
                }
                if(temp==null){
                    System.out.println("Wrong AccNo or password!!");
                }
                else{
                    FileHandler.readTransactionHistory(temp.getAccNo());
                    for(TransactionHistory current:temp.getHistory()){
                        System.out.println(current.getSenderName()+current.getReceiverName()+current.amount()+current.getTransferType());
                    }
                }
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid account number");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        
    }
    public static  void transfer(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter your (Sender) account no");
            int senderAccNo=Integer.parseInt(scanner.nextLine());
            System.out.println("Enter your (Sender) password");
            String senderPassword=scanner.nextLine();
            System.out.println("Enter receiver account no");
            int receiverAccNo=Integer.parseInt(scanner.nextLine());
            
            System.out.println("Enter Amount");
            int amount=Integer.parseInt(scanner.nextLine());
            Customer senderObj=null;
            Customer receiverObj=null;
            for(Customer cust:Create.customers){
                String decrypString=Display.decryption(cust.getPassword());
                if(cust.getAccNo()==senderAccNo && decrypString.equals(senderPassword)){
                    senderObj=cust;
                }
                if(cust.getAccNo()==receiverAccNo){
                    receiverObj=cust;
                }
            }
            if(senderObj==null && receiverObj==null){
                System.out.println("Sender and Receiver not exists.. enter valid details");
            }
            else if(senderObj==null){
                System.out.println("Sender not exists");
            }
            else if(receiverObj==null){
                System.out.println("Receiver not exists");
            }
            else if(senderObj.viewbalance()<amount){
                System.out.println("You don't have sufficient amount");
            }
            else{
                senderObj.reduceAmount(amount);
                receiverObj.addAmount(amount);
                System.out.println("Transaction success");
                // After successful transaction
                String transactionDetails = "Sender: " + senderObj.getName() +
                ", Receiver: " + receiverObj.getName() +
                ", Amount: " + amount +
                ", Date: " + LocalDateTime.now();

                FileHandler.writeTransactionToFile(senderObj.getAccNo(), "Debited: " + transactionDetails);
                FileHandler.writeTransactionToFile(receiverObj.getAccNo(), "Credited: " + transactionDetails);

                senderObj.addHistory(new TransactionHistory(senderObj.getAccNo(),senderObj.getName(),receiverObj.getAccNo()
                ,receiverObj.getName(),"debited",amount));
                receiverObj.addHistory(new TransactionHistory(senderObj.getAccNo(),senderObj.getName(),receiverObj.getAccNo(),receiverObj.getName(),"credited",amount));
                // senderObj.addHistory(receiverObj.getName(), receiverObj.getAccNo(),"debited",amount,LocalDateTime.now());
                // receiverObj.addHistory(senderObj.getName(), senderObj.getAccNo(),"credited",amount,LocalDateTime.now());
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid accno or amount .. check the details");

        }
        catch(Exception e){
            System.out.println(e.getMessage());;

        }
        
    }
}
