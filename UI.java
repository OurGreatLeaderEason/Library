import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class UI{
    private ArrayList<Book> library;
    private ArrayList<Book> returned;
    private ArrayList<Account> accounts;
    private Account currentUser;
    private Book selectedBook;
   
    public UI(ArrayList<Book> library, ArrayList<Book> returned, ArrayList<Account> accounts){
        this.library=library;
        this.returned=returned;
        this.accounts=accounts;
        
    }
    public void main(){
        
        
            Scanner kbReader=new Scanner(System.in);
            System.out.println("\nWelcome to the library");
            System.out.println("1. Register\n2. Login\n3. Power off");
            String option=kbReader.next();
            if(option.equals("1")){
                this.register();
            }
            else if(option.equals("2")){
                this.login();
            }
            else if(option.equals("3")){
                System.out.println("Powered Off");
                
                
                
                System.exit(0);
            }
            else{
                System.out.println("Not a valid option");
                this.main();
            }
    }
    public void register(){
        boolean status;
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What kind of account do you wish to create?\n1. Admin\n2. User");
        String option=kbReader.next();
        if(option.equals("1")){
            status=true;
        }
        else if(option.equals("2")){
            status=false;
        }
        else{
            System.out.println("Not an option, please try again");
            
            this.register();
            status=true;
        }
        System.out.println("Please enter a username");
        String username=kbReader.next();
        int duplicate=0;
        for(Account account:accounts){
            if(account.getUsername().equals(username)){
                duplicate+=1;
            }
        }
       
        if(duplicate==0){
            System.out.println("Please enter a password");
            String password=kbReader.next();
            Account account=new Account(username, password, status);
            this.accounts.add(account);
            System.out.println("Registration complete, now returning to the main page");
            
            this.main();
        }
        else{
            System.out.println("This username already exists, please try again");
            this.register();
        }
    }
    public void login(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Please enter your username");
        String username=kbReader.next();
        int bruh=0;
        for(int i=0; i<this.accounts.size(); i++){
            if(this.accounts.get(i).getUsername().equals(username)){
                bruh+=1;
            }
        }
        if(bruh>0){
            System.out.println("Please enter your password");
            String password=kbReader.next();
            for(int i=0; i<this.accounts.size(); i++){
                if(this.accounts.get(i).getUsername().equals(username)){
                    if(password.equals(this.accounts.get(i).getPassword())){
                        System.out.println("Login successful, now redirecting to your home page");
                        this.currentUser=this.accounts.get(i);
                        if(this.currentUser.isAdmin()==true){
                            this.adminPage();
                        }
                        else{
                            this.userPage();
                        }
                    }
                    else{
                        this.wrongPassword();
                    }
                }
                
            }
        }
        else{
            this.wrongUsername();
        }
        
    }
    public void wrongPassword(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("The password is incorrect, what would you like to do?\n1. Try again\n2. Go back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.login();
        }
        else if(choice.equals("2")){
            this.main();
        }
        else{
            System.out.println("Not an option");
            this.wrongPassword();
        }
    
    }
    public void wrongUsername(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("The user cannot be found, what would you like to do?\n1. Try again\n2. Go back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.login();
        }
        else if(choice.equals("2")){
            this.main();
        }
        else{
            System.out.println("Not an option");
            this.wrongUsername();
        }
    }
    public void userPage(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Display all available books\n2. Search book\n3. Return book\n4. View my books\n5. Log off");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayVisible();
        }
        if(choice.equals("2")){
        }
        if(choice.equals("3")){
        }
        if(choice.equals("4")){
        }
        if(choice.equals("5")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("Not an option, please try again");
            this.userPage();
        }
    }
    public void adminPage(){
    }
    public void displayVisible(){
        int a=1;
        for(int i=0; i<this.library.size(); i++){
            a+=i;
            if(this.library.get(i).getVisibility()==true){
                System.out.println(a+". "+this.library.get(i).getTitle());
            }
        }
        
        
    }
    public void displayVoptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Select Book");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.userPage();
        }
        if(choice.equals("2")){
            
        }
        else{
            this.displayVoptions();
        }
    }
    
    public void selectBook(ArrayList<Book> books){
        int a;
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Which book would you like to select?");
        a=kbReader.nextInt();
        a-=1;
        if(a<books.size()&&a>=0){
            this.selectedBook=books.get(a);
        }
        else{
            System.out.println("We don't have that number, please try again");
        }
    }
        
    
    
}