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
        boolean on=true;
        while(on==true){
            Scanner kbReader=new Scanner(System.in);
            System.out.println("\nWelcome to the library");
            System.out.println("1. Register\n2. Login\n3. Power off");
            String option=kbReader.next();
            if(option.equals("1")){
                this.register();
            }
            else if(option.equals("2")){}
            else if(option.equals("3")){
                System.out.println("Powered Off");
                break;
            }
            else{
                System.out.println("Not a valid option");
            }
            
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
        for(int i=0; i<accounts.size(); i++){
            if(this.accounts.get(i).equals(username)){
                duplicate+=1;
            }
        }
        System.out.println(duplicate);
        if(duplicate==0){
            System.out.println("Please enter a password");
            String password=kbReader.next();
            Account account=new Account(username, password, status);
            this.accounts.add(account);
            System.out.println("Registration complete, now returning to the main page");
            this.printUsers();
            this.main();
        }
        else{
            System.out.println("This username already exists, please try again");
            this.register();
        }
    }
    public void printUsers(){
        for(Account accounts: this.accounts){
            System.out.print(accounts);
        }
    }
}