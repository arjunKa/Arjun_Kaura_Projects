import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PeopleButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PeopleButton extends Button
{
    /**
     * Act - do whatever the PeopleButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
          
           //if( ((MyWorld)getWorld()).getObjects(TextMsg.class).size()!=0){
          
          //     ((MyWorld)getWorld()).getObjects(TextMsg.class).get(0).destroy();
           // }
            ((MyWorld)getWorld()).displayPeople();
            ((MyWorld)getWorld()).buttonSound.play();
            getWorld().removeObject(this);
            
        }
    }    
}
