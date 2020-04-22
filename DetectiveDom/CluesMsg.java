import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CluesTxt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CluesMsg extends Message
{
    String description;
    String text;
    /**
     * Act - do whatever the CluesTxt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this) /*&& ((MyWorld)getWorld()).cluesInteraction*/){

            //if(((MyWorld)getWorld()).getObjects(TextMsg.class).size()>0 
           // ||((MyWorld)getWorld()).getObjects(TextMsg.class).size()==0){

                ((MyWorld)getWorld()).showClueInfo(description);
                if(((MyWorld)getWorld()).notebook.getWorld()!= null){
                    ((MyWorld)getWorld()).continueSound.play();
                    if(((MyWorld)getWorld()).getObjects(SelectButton.class).size()>0){
                        ((MyWorld)getWorld()).currentEvidence= text;

                    }
                }

           // }

            //if()
            //    ((MyWorld)getWorld()).showClueInfo(description);

            //((MyWorld)getWorld()).removeObject(((MyWorld)getWorld()).displayedText);
            //((MyWorld)getWorld()).notebookList.remove(this.text);
            //((MyWorld)getWorld()).giveInput("Q: " + this.text);
            //((MyWorld)getWorld()).notebookList.remove(text);
            // getWorld().removeObject(this);
        }

    }    

    public CluesMsg(String text)
    {
        int [] space = {0,0};
        this.text = text;
        String modifiedText = text;
        StringBuilder phrase = new StringBuilder(text);
        int size = text.length();
        int charPlace = 30;
        int lastPlaceDone = 0;
        //modifiedText.replaceAll("(.{25})", "$1\n");

        for (int i = 0; i < size; i++) {

            // modifiedText.replaceAll("(.{50})", "$1\n");
            if (modifiedText.charAt(i) == ' ') {
                space[0] = i;

            }
            if (i != 0 && i % charPlace == 0 && modifiedText.charAt(i) == ' ') {
                lastPlaceDone = i;
                modifiedText = modifiedText.substring(0, i) + modifiedText.substring(i + 1, modifiedText.length());
                modifiedText = modifiedText.substring(0, i) + "\n" + modifiedText.substring(i, modifiedText.length());
                //size++;
                
                //space[0] = 0;
            } else if ( (i - lastPlaceDone) >= charPlace && i > charPlace && i % charPlace != 0 && (i - (i % charPlace)) / charPlace > 0) {
                //System.out.print("a");
                modifiedText = modifiedText.substring(0, space[0])+ modifiedText.substring(space[0] + 1, modifiedText.length());
                modifiedText = modifiedText.substring(0, space[0]) + "\n"+ modifiedText.substring(space[0], modifiedText.length());
                //size++;
                //i++;
                lastPlaceDone = space[0];
                //space[0] = 0;
            }
            //size = modifiedText.length();
            //i++;

        }//end of for loop
        GreenfootImage gi= new GreenfootImage( modifiedText, 18, Color.WHITE, Color.BLACK);
        setImage(gi);

        
    }

    public void setInteraction(boolean b){

    } 
}
