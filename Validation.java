public class Validation {
    public static Boolean  nameCheck(String name){
        if(name.length()<=3){
            System.out.println("Your name should contain greater than three letters");
            return false;
        }
        else if(name.matches(".*\\d.*")){
            System.out.println("Your name contains digits");
            return false;
        }
        else if(name.matches(".*[!@#$%^&*()-_+=}{];:''.,}].*")){
            System.out.println("Your name contains special characters");
            return false;
        }
        return true;
    }
    public static Boolean passwordCheck(String password){
        if(password.length()<8){
            System.out.println("Your password should contain aleast 8 characters");
            return false;
        }
        else if(!password.matches(".*[A-Z].*")){
            System.out.println("Password should contain atleast one uppercase letter");
            return false;
        }
        else if(!password.matches(".*[a-z].*")){
            System.out.println("Password should contain atleast one lowercase letter");
            return false;
        }
        else if(!password.matches(".*\\d.*")){
            System.out.println("Password should contain atleast one digit");
            return false;
        }
        return true;
    }
}
