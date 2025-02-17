
import java.time.LocalDateTime;
import java.util.Scanner;
public class  Transaction{
    public static void history(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        try  {
            
                System.out.println("enter account no:");
                int accNo=Integer.parseInt(scanner.nextLine());
                System.out.println("enter password");
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
                    System.out.println("your account not exists");
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
            System.out.println("innvalid account number");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        
    }
    public static  void transfer(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("enter the your (sender) account no");
            int senderAccNo=Integer.parseInt(scanner.nextLine());
            System.out.println("enter your(Sender) password");
            String senderPassword=scanner.nextLine();
            System.out.println("enter receiver account no");
            int receiverAccNo=Integer.parseInt(scanner.nextLine());
            
            System.out.println("enter Amount");
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
                System.out.println("sender and receiver not exists.. enter valid details");
            }
            else if(senderObj==null){
                System.out.println("sender not exists");
            }
            else if(receiverObj==null){
                System.out.println("receiver accno not exists");
            }
            else if(senderObj.viewbalance()<amount){
                System.out.println("you don't have a sufficient amount");
            }
            else{
                senderObj.reduceAmount(amount);
                receiverObj.addAmount(amount);
                System.out.println("transaction success");
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
            System.out.println("invalid accno and amount .. check the details");

        }
        catch(Exception e){
            System.out.println(e.getMessage());;

        }
        
    }
}