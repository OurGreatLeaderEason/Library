public class Book{
    private String title;
    public int index;
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
    
    public int getIndex(){
        return this.index;
    }
    public void setIndex(int index){
        this.index=index;
    }
    
    public void setVisibility(boolean visibility){
        this.visible=visibility;
    }
    
    
   
    
}