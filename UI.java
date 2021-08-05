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
        System.out.println("What would you like to do?\n1. Display all available books\n2. Search book\n3. View my books\n4. Log off");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayVisible();
        }
        else if(choice.equals("2")){
            this.search();
        }
        
        else if(choice.equals("3")){
            this.viewMyBooks();
        }
        else if(choice.equals("4")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("Not an option, please try again");
            this.userPage();
        }
    }
    
    public void displayVisible(){
        int cunt=0;
        for(int i=0; i<this.library.size(); i++){
                this.library.get(i).setIndex(0);
        }
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
        System.out.println("This book has been borrowed");
        String choice=kbReader.next();
        this.displayVisible();
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
        System.out.println("This book has been borrowed");
        this.userPage();
    }
    public void viewMyBooks(){
        if(this.currentUser.getBooks().size()==0){
            System.out.println("It appears that you don't have any books");
            this.userPage();
        }
        else{
            int count=0;
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                count+=1;
                System.out.println(count+". "+this.currentUser.getBooks().get(i).getTitle());
                this.currentUser.getBooks().get(i).setIndex(count);
            }
        this.myOptions();
        }
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
            
            this.returned.add(this.selectedBook);
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                if(this.currentUser.getBooks().get(i)==this.selectedBook){
                    this.currentUser.getBooks().remove(i);
                }
            }
            this.selectedBook=null;
            System.out.println("The book has been returned");
            this.myOptions();
        }
        else if(choice.equals("2")){
            this.myOptions();
        }
        else{
            System.out.println("Not an option, please try again");
            this.returnOptions();
        }
    }
    //Admin------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void adminPage(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Display all books\n2. Search Books\n3. View Returned Books\n4. Log off");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayAllBooks();
        }
        else if(choice.equals("2")){
            this.adminSearch();
        }
        else if(choice.equals("3")){
            this.displayReturned();
        }
        else if(choice.equals("4")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("Not an option, please try again");
            this.adminPage();
        }
    }
    //Display Books in the library and options
    public void displayAllBooks(){
        int count=1;
        for(int i=0; i<this.library.size(); i++){
                this.library.get(i).setIndex(0);
        }
        for(int i=0; i<this.library.size(); i++){
            System.out.println(count+". "+this.library.get(i).getTitle());
            this.library.get(i).setIndex(count);
            count+=1;
        }
        this.opciones();
    }
    
    public void opciones(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Select Book");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.selectDisplayBook();
        }
        else{
            System.out.println("Not an option, please try again");
            this.opciones();
        }
    }
    
    public void selectDisplayBook(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Which book would you like to choose?(Enter a number)");
        int choice=kbReader.nextInt();
        int index=choice-1;
        if(choice>=1&&choice<=this.library.size()){
            this.selectedBook=this.library.get(index);
            this.adminDisplayBook();
        }
        else{
            System.out.println("Seemes like this book is unavailable, please try again");
            this.selectDisplayBook();
        }
        
    }
    
    public void adminDisplayBook(){
        System.out.println(this.selectedBook.getInfo());
        System.out.println("Visibility: "+this.selectedBook.getVisibility());
        this.bookOptions();
        
    }
    
    public void bookOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Change Visibility");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.selectDisplayBook();
        }
        else if(choice.equals("2")){
            if(this.selectedBook.getVisibility()==false){
                this.selectedBook.setVisibility(true);
            }
            else{
                this.selectedBook.setVisibility(false);
            }
            System.out.println("This book's visibility is now "+this.selectedBook.getVisibility());
            this.opciones();
        }
        else{
            System.out.println("Not an option, please try again");
            this.opciones();
        }
    }
    //search function for admin
    public void adminSearch(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Enter a book's name");
        String title=kbReader.nextLine();
        int count=0;
        for(int i=0; i<this.library.size(); i++){
            if(title.equals(this.library.get(i).getTitle())){
                this.selectedBook=this.library.get(i);
                this.adminDisplayBook2();
                count+=1;
            }
        }
        if(count==0){
            this.adminSearchUnavailable();
        }
    }
    
    public void adminSearchUnavailable(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Seems like this book is not available, would you like to try again?\n1. Back\n2. Try Again");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.adminSearch();
        }
        else{
            System.out.println("Not an option, please try again");
            this.adminSearchUnavailable();
        }
    }
    
    public void adminDisplayBook2(){
        System.out.println(this.selectedBook.getInfo());
        System.out.println("Visibility: "+this.selectedBook.getVisibility());
        this.bookOptions2();
        
    }
    
    public void bookOptions2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Change Visibility");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminSearch();
        }
        else if(choice.equals("2")){
            if(this.selectedBook.getVisibility()==false){
                this.selectedBook.setVisibility(true);
            }
            else{
                this.selectedBook.setVisibility(false);
            }
            System.out.println("This book's visibility is now "+this.selectedBook.getVisibility());
            this.adminPage();
        }
        else{
            System.out.println("Not an option, please try again");
            this.bookOptions2();
        }
    }
    //Check returned book list
    public void displayReturned(){
        int count=1;
        for(int i=0; i<this.returned.size(); i++){
            System.out.println(count+". "+this.returned.get(i).getTitle());
            this.returned.get(i).setIndex(count);
            count+=1;
        }
        this.returnDecision();
    }
    
    public void returnDecision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do?\n1. Back\n2. Select Book");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.selectReturnedBook();
        }
        else{
            System.out.println("Not an option, please try again");
        }
    }
    
    public void selectReturnedBook(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("Which book?(Enter a number)");
        int choice=kbReader.nextInt();
        if(choice>=1&&choice<=this.returned.size()){
            for(int i=0; i<this.returned.size(); i++){
                if(choice==this.returned.get(i).getIndex()){
                    this.selectedBook=this.returned.get(i);
                    this.displayreturnedbook();
                }
            }
        }
        else{
            System.out.println("This book does is not returned, please try again");
            this.adminPage();
        }
    }
    
    public void displayreturnedbook(){
        System.out.println(this.selectedBook.getInfo());
        this.finaldecision();
    }
    
    public void finaldecision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("What would you like to do with this book?\n1. Back\n2. Return to Library");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayReturned();
        }
        else if(choice.equals("2")){
            this.library.add(this.selectedBook);
            for(int i=0; i<this.returned.size(); i++){
                if(this.selectedBook.getTitle().equals(this.returned.get(i).getTitle())){
                    this.returned.remove(i);
                }
            }
        }
        else{
            System.out.println("Not an option, please try again");
            this.finaldecision();
        }
    }
    
    
}