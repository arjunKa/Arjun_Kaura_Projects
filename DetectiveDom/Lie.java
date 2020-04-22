import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lie extends Button
{
    String text = "lie";
    /**
     * Act - do whatever the Lie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
          if(Greenfoot.mouseClicked(this)){
          ((MyWorld)getWorld()).buttonSound.play();
           if( ((MyWorld)getWorld()).getObjects(TextMsg.class).size()!=0){
          
               ((MyWorld)getWorld()).getObjects(TextMsg.class).get(0).destroy();
            }
            ((MyWorld)getWorld()).chooseResponse(this.text);
            //getWorld().removeObject(this);
        }
    }    
}
