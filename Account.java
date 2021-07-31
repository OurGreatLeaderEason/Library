import java.util.ArrayList;
public class Account{
    private String username;
    private String password;
    private boolean status;
    private ArrayList<Book> books;
    
    /**
     * ??????
     * 
     * @param  name  ???? 
     * @param  pass  ??
     * @param  admin  ??????
     */
    public Account(String name, String pass, Boolean status){
        this.username=name;
        this.password=pass;
        this.status=status;
   
        
    }
    /**
     * ????
     * 
     * @return ??
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * ?????
     * 
     * @return  ???
     */
    public String getUsername(){
        return this.username;
    }
    
    
    
    
    public boolean isAdmin(){
        return this.status;
    }
    
    public void setBoosk(ArrayList<Book> books){
        this.books=books;
    }
    
    public ArrayList<Book> getBooks(){
        return this.books;
    }
    
    public void addBook(Book book){
        this.books.add(book);
    }
    
    public String toString(){
        String text="";
        text+=this.username+" "+this.password+" "+this.status;
        return text;
    }
   
    
    
    
}