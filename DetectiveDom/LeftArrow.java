import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeftArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeftArrow extends Actor
{
    /**
     * Act - do whatever the LeftArrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            ((MyWorld)getWorld()).displayClues();
            ((MyWorld)getWorld()).count = 0;
        }
    }    
}
