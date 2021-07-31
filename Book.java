public class Book{
    private String title;
   
    private boolean visible;
    private String info;
    /**
     * 
     * 
     * @param  title  
     * @param  author  
     * @param  visible  
     */
    public Book(String title, String info, boolean visible){
        this.title=title;
        this.info=info;
        this.visible=visible;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public boolean getVisibility(){
        return this.visible;
    }
    
    public String getInfo(){
        return this.info;
    }
   
    
}