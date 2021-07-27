import java.util.ArrayList;
public class Admin extends Account{
    private boolean status;
    private ArrayList<Book> books;
    public Admin(String username, String password){
        super(username, password);
        this.status=true;
        this.books=null;
    }
}