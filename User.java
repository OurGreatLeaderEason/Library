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
    
    
    
    
    
    public String toString(){
        String text="";
        for (int i=0; i<this.books.size(); i++){
            text+=this.books.get(i).getTitle()+"\n";
        }
        return text;
    }
    
    public void display(){
        for (Book book:books){
            System.out.println(book.getTitle()+"\n");
        }
    }
    
    
    
    
    
    
}