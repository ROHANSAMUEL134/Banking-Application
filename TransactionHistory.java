public class TransactionHistory{
    private String sender;
    private int senderAccNo;
    private String receiver;
    private  int receiverAccNo;
    private int amount;
    private  String transferType;

    public TransactionHistory(int senderAccNo,String senderName,int receiverAccNo,String receiverName,String transferType,int amount) {
        this.senderAccNo=senderAccNo;
        this.sender=senderName;
        this.receiver=receiverName;
        this.receiverAccNo=receiverAccNo;
        this.transferType=transferType;
        this.amount=amount;
    }
    public int getSenderAccNo(){
        return this.senderAccNo;
    } 
    public int getReceiverAccNo(){
        return this.receiverAccNo;
    }
    public String getSenderName(){
        return this.sender;
    }
    public String getReceiverName(){
        return this.receiver;
    }
    public String getTransferType(){
        return this.transferType;
    }
    public int amount(){
        return this.amount;
    }
}