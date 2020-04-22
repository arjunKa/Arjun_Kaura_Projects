import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NotebookMsg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NotebookMsg extends Message
{
   public String text;
    public String finalText;
    World world;
    
    /**
     * Act - do whatever the message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this) && ((MyWorld)getWorld()).getObjects(TextMsg.class).size()==0
        &&((MyWorld)getWorld()).getObjects(Lie.class).size()==0 && ((MyWorld)getWorld()).getObjects(SelectButton.class).size()==0 )
        {
      
           ((MyWorld)getWorld()).giveInput("Q: " + this.text);
           ((MyWorld)getWorld()).overallNotebookList.remove(((MyWorld)getWorld()).witnessName + ":" +text);
          // if(((MyWorld)getWorld()).overallNotebookList.contains(((MyWorld)getWorld()).witnessName + ":" +text))
          // ((MyWorld)getWorld()).addObject(new TextMsg("ssss"), 40,40);
           ((MyWorld)getWorld()).currentQ=this.text;
           ((MyWorld)getWorld()).continueSound.play();
            getWorld().removeObject(this);
        }
     
        
    }    
    
    public NotebookMsg(String text)
     {
        int wordSize = 0;

        this.text = text;
    
       
        GreenfootImage gi= new GreenfootImage( text, 20, Color.WHITE, Color.BLACK);
        setImage(gi);
    }
    
    //public void setInteraction(boolean b){
        //interaction = b;
        
   // }

}
