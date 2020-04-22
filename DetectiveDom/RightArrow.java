import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RightArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightArrow extends Actor
{
    /**
     * Act - do whatever the RightArrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(((MyWorld)getWorld()).evidenceHolder.getImage().getHeight() + 
            ((MyWorld)getWorld()).evidenceHolder.getY()> getWorld().getHeight()-50){
                ((MyWorld)getWorld()).displayClues();
                setImage("evidencePlus.png");
            }else {
                ((MyWorld)getWorld()).extendEvidence();
                setImage("evidenceMinus.png");
            }
        }
    }    
}
