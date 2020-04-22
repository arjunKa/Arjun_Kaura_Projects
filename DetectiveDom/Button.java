import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    Lie lieButton;
    Truth truthButton;
    Doubt doubtButton;
    String text;
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /*
        if(Greenfoot.mouseClicked(this) && ((MyWorld)getWorld()).interaction){
           if(Greenfoot.mouseClicked(truthButton))
           text = "truth";
           if(Greenfoot.mouseClicked(lieButton))
           text = "lie";
           if(Greenfoot.mouseClicked(doubtButton))
           text = "doubt";
            ((MyWorld)getWorld()).continueQuestion(this.text);
            
            getWorld().removeObject(this);
        }*/
    }
    
    public void addedToWorld(){
       lieButton = new Lie();
      truthButton = new Truth();
     doubtButton = new Doubt();
        ((MyWorld)getWorld()).addOptionsToWorld();
        
        
        
        
    }
    
}
