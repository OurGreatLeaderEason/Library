import java.util.ArrayList;
public class Library{
    private ArrayList<Account> accounts;
    private ArrayList<Book> books;
    private Account account;
    
    public Library(ArrayList<Book> books){
        this.accounts=new ArrayList<Account>();
        this.books=books;
        
    }
    //need to implement Admin
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
    
    public void login(String username, String password){
        boolean usernameFound=false;
        for (int i=0; i<this.accounts.size(); i++){
            if(username.equals(this.accounts.get(i).getUsername())){
                if(password.equals(this.accounts.get(i).getPassword())){
                    this.account=this.accounts.get(i);
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
    
    public void logout(){
        this.account=null;
    }
    
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
}