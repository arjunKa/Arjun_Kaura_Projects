import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Actor
{
    /**
     * Act - do whatever the ExitButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            
            if( !((MyWorld)getWorld()).txtArea.isNearText() &&
            ((MyWorld)getWorld()).getObjects(Lie.class).size()==0)
            {
                getWorld().removeObjects((getWorld().getObjects(TextMsg.class)));
                // ((MyWorld)getWorld()).getObjects(TextMsg.class).get(0).destroy();
                ((MyWorld)getWorld()).displayMainUI();
                ((MyWorld)getWorld()).buttonSound.play();
                getWorld().removeObject(this);
            }
            // ((MyWorld)getWorld()).displayMainUI();
            // getWorld().removeObject(this);
        }
    }    
}
