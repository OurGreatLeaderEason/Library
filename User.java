import java.util.ArrayList;
public class User extends Account{
    private boolean status;
    private ArrayList<Book> books;
    public User(String name, String password){
        super(name, password);
        this.status=false;
        this.books=new ArrayList<Book>();
    }
    
    public void setBooks(ArrayList<Book> books){
        this.books=books;
    }
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
}