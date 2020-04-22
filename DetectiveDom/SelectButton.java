import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class selectButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectButton extends Actor
{
 
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
        if( ((MyWorld)getWorld()).getObjects(TextMsg.class).size()>0/*
        && !((MyWorld)getWorld()).currentEvidence.equals(null)*/){
            ((MyWorld)getWorld()).getObjects(TextMsg.class).get(0).destroy();
            ((MyWorld)getWorld()).commitEvidence();
            
            
        }
        
    }
    
}
}
