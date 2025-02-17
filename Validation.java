public class Validation {
    public static Boolean  nameCheck(String name){
        if(name.length()<=2){
            System.out.println("your name contains greater than three letters");
            return false;
        }
        else if(name.matches(".*\\d.*")){
            System.out.println("your name  contains digits");
            return false;
        }
        else if(name.matches(".*[!@#$%^&*()-_+=}{];:''.,}].*")){
            System.out.println("your name contains special characters");
            return false;
        }
        return true;
    }
    public static Boolean passwordCheck(String password){
        if(password.length()<8){
            System.out.println("your password atleast contains 8 characters");
            return false;
        }
        else if(!password.matches(".*[A-Z].*")){
            System.out.println("password contains atleast one uppercase letters");
            return false;
        }
        else if(!password.matches(".*[a-z].*")){
            System.out.println("password contains atleast one lowercase letters");
            return false;
        }
        else if(!password.matches(".*\\d.*")){
            System.out.println("password contains atleast one digits");
            return false;
        }
        return true;
    }
}
