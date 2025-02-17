import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void writeTransactionToFile(int accno, String transactionDetails) {
        String fileName = accno + "history.txt";
        try {
            FileWriter writer = new FileWriter(fileName, true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer) // Use BufferedWriter for safety
            ) {
                System.out.println("Writing to file: " + new File(fileName).getAbsolutePath());
                bufferedWriter.write(transactionDetails);
                bufferedWriter.newLine(); // Ensure line is added
                bufferedWriter.flush(); // Force writing to file
                // Ensure it's closed properly
            }
            
            System.out.println("Transaction added to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

   
    public static void readTransactionHistory(int accNo) {
        String fileName=accNo+"history.txt";
        try {
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("file reading Started");
            while((line=reader.readLine())!=null){
                System.out.println(line);
            }


        }catch(IOException e){
            System.out.println("error readinggg file");
        }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
   
}

    

