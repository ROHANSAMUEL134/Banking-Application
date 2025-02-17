public class Decryption{
    public static String decrypt(String password){
        int n=password.length();
        String decyptString="";
        for(int i=0;i<n;i++){
            if(Character.isUpperCase(password.charAt(i))){
                decyptString+=(char)(((password.charAt(i)-'A'+26-3)%26)+'A');
            }
            else if(Character.isLowerCase(password.charAt(i))){
                decyptString+=(char)(((password.charAt(i)-'a'-3+26)%26)+'a');
            }
            else if(Character.isDigit(password.charAt(i))){
                decyptString+=(char)(((password.charAt(i)-'0'-3+10)%10)+'0');
            }
            else{
                decyptString+=(char)password.charAt(i);
            }
        }
        return decyptString;
    }
}