import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextArea here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextArea extends Actor
{
    /**
     * Act - do whatever the TextArea wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
    TextArea(){
        //getWorld().addObject(new ContinueButton(), getX()+ getImage().getWidth()/2, getY()+ getImage().getHeight()/2);
        
        
        
        
    }
    public void removeText(){
       //  getWorld().addObject(new ContinueButton(), getX()+ getImage().getWidth()/2, getY()+ getImage().getHeight()/2);
       	((TextMsg)getOneIntersectingObject(TextMsg.class)).destroy();
       // removeTouching(TextMsg.class);
        
        
    }
    
    public boolean isNearText(){
        return isTouching(TextMsg.class);
        
        
    }
}
