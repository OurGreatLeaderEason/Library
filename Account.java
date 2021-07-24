import java.util.ArrayList;
public class Account{
    private String username;
    private String password;
    private boolean status;
    
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
    
    public boolean search(ArrayList<Book> books, Book book){
        int lang=books.size();
        for (int i=0; i<lang; i++){
            if (books.get(i).getTitle().equals(book.getTitle())){
                return true;
            }
        }
        return false;
        
    }
    
    public int getIndex(ArrayList<Book> library, Book book){
        for (int i=0; i<library.size(); i++){
            if (library.get(i).getTitle().equals(book.getTitle())){
                return i;
            }
        }
        return 0;
    }
    
    public boolean isAdmin(){
        return this.status;
    }
    
   
    
    
    
}