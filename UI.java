import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class UI{
    private ArrayList<Book> library;
    private ArrayList<Book> returned;
    private ArrayList<Account> accounts;
    private Account currentUser;
    private Book selectedBook;
   
    public UI(ArrayList<Book> library, ArrayList<Book> returned, ArrayList<Account> accounts){
        this.library=library;
        this.returned=returned;
        this.accounts=accounts;
        
    }
    public void main(){
        
        
            Scanner kbReader=new Scanner(System.in);
            System.out.println("\n欢迎来到图书馆程序");
            System.out.println("1. 注册\n2. 登录\n3. 关机");
            String option=kbReader.next();
            if(option.equals("1")){
                this.register();
            }
            else if(option.equals("2")){
                this.login();
            }
            else if(option.equals("3")){
                System.out.println("已关机");
                
                
                
                System.exit(0);
            }
            else{
                System.out.println("选项不存在，请重试");
                this.main();
            }
    }
    public void register(){
        boolean status;
        Scanner kbReader=new Scanner(System.in);
        System.out.println("选择账号类型\n1. 管理员\n2. 用户");
        String option=kbReader.next();
        if(option.equals("1")){
            status=true;
        }
        else if(option.equals("2")){
            status=false;
            
        }
        else{
            System.out.println("选项不存在，请重试");
            
            this.register();
            status=true;
        }
        System.out.println("请输入用户名");
        String username=kbReader.next();
        int duplicate=0;
        for(Account account:accounts){
            if(account.getUsername().equals(username)){
                duplicate+=1;
            }
        }
       
        if(duplicate==0){
            System.out.println("请输入密码");
            String password=kbReader.next();
            Account account=new Account(username, password, status);
            this.accounts.add(account);
            System.out.println("注册完成，返回登录页面");
            
            this.main();
        }
        else{
            System.out.println("该用户名已存在");
            this.register();
        }
    }
    public void login(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=kbReader.next();
        int bruh=0;
        for(int i=0; i<this.accounts.size(); i++){
            if(this.accounts.get(i).getUsername().equals(username)){
                bruh+=1;
            }
        }
        if(bruh>0){
            System.out.println("请输入密码");
            String password=kbReader.next();
            for(int i=0; i<this.accounts.size(); i++){
                if(this.accounts.get(i).getUsername().equals(username)){
                    if(password.equals(this.accounts.get(i).getPassword())){
                        System.out.println("登录成功，");
                        this.currentUser=this.accounts.get(i);
                        if(this.currentUser.isAdmin()==true){
                            this.adminPage();
                        }
                        else{
                            this.userPage();
                        }
                    }
                    else{
                        this.wrongPassword();
                    }
                }
                
            }
        }
        else{
            this.wrongUsername();
        }
        
    }
    public void wrongPassword(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("密码错误?\n1. 重试\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.login();
        }
        else if(choice.equals("2")){
            this.main();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.wrongPassword();
        }
    
    }
    public void wrongUsername(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("该用户不存在\n1. 重试\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.login();
        }
        else if(choice.equals("2")){
            this.main();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.wrongUsername();
        }
    }
    //to be implemented
    public void userPage(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("1. 查看所有书籍\n2. 搜索图书\n3. 查看我的图书\n4. 注销");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayVisible();
        }
        else if(choice.equals("2")){
            this.search();
        }
        
        else if(choice.equals("3")){
            this.viewMyBooks();
        }
        else if(choice.equals("4")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.userPage();
        }
    }
    
    public void displayVisible(){
        int cunt=0;
        for(int i=0; i<this.library.size(); i++){
                this.library.get(i).setIndex(0);
        }
        for(int i=0; i<this.library.size(); i++){
            
            if(this.library.get(i).getVisibility()==true){
                cunt+=1;
                System.out.println(cunt+". "+this.library.get(i).getTitle());
                this.library.get(i).setIndex(cunt);
            }
        }
        this.displayVoptions();
        
        
    }
    public void displayVoptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("----------------------\n1. 返回\n2. 选择图书");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.userPage();
            
        }
        else if(choice.equals("2")){
            System.out.println("选那本书?(输入图书编号)");
            int index=kbReader.nextInt();
            int count=0;
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i).getIndex()==index){
                    this.selectedBook=this.library.get(i);
                    count+=1;
                    this.displayBook();
                }
            }
            if(count==0){
                System.out.println("没有这本书");
                this.displayVoptions();
            }
        }
        else{
            System.out.println("选项不存在，请重试");
            this.displayVoptions();
        }
    }
    
    public void displayBook(){
        System.out.println(this.selectedBook.getTitle());
        System.out.println(this.selectedBook.getInfo());
        this.decision();
    }
    public void decision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 借书\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            
            //this.currentUser.addBook(this.selectedBook);
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i)==this.selectedBook){
                    this.library.remove(i);
                }
            }
            //this.displayVisible();
            this.currentUser.addBook(this.selectedBook);
            this.message();
        }
        else if(choice.equals("2")){
            this.displayVoptions();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.decision();
        }
    }
    public void message(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("借书成功");
        String choice=kbReader.next();
        this.displayVisible();
    }
    public void search(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("请输入书名");
        String title=kbReader.nextLine();
        int count=0;
        for(int i=0; i<this.library.size(); i++){
            if(this.library.get(i).getTitle().equals(title)){
                count+=1;
                this.selectedBook=this.library.get(i);
                this.displayBook2();
            }
        }
        if(count==0){
            this.choice();
        }
    }
    public void choice(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("没有这本书\n1. 重试\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.search();
        }
        else if(choice.equals("2")){
            this.userPage();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.choice();
        }
    }
    public void displayBook2(){
        
        System.out.println(this.selectedBook.getInfo());
        this.decision2();
    }
    public void decision2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 借书\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            
            //this.currentUser.addBook(this.selectedBook);
            for(int i=0; i<this.library.size(); i++){
                if(this.library.get(i)==this.selectedBook){
                    this.library.remove(i);
                }
            }
            //this.displayVisible();
            this.currentUser.addBook(this.selectedBook);
            this.message2();
        }
        else if(choice.equals("2")){
            this.userPage();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.decision2();
        }
    }
    public void message2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("借书成功");
        this.userPage();
    }
    public void viewMyBooks(){
        if(this.currentUser.getBooks().size()==0){
            System.out.println("您没有借书");
            this.userPage();
        }
        else{
            int count=0;
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                count+=1;
                System.out.println(count+". "+this.currentUser.getBooks().get(i).getTitle());
                this.currentUser.getBooks().get(i).setIndex(count);
            }
        this.myOptions();
        }
    }
    public void myOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 选择图书");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.userPage();
        }
        else if(choice.equals("2")){
            System.out.println("那本书?(输入编号)");
            int index=kbReader.nextInt();
            int count=0;
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                if(this.currentUser.getBooks().get(i).getIndex()==index){
                    this.selectedBook=this.currentUser.getBooks().get(i);
                    count+=1;
                    this.viewMyBook();
                }
            }
            if(count==0){
                System.out.println("没有这本书，请重试");
                this.myOptions();
            }
        }
        else{
            System.out.println("选项不存在，请重试");
            this.myOptions();
        }
    }
    public void viewMyBook(){
        System.out.println(this.selectedBook.getTitle());
        this.returnOptions();
    }
    
    public void returnOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 还书\n2. 返回");
        String choice=kbReader.next();
        if(choice.equals("1")){
            
            this.returned.add(this.selectedBook);
            for(int i=0; i<this.currentUser.getBooks().size(); i++){
                if(this.currentUser.getBooks().get(i)==this.selectedBook){
                    this.currentUser.getBooks().remove(i);
                }
            }
            this.selectedBook=null;
            System.out.println("还书成功");
            this.myOptions();
        }
        else if(choice.equals("2")){
            this.myOptions();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.returnOptions();
        }
    }
    //Admin------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void adminPage(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("1. 查看所有书籍\n2. 搜索图书\n3. 查看已归还的书籍\n4. 注销");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayAllBooks();
        }
        else if(choice.equals("2")){
            this.adminSearch();
        }
        else if(choice.equals("3")){
            this.displayReturned();
        }
        else if(choice.equals("4")){
            this.currentUser=null;
            this.main();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.adminPage();
        }
    }
    //Display Books in the library and options
    public void displayAllBooks(){
        int count=1;
        for(int i=0; i<this.library.size(); i++){
                this.library.get(i).setIndex(0);
        }
        for(int i=0; i<this.library.size(); i++){
            System.out.println(count+". "+this.library.get(i).getTitle());
            this.library.get(i).setIndex(count);
            count+=1;
        }
        this.opciones();
    }
    
    public void opciones(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 选择书籍");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.selectDisplayBook();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.opciones();
        }
    }
    
    public void selectDisplayBook(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("那本书?(请输入编号)");
        int choice=kbReader.nextInt();
        int index=choice-1;
        if(choice>=1&&choice<=this.library.size()){
            this.selectedBook=this.library.get(index);
            this.adminDisplayBook();
        }
        else{
            System.out.println("没有这本书，请重试");
            this.selectDisplayBook();
        }
        
    }
    
    public void adminDisplayBook(){
        System.out.println(this.selectedBook.getInfo());
        System.out.println("能见度: "+this.selectedBook.getVisibility());
        this.bookOptions();
        
    }
    
    public void bookOptions(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 更改能见度");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.selectDisplayBook();
        }
        else if(choice.equals("2")){
            if(this.selectedBook.getVisibility()==false){
                this.selectedBook.setVisibility(true);
            }
            else{
                this.selectedBook.setVisibility(false);
            }
            System.out.println("这本书的能见度现在是 "+this.selectedBook.getVisibility());
            this.opciones();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.opciones();
        }
    }
    //search function for admin
    public void adminSearch(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("请输入书名");
        String title=kbReader.nextLine();
        int count=0;
        for(int i=0; i<this.library.size(); i++){
            if(title.equals(this.library.get(i).getTitle())){
                this.selectedBook=this.library.get(i);
                this.adminDisplayBook2();
                count+=1;
            }
        }
        if(count==0){
            this.adminSearchUnavailable();
        }
    }
    
    public void adminSearchUnavailable(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("没有这本书\n1. 返回\n2. 重试");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.adminSearch();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.adminSearchUnavailable();
        }
    }
    
    public void adminDisplayBook2(){
        System.out.println(this.selectedBook.getInfo());
        System.out.println("能见度: "+this.selectedBook.getVisibility());
        this.bookOptions2();
        
    }
    
    public void bookOptions2(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 更改能见度");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            if(this.selectedBook.getVisibility()==false){
                this.selectedBook.setVisibility(true);
            }
            else{
                this.selectedBook.setVisibility(false);
            }
            System.out.println("这本书的能见度现在是 "+this.selectedBook.getVisibility());
            this.adminPage();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.bookOptions2();
        }
    }
    //Check returned book list
    public void displayReturned(){
        if(this.returned.size()==0){
            System.out.println("没有已归还图书");
            this.adminPage();
        }
        else{
            int count=1;
            for(int i=0; i<this.returned.size(); i++){
                System.out.println(count+". "+this.returned.get(i).getTitle());
                this.returned.get(i).setIndex(count);
                count+=1;
            }
            this.returnDecision();
        }
    }
    
    public void returnDecision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 选择图书");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.adminPage();
        }
        else if(choice.equals("2")){
            this.selectReturnedBook();
        }
        else{
            System.out.println("选项不存在，请重试");
        }
    }
    
    public void selectReturnedBook(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("那本书?(请输入编号)");
        int choice=kbReader.nextInt();
        if(choice>=1&&choice<=this.returned.size()){
            for(int i=0; i<this.returned.size(); i++){
                if(choice==this.returned.get(i).getIndex()){
                    this.selectedBook=this.returned.get(i);
                    this.displayreturnedbook();
                }
            }
        }
        else{
            System.out.println("这本书没有被归还");
            this.adminPage();
        }
    }
    
    public void displayreturnedbook(){
        System.out.println(this.selectedBook.getInfo());
        this.finaldecision();
    }
    
    public void finaldecision(){
        Scanner kbReader=new Scanner(System.in);
        System.out.println("-------------------\n1. 返回\n2. 入库图书");
        String choice=kbReader.next();
        if(choice.equals("1")){
            this.displayReturned();
        }
        else if(choice.equals("2")){
            this.library.add(this.selectedBook);
            for(int i=0; i<this.returned.size(); i++){
                if(this.selectedBook.getTitle().equals(this.returned.get(i).getTitle())){
                    this.returned.remove(i);
                }
            }
            System.out.println("图书已入库");
            this.displayReturned();
        }
        else{
            System.out.println("选项不存在，请重试");
            this.finaldecision();
        }
    }
    
    
}
