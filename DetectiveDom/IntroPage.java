import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroPage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroPage extends Actor
{
    /**
     * Act - do whatever the IntroPage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
       
       
        
        ((MyWorld)getWorld()).removeObject(this);
    }
    }    
}
