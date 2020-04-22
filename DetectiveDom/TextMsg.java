import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class textMsg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextMsg extends Message
{
    public String text;

    World world;

    /**
     * Act - do whatever the message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            destroy();
            //((MyWorld)getWorld()).removeObject(((MyWorld)getWorld()).displayedText);

        }
    }        

    public TextMsg(String text)
    {
        int wordSize = 0;
        int [] space = {0,0};

        this.text = text;
        String modifiedText = text;
        StringBuilder phrase = new StringBuilder(text);
        int size = text.length();
        int charPlace = 65;
        int lastPlaceDone = 0;
        //modifiedText.replaceAll("(.{25})", "$1\n");

        for (int i = 0; i < size; i++) {

            // modifiedText.replaceAll("(.{50})", "$1\n");
            if (modifiedText.charAt(i) == ' ') {
                space[0] = i;

            }
            if ( (i - lastPlaceDone) >= charPlace && i >= charPlace /*&& i % charPlace != 0 && (i - (i % charPlace)) / charPlace > 0*/) {
                //System.out.print("a");
                modifiedText = modifiedText.substring(0, space[0])+ modifiedText.substring(space[0] + 1, modifiedText.length());
                modifiedText = modifiedText.substring(0, space[0]) + "\n"+ modifiedText.substring(space[0], modifiedText.length());
                //size++;
                //i++;
                lastPlaceDone = space[0];
                i = lastPlaceDone;
                //space[0] = 0;
            }
            //size = modifiedText.length();
            //i++;

        }//end of for loop
        GreenfootImage gi= new GreenfootImage( modifiedText, 20, Color.WHITE, Color.BLACK);
        setImage(gi);
    }

    public void destroy(){
        //if we are in interrogation mode
        if(((MyWorld)getWorld()).peopleList.contains(this.text)){
            ((MyWorld)getWorld()).continueSound.play();
            ((MyWorld)getWorld()).switchToPerson(this.text);
            // getWorld().removeObjects((getWorld().getObjects(TextMsg.class)));

            //if we're in main menu
        }else if(((MyWorld)getWorld()).notebook.getWorld()==null){
            //if(this.text
            ((MyWorld)getWorld()).continueSound.play();
            getWorld().removeObject(this);

            //send input of the line spoken to  prceed to next line
        }else /*if(((MyWorld)getWorld()).getObjects(Lie.class).size()==0)*/{
            ((MyWorld)getWorld()).giveInput(this.text);
            ((MyWorld)getWorld()).continueSound.play();
            getWorld().removeObject(this);
        }

    }
    public void sendInput(){
    }
}