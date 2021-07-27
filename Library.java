import java.util.ArrayList;
public class Library{
    private ArrayList<Account> accounts;
    private ArrayList<Book> books;
    private ArrayList<Book> pool;
    private Account account;
    
    public Library(ArrayList<Book> books){
        this.accounts=new ArrayList<Account>();
        this.books=books;
        
    }
    //need to implement Admin
    /**
     * Adds a new account to the arraylist accounts
     */
    public void register(String username, String password, boolean isAdmin){
        if (isAdmin==false){
            int duplicateCount=0;
            for (int i=0; i<this.accounts.size(); i++){
                if (username.equals(this.accounts.get(i).getUsername())){
                    duplicateCount+=1;
                }
            }
            if (duplicateCount>0){
                System.out.println("This username already exists");
            }
            else{
                this.accounts.add(new User(username, password));
            }
            
        }
        
        
    }
    /**
     * Finds a pre-existing account and sets it to the field variable account
     */
    public void login(String username, String password){
        boolean usernameFound=false;
        for (int i=0; i<this.accounts.size(); i++){
            if(username.equals(this.accounts.get(i).getUsername())){
                if(password.equals(this.accounts.get(i).getPassword())){
                    this.account=this.accounts.get(i);
                    System.out.println("Login Successful");
                }
                else{
                    System.out.println("Password is incorrect");
                }
            }
            else{
                System.out.println("The username does not exist");
            }
        }

    }
    /**
     * sets the field variable account to null;
     */
    public void logout(){
        this.account=null;
    }
    /**
     * displays all the books available, hides some books for non-admin
     */
    public void display(){
        if (this.account.isAdmin()==true){
            for(Book book:this.books){
                System.out.println(book.getTitle()+"\n");
            }
        }
        else{
            for(Book book:this.books){
                if(book.getVisibility()==true){
                    System.out.println(book.getTitle()+"\n");
                }
            }
        }
    }
    

    /**
     * Returns the index of a desired book
     * 
     * @param  book  the book in which you want to find the index of
     * @return  i  the index of the desired book
     */
    public int getIndex(Book book){
        for (int i=0; i<this.books.size(); i++){
            if (this.books.get(i).getTitle().equals(book.getTitle())){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
    /**
     * looks for the book in the libary, returns true if present and false if not present
     */
    public boolean search(Book book){
        int lang=this.books.size();
        for (int i=0; i<lang; i++){
            if (books.get(i).getTitle().equals(book.getTitle())){
                return true;
            }
        }
        return false;
        
    }
    
    /**
     * removes a book from the library
     */
    public void removeBook(Book book){
        boolean tf=this.search(book);
        if(tf==true){
            int index=getIndex(book);
            this.books.remove(index);
        }
        else{
            System.out.println("This book does not exist");
        }
    }
    
    /**
     * moves a book from the arraylist library to the account's arraylist 
     * 
     * @param  book  the book that we want to move
     */
    public void borrowBook(Book book){
        if(this.search(book)==true){
            
            this.account.addBook(book);
            removeBook(book);
        }
        else{
            System.out.println("This book does not exist");
        }
    }
    
    /**
     * removes a book from the user's booklist and return it to the pool
     */
    public void returnBook(Book book){
        if(this.account.search(book)==true){
            int index=this.account.getIndex(book);
            this.pool.add(book);
            this.account.removeBook(book);
            
        }
    }
    
    
    
    
    
    
}