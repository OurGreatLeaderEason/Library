import java.util.ArrayList;
public class User extends Account{
    private boolean status;
    private ArrayList<Book> books;
    public User(String name, String password){
        super(name, password);
        this.status=true;
    }
    
    public void setBooks(ArrayList<Book> books){
        this.books=books;
    }
    
    public ArrayList<Book> borrow(ArrayList<Book> library, Book book){
        boolean availability=this.search(library, book);
        if (availability==true && library.size()>0){
            int index=this.getIndex(library, book);
            library.remove(index);
            this.books.add(book);
            return library;
        }
        else{
            System.out.println("The book cannot be found");
            return library;
        }
    }
    
    public ArrayList<Book> returnBook(ArrayList<Book> library, Book book){
        boolean availability=search(this.books, book);
        if (availability==true){
            int index=this.getIndex(this.books, book);
            this.books.remove(index);
            library.add(book);
            return library;
        }
        else{
            System.out.println("You don't have this book");
            return library;
        }
            
        
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