import java.util.ArrayList;
public class main{
    public static void main(String args[]){
        
        UI ui=new UI(new ArrayList<Book>(), new ArrayList<Book>(), new ArrayList<Account>());
        ui.main();
    }
}