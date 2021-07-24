public class Book{
    private String title;
    private String author;
    private boolean visibleAll;
    /**
     * 
     * 
     * @param  title  
     * @param  author  
     * @param  visible  
     */
    public Book(String title, String author, boolean visible){
        this.title=title;
        this.author=author;
        this.visibleAll=visible;
    }
    public String getTitle(){
        return this.title;
    }
    public boolean getVisibility(){
        return this.visibleAll;
    }
    public String getAuthor(){
        return this.author;
    }
    
}