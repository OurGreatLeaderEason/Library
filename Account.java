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
    public Account(String name,  String pass){
        this.username=name;
        this.password=pass;
   
        
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
     * ????
     * 
     * @param  newPassword  ????
     */
    public void setPassword(String newPassword){
        this.password=newPassword;
        
    }
    /**
     * ?????
     * 
     * @return  ???
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * ?????
     * 
     * @param  newUsername  ?????
     */
    public void setUsername(String newUsername){
        this.username=newUsername;
    }
    
    public void removeBook(Book book){
        boolean tf=this.search(book);
        if(tf==true){
            this.books.remove(this.getIndex(book));
        }
        else{
            System.out.println("You don't have this book");
        }
        
    }
    
    public boolean search(Book book){
        int lang=this.books.size();
        for (int i=0; i<lang; i++){
            if (books.get(i).getTitle().equals(book.getTitle())){
                return true;
            }
        }
        return false;
        
    }
    
    public int getIndex(Book book){
        for (int i=0; i<this.books.size(); i++){
            if (this.books.get(i).getTitle().equals(book.getTitle())){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public boolean isAdmin(){
        return this.status;
    }
    
    public void addBook(Book book){
        this.books.add(book);
    }
    
    public ArrayList<Book> getBooks(){
        return this.books;
    }
    
   
    
    
    
}