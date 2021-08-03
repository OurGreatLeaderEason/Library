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
    //to be implemented
    public void userPage(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Display all available books\n2. Search book\n3. Return book\n4. View my books\n5. Log off");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayVisible();
        }
        else if(choice.equals("2")){
            this.search();
        }
        else if(choice.equals("3")){
        }
        else if(choice.equals("4")){
            this.viewMyBooks();
        }
        else if(choice.equals("5")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("Not an option, please try again");
            this.userPage();
        }
    }
    //to be implemented
    public void adminPage(){
    }
    public void displayVisible(){
        int cunt=0;
        
        for(int i=0; i<this.library.size(); i++){
            
            if(this.library.get(i).getVisibility()==true){
                cunt+=1;
                System.out.println(cunt+". "+this.library.get(i).getTitle());
                this.library.get(i).setIndex(cunt);
            }
        }
        this.displayVoptions();
        
        
    }
    public void displayVoptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Select Book");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.userPage();
        }
        else if(choice.equals("2")){
            System.out.println("Which book?(Enter a number)");
            int index=kbReader.nextInt();
            int count=0;
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i).getIndex()==index){
                    this.selectedBook=this.library.get(i);
                    count+=1;
                    this.displayBook();
                }
            }
            if(count==0){
                System.out.println("Seems like this book is not available, please try again");
                this.displayVoptions();
            }
        }
        else{
            System.out.println("Not an option, please try again");
            this.displayVoptions();
        }
    }
    
    public void displayBook(){
        System.out.println(this.selectedBook.getTitle());
        System.out.println(this.selectedBook.getInfo());
        this.decision();
    }
    public void decision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Borrow\n2. Back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            
            //this.currentUser.addBook(this.selectedBook);
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i)==this.selectedBook){
                    this.library.remove(i);
                }
            }
            //this.displayVisible();
            this.currentUser.addBook(this.selectedBook);
            this.message();
        }
        else if(choice.equals("2")){
            this.displayVoptions();
        }
        else{
            System.out.println("Not an option");
            this.decision();
        }
    }
    public void message(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("This book has been borrowed\n1. Back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayVisible();
        }
        else{
            System.out.println("Not an option");
            this.message();
        }
    }
    public void search(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Please enter the book's title");
        String title=kbReader.nextLine();
        int count=0;
        for(int i=0; i<this.library.size(); i++){
            if(this.library.get(i).getTitle().equals(title)){
                count+=1;
                this.selectedBook=this.library.get(i);
                this.displayBook2();
            }
        }
        if(count==0){
            this.choice();
        }
    }
    public void choice(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Seems like this book is unavailable at this moment, what would you like to do?\n1. Try again\n2. Back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.search();
        }
        else if(choice.equals("2")){
            this.userPage();
        }
        else{
            System.out.println("Not an option, please try again");
            this.choice();
        }
    }
    public void displayBook2(){
        
        System.out.println(this.selectedBook.getInfo());
        this.decision2();
    }
    public void decision2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Borrow\n2. Back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            
            //this.currentUser.addBook(this.selectedBook);
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i)==this.selectedBook){
                    this.library.remove(i);
                }
            }
            //this.displayVisible();
            this.currentUser.addBook(this.selectedBook);
            this.message2();
        }
        else if(choice.equals("2")){
            this.userPage();
        }
        else{
            System.out.println("Not an option");
            this.decision2();
        }
    }
    public void message2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("This book has been borrowed\n1. Back");
        this.userPage();
    }
    public void viewMyBooks(){
        int count=0;
        for(int i=0; i<this.currentUser.getBooks().size(); i++){
            count+=1;
            System.out.println(count+". "+this.currentUser.getBooks().get(i).getTitle());
            this.currentUser.getBooks().get(i).setIndex(count);
        }
        this.myOptions();
    }
    public void myOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Select Book");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.userPage();
        }
        else if(choice.equals("2")){
            System.out.println("Which book?(Enter a number)");
            int index=kbReader.nextInt();
            int count=0;
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                if(this.currentUser.getBooks().get(i).getIndex()==index){
                    this.selectedBook=this.currentUser.getBooks().get(i);
                    count+=1;
                    this.viewMyBook();
                }
            }
            if(count==0){
                System.out.println("Seems like this book is not available, please try again");
                this.myOptions();
            }
        }
        else{
            System.out.println("Not an option, please try again");
            this.myOptions();
        }
    }
    public void viewMyBook(){
        System.out.println(this.selectedBook.getTitle());
        this.returnOptions();
    }
    
    public void returnOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("1. Return\n2. Back");
        String choice=kbReader.next();
        if(choice.equals("1")){
            /*
            this.library.add(this.selectedBook);
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                if(this.currentUser.getBooks().get(i)==this.selectedBook){
                    this.currentUser.getBooks().remove(i);
                }
            }
            this.selectedBook=null;
            System.out.println("This book has been returned");
            */
            System.out.println("Return");
        }
        else if(choice.equals("2")){
            this.myOptions();
        }
        else{
            System.out.println("Not an option, please try again");
            this.returnOptions();
        }
    }
    
    
        
    
    
}