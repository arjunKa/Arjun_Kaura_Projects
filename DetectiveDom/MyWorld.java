import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;
import java.awt.FileDialog;
import java.io.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    String fileName = "dialogue.txt";
    GreenfootSound continueSound = new GreenfootSound("sound_typewriterClick.wav");
    //GreenfootSound correctChoiceSound = new GreenfootSound("sound_correctChoice.wav");
    GreenfootSound correctChoiceSound = new GreenfootSound("sound_correct.wav");
    GreenfootSound incorrectChoiceSound = new GreenfootSound("sound_incorrect.wav");
    GreenfootSound buttonSound = new GreenfootSound("sound_buttonClick.wav");
    ImageHolder imgHolder = new ImageHolder();
    //  List notebookList = new ArrayList();
    List <String> overallNotebookList  = new ArrayList();
    List notebookListObj = new ArrayList();
    List linesDone = new ArrayList();
    List cluesList = new ArrayList();
    List cluesListObj = new ArrayList();
    List cluesListDesc = new ArrayList();
    List uiOptions = new ArrayList();
    List peopleList = new ArrayList();
    List peopleListObj = new ArrayList();
    List peopleListSpeech = new ArrayList();
    List unlockedSpeech = new ArrayList();

    //boolean clearDisplay = false;
    
   RightArrow extensionSymb = new RightArrow();
    int interStage = 0;
    Notebook notebook;
    String prevLine;
    String line = "null";
    TextArea txtArea;
    int linePosition = -1;
    int qPos = -1;
    int speechCount = 0;
    Message displayedText;
    ContinueButton contButton;

    Lie lieButton;
    Truth truthButton;
    Doubt doubtButton;

    MainUI mainUI;
    int currentEncounter= 0;
    String lineMemory = "null";
    Button button;
    //Boolean ntbkInteraction = true;
    Boolean cluesInteraction = false;
    String chosenOption;
    String witnessName;
    String correctAns;
    String currentEvidence = null;
    EvidenceHolder evidenceHolder;
    String currentQ;
    String contButtonString;
    SelectButton selectButton;
    ExitButton exitButton;
    UiSide ui = new UiSide();
    String arrayOfText[];
    

    UiSide2 uiSide2;
    UiSide uiSide;
    int count = 9;
    int globalCount;

    public void act() 
    {
        //  if(Greenfoot.mouseClicked(Message.class))
        //     getObjectsAt(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY(), Message.class).get(0).destroy();

    }

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        arrayOfText = getFileText("initialSetup.txt");
        prepare();

    }

    public void addInteractionButton(Actor a){
        if(getObjects(ContinueButton.class).size()>0)
            removeObject(contButton);
        else if(getObjects(SelectButton.class).size()>0)
            removeObject(selectButton);
        addObject(a, txtArea.getX()+ txtArea.getImage().getWidth()/2 - a.getImage().getWidth()/2, 
            txtArea.getY()+ txtArea.getImage().getHeight()/2 - a.getImage().getHeight()/2);

    }

    public void readTxtFile(String input){

        //Create object of FileReader
        // FileReader inputFile = new FileReader(fileName);

        //Instantiate the BufferedReader Class
        //   BufferedReader bufferReader = new BufferedReader(inputFile);

        // Read file line by line and print on the console
        int count=-1;
        //while (count!= getFileText(fileName + ".txt").length-1)   {

        while (count!= arrayOfText.length-1)   {
            count++;
            line = arrayOfText[count];

            if(line.equals(input) ){

                //adds the line you just said or read form someone else to the memory
                //linesDone.add(input);
                //the line looked at now is the seubsequent line to the one we just put in memory
                count++;
                line =  arrayOfText[count];
                if(input.equals("Question:"+ Integer.toString(interStage))){
                    addtoOverallNtbk(witnessName + ":" + line);
                    //notebookList.add(line);

                }else if(input.equals("Clues:")){

                    cluesList.add(line);
                    cluesListObj.add(new CluesMsg((String)cluesList.get(cluesList.size()-1)));
                    count++;
                    line =  arrayOfText[count];
                    cluesListDesc.add(line);
                    displayClues();

                }else if (input.equals("Witness Name:")){

                    witnessName = line;

                }else if (input.equals("Speech"+ Integer.toString(0))){
                    //linesDone.add(line);
                    //lineMemory = (String)linesDone.get(linesDone.size()-1);
                    // count++;
                    // line =  arrayOfText[count];
                    //removeObjects(getObjects(TextMsg.class));
                    //witnessName = 
                    displayText(line);
                    addtoOverallNtbk("Speech" + Integer.toString(0) +witnessName);
                    //addObject(new CluesMsg(line), 10 +530/2, 15 +200);
                    //iff the next line after the one you just saw is another dialogue, then show that dialogue
                }else if (input.contains("unlockedSpeech:")){
                    unlockedSpeech.remove(unlockedSpeech.get(0));
                    displayText(line);
                }else if(input.equals("GetWitnessList")){
                    do{
                        addPerson(line);
                        //peopleList.add(line);
                        count++;
                        line =  arrayOfText[count];
                    }while(!line.equals("end"));

                }else if(input.equals("Incorrect: chose lie: "+currentQ)){
                    //line = bufferReader.readLine();
                    //addObject(new TextMsg(currentEvidence), 50,50);
                    displayText(line);
                    incorrectChoiceSound.play();
                    
                }else if(lineMemory.equals(input) && !chosenOption.equals("lie")){
                    //goes to the answer
                    count++;
                    line =  arrayOfText[count];

                    if(line.equals(chosenOption)){

                        count++;
                        line =  arrayOfText[count];
                        //addObject(new MainUI(), 50,50);
                        correctChoiceSound.play();
                        displayText(line);
                    }else if(chosenOption.equals("truth")){
                        giveInput("Incorrect: chose truth: " + currentQ);
                        incorrectChoiceSound.play();
                    }else if(chosenOption.equals("doubt")){

                        giveInput("Incorrect: chose doubt: " + currentQ);
                        incorrectChoiceSound.play();
                    }
                    
                }else if(line.equals("(unlock speech)")){
                    
                    //giveInput(line);
                    do{
                        count++;
                        line =  arrayOfText[count];
                        unlockedSpeech.add(line);
                        count++;
                        line =  arrayOfText[count];
                    }while(!line.equals("cont'd"));
                    if(line.equals("cont'd")){
                        count++;
                        line =  arrayOfText[count];
                        displayText(line);
                    
                    }
                }else if(line.equals("(add person)")){
                    count++;
                    line = arrayOfText[count];
                    addPerson(line);
                    //refreshPeople();
                
                }else if(line.contains("(unlockSpeechNow)")){
                    count++;
                    line =  arrayOfText[count];
                    giveInput("(unlockedSpeechNow) " + line);

                }else if(line.contains("(unlockedSpeechNow)")){
                    count++;
                    line =  arrayOfText[count];
                    displayText(line);

                }else if(input.equals("Accuse:"+currentQ)){
                    displayText(line);

                }else if(line.equals("(choose evidence)")){
                    chooseEvidence();

                }else if (line.contains(witnessName + ":") || line.contains("DD:")){
                    linesDone.add(input); 
                    displayText(line);

                }else if (line.equals("(AddtoNtbk)") || line.equals("(AddtoNtbkOther)")){
                    //linesDone.add(line);
                    String s = line;
                    //looks at the new question 
                    count++;
                    line =  arrayOfText[count];
                    //adds new question to ntbk
                    if (!s.equals("(AddtoNtbkOther)"))
                        addtoOverallNtbk(witnessName + ":" + line);
                    else
                        addtoOverallNtbk(line);
                    //notebookList.add(line);
                    //notebookList.add(line);

                    count++;
                    line =  arrayOfText[count];
                    String test = arrayOfText[count+1];
                    if(!cluesList.contains(s) && line.equals("(add clue)")){
                        do{
                            count++;
                            line =  arrayOfText[count];
                            //adds new clue to evidence

                            cluesList.add(line);
                            count++;
                            line =  arrayOfText[count];
                            cluesListDesc.add(line);
                            count++;
                            line =  arrayOfText[count];
                        }while(line.equals("(add clue)"));

                        //removeObjects(cluesListObj);
                        //displayCluesObj();
                        displayClues();
                        //displayStuff();
                    }
                    //erase displayed ntbk
                    removeObjects(notebookListObj);
                    //show new updated ntbk
                    displayNotes();
                    //count++;
                    //line =  arrayOfText[count];
                    if(line.equals("cont'd")){
                        count++;
                        line =  arrayOfText[count];
                        //if(!linesDone.contains(line))
                        displayText(line);

                    }
                    
                }else if (line.equals("(add clue)") && !cluesList.contains(arrayOfText[count + 1])){
                    //linesDone.add(line);

                    //looks at the new question 
                    do{
                        count++;
                        line =  arrayOfText[count];
                        //adds new clue to evidence
                        cluesList.add(line);

                        count++;
                        line =  arrayOfText[count];
                        cluesListDesc.add(line);

                        count++;
                        line =  arrayOfText[count];
                    }while(line.equals("(add clue)"));
                    //erase displayed ntbk
                    removeObjects(cluesListObj);
                    //show new updated ntbk
                    
                    displayClues();
                    //displayStuff();
                    if(line.equals("cont'd")){
                        count++;
                        line =  arrayOfText[count];
                        //if(!linesDone.contains(line))
                        displayText(line);

                    }

                }else if (line.equals("(show options)")){
                    lineMemory = input;
                    count++;
                    line =  arrayOfText[count];
                    correctAns = line;

                    displayOptions();

                }else if(input.equals("Q: " + currentQ + ": clue required:") ){
                    if(line.equals(currentEvidence)){
                        count++;
                        line =  arrayOfText[count];
                        displayText(line);
                        correctChoiceSound.play();
                    }else {
                        giveInput("Incorrect: chose lie: "+currentQ);
                        //incorrectChoiceSound.play();
                        //addObject(new TextMsg(currentQ + "Sss"), 50,50);
                    }
                }else if(line.equals("Encounter:" + (currentEncounter +1))){
                    // break;
                }

            }

        }

    }

    public void displayMainUI()
    {
        if(notebook.getWorld()!=null)
            removeObject(notebook);
        addObject((Actor)uiSide, getWidth() - 130, getHeight() -150);
        if(getObjects(ImageHolder.class).size()!=0)
        removeObject(getObjects(ImageHolder.class).get(0));

        notebookListObj.clear();
        removeObjects(getObjects(NotebookMsg.class));
        addObject(new PeopleButton(), mainUI.getX() - mainUI.getImage().getWidth()/2+ 50, 
        mainUI.getY()- mainUI.getImage().getHeight()/2+30);
        //notebook.setImage("UIsideElement.png");
    }
    
    public void refreshPeople()
    {
        peopleListObj.clear();
        for(int i = 0; i <peopleList.size(); i++){
            peopleListObj.add(new TextMsg((String)peopleList.get(i)));
            
        
        }
    }
    public void displayPeople()
    {
        addObject (exitButton, 47,37);
        peopleListObj.clear();
        for(int i = 0; i <peopleList.size(); i++){
            peopleListObj.add(new TextMsg((String)peopleList.get(i)));
            addObject((Actor)peopleListObj.get(i), 0, 0);
            if(i==0)
            ((Actor)peopleListObj.get(peopleListObj.size()-1)).setLocation(mainUI.getX()-
            mainUI.getImage().getWidth()/2  +20,
            mainUI.getY());
            if(i>0)
            ((Actor)peopleListObj.get(i)).setLocation(((Actor)peopleListObj.get(i-1)).getX() +
            ((Actor)peopleListObj.get(i-1)).getImage().getWidth()/2 +
            ((Actor)peopleListObj.get(i)).getImage().getWidth()/2 +10, mainUI.getY());
        }

    }

    public void switchToPerson(String pers)
    {
        if(uiSide.getWorld()!=null)
            removeObject(uiSide);
        //addObject((Actor)uiSide, getWidth() - 130, getHeight() -150);
        addObject((Actor)notebook, getWidth()-130, getHeight()-150);
        //notebook.setImage("notebook.png");
        addObject(imgHolder, 10 +530/2, 180);
        witnessName = pers;
        fileName = pers;
        arrayOfText = getFileText(pers +".txt");
        // if(overallNotebookList.contains("Speech:"+speechCount))
        // readTxtFile("Speech:"+ speechCount);

        displayNotes();
        //displayCluesObj();
       // displayStuff();
        displayClues();

        addObject (exitButton, 47,37);
        removeObjects(getObjects(TextMsg.class));
        //speechCount = peopleListSpeech.indexOf(peopleList.indexOf(witnessName));
        if(!overallNotebookList.contains("Speech" + Integer.toString(0) + witnessName)){
            readTxtFile("Speech"+ Integer.toString(0));
            //addObject(new Lie(), 10 +530/2, 15 +200);
        } else if(unlockedSpeech.size()>0 && ((String)(unlockedSpeech.get(0))).contains(witnessName)){
            readTxtFile("unlockedSpeech: " +(String)unlockedSpeech.get(0));
            //addObject(new Lie(), 10 +530/2, 15 +200);
        }

    }

    public String[] getFileText(String filename)
    {
        java.util.List<String> lines = new java.util.ArrayList<String>();
        BufferedReader br = null; // sets up a local field for the BufferedReader object
        // attempt to open an input stream to the file given
        try
        {
            InputStream input = getClass().getClassLoader().getResourceAsStream(filename); // open stream
            br = new BufferedReader(new InputStreamReader(input)); // wrap it within a BufferedReader object
        }
        catch (Exception e) { System.out.println("'"+filename+"' file not found"); return (String[]) null; } // for failure to open file
        // attempt to read in the lines of text
        try
        {
            String line = null; // sets up a local field for each line of text
            while ((line = br.readLine()) != null)  lines.add(line); // read each line and add them to the 'lines' list
            br.close(); // close the BufferedReader object
        }
        catch (Exception e) { try { br.close(); } catch (Exception f) {} } // close file if read error occurred
        return lines.toArray(new String[0]);
    }

    public void chooseResponse(String a)
    {
        //correctAns = "";
        currentEvidence = "";
        chosenOption = a;
        removeOptions();

        if(!a.equals("lie"))
            giveInput(lineMemory);
        else
            giveInput("Accuse:" +currentQ);
        continueSound.play();

    }

    public void chooseEvidence(){
        // contButton.setImage("selectImg.png");

        addInteractionButton(selectButton);
        //ntbkInteraction = false;
    }

    public void commitEvidence(){
        //addObject(new TextMsg(currentEvidence + currentQ),50,50);
        addInteractionButton(contButton);
        //lineMemory = null;
        if(chosenOption.equals( correctAns)){
        giveInput("Q: " + currentQ +": clue required:");
        
       }
        else{
        giveInput("Incorrect: chose lie: "+currentQ);
        //addObject(new TextMsg(currentEvidence + currentQ + correctAns),50,50);
    }
        //currentEvidence = "";
        //currentQ = "";
        correctAns = "";
    }

    public void addtoOverallNtbk(String s){
        //notebookList.add(s);
        overallNotebookList.add(s);

    }

    public void displayNotes(){
        int xNotebk = getObjects(Notebook.class).get(0).getX();
        int yNotebk = getObjects(Notebook.class).get(0).getY();
        notebookListObj.clear();
        //addObject( new NotebookMsg("sss:real stuff".replace("sss:", "")), 40, 40);
        for(int i =0; i < overallNotebookList.size(); i++){

            if(overallNotebookList.get(i).contains(witnessName + ":")){
                notebookListObj.add(new NotebookMsg((String)overallNotebookList.get(i).replace( witnessName+":", "")));

            }
        }

        for(int i =0; i < notebookListObj.size(); i++){
            addObject((Actor)notebookListObj.get(i), xNotebk - notebook.getImage().getWidth()/2 + 
                ((Actor)notebookListObj.get(i)).getImage().getWidth()/2 ,
                yNotebk - notebook.getImage().getHeight()/2 + 50 + i*30);
        }

    }

    public void showClueInfo(String s){
        if(getObjects(TextMsg.class).size()>0){
            if(!((String)(getObjects(TextMsg.class).get(0).text)).contains("DD:")&&
            !((String)(getObjects(TextMsg.class).get(0).text)).contains(witnessName + ":")){
                getObjects(TextMsg.class).get(0).destroy();
                displayText(s);
            }
        }else{
            displayText(s);
        }

    }

    public void addCluesObj(){
        //cluesListObj.add(new CluesMsg((String)cluesList.get(y2)));
        //((CluesMsg)cluesListObj.get(y2)).description= (String)cluesListDesc.get(y2);

    
    }

    public void displayClues(){
        evidenceHolder.setImage("evidence.png");
        evidenceHolder.setLocation(uiSide2.getX(), uiSide2.getY());
        int xClues = getObjects(EvidenceHolder.class).get(0).getX();
        int yClues = getObjects(EvidenceHolder.class).get(0).getY();
        int yPlace = 0;
        cluesListObj.clear();
        removeObjects(getObjects(CluesMsg.class));
        int y2 = -1;
        int y = 0;
        
        for(int i = 0; i < cluesList.size(); i++){
            //world = (MyWorld) getWorld();
            // Message m = new NotebookMsg((String)notebookList.get(i));

            cluesListObj.add(new CluesMsg((String)cluesList.get(i)));
            ((CluesMsg)cluesListObj.get(i)).description= (String)cluesListDesc.get(i);
        }
        
        if(cluesList.size()!=0){
            do{
                y2++;

                addObject((Actor)cluesListObj.get(y2), xClues - evidenceHolder.getImage().getWidth()/2 
                    + ((Actor)cluesListObj.get(y2)).getImage().getWidth()/2, y);
                if(y2 ==0){
                    y = ((Actor)cluesListObj.get(y2)).getImage().getHeight()/2 +
                    yClues - evidenceHolder.getImage().getHeight()/2 + 50;
                    //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(count)).getX(),y);

                }else if(y2 >= 1) {

                    y = ((Actor)cluesListObj.get(y2)).getImage().getHeight()/2 +
                    ((Actor)cluesListObj.get(y2-1)).getY() + 
                    ((Actor)cluesListObj.get(y2-1)).getImage().getHeight()/2 +3;

                    //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(i+count)).getX(),y);

                }
                ((Actor)cluesListObj.get(y2)).setLocation(((Actor)cluesListObj.get(y2)).getX(),y);
                y=0;
            }while( y2< (cluesListObj.size()-1) && ((Actor)cluesListObj.get(y2)).getY() < 
            (evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 ) - 70);
            if(((Actor)cluesListObj.get(y2)).getY() >= 
            (evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 ) -70){
               if(extensionSymb.getWorld()== null)
                addObject(extensionSymb, evidenceHolder.getX(), 
                evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 - 15);
                else
                extensionSymb.setLocation( evidenceHolder.getX(), 
                evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 - 15);
            
            }
        }

    }

    //update the clues Obj list
    public void displayCluesObj(){
        cluesListObj.clear();
        removeObjects(getObjects(CluesMsg.class));

        for(int i = 0; i < cluesList.size(); i++){
            //world = (MyWorld) getWorld();
            // Message m = new NotebookMsg((String)notebookList.get(i));

            cluesListObj.add(new CluesMsg((String)cluesList.get(i)));
            ((CluesMsg)cluesListObj.get(i)).description= (String)cluesListDesc.get(i);
        }

    }
    
    public void extendEvidence(){
        cluesListObj.clear();
        removeObjects(getObjects(CluesMsg.class));
        
       evidenceHolder.setImage("evidenceExtended.png");
       evidenceHolder.setLocation(evidenceHolder.getX(), getHeight()/2 );
       
       int xClues = getObjects(EvidenceHolder.class).get(0).getX();
        int yClues = getObjects(EvidenceHolder.class).get(0).getY();
        int yPlace = 0;
        int y = 0;
        
        extensionSymb.setLocation(extensionSymb.getX(), 
        evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 - 15);
        
        for(int i = 0; i < cluesList.size(); i++){
            //world = (MyWorld) getWorld();
            // Message m = new NotebookMsg((String)notebookList.get(i));

            cluesListObj.add(new CluesMsg((String)cluesList.get(i)));
            ((CluesMsg)cluesListObj.get(i)).description= (String)cluesListDesc.get(i);
        }
        int y2 = -1;
        do{
            //globalCount++;
            y2++;
            addObject((Actor)cluesListObj.get(y2), xClues - evidenceHolder.getImage().getWidth()/2 
            + ((Actor)cluesListObj.get(y2)).getImage().getWidth()/2, y);
            
            if(y2 ==0){
                y = ((Actor)cluesListObj.get(y2)).getImage().getHeight()/2 +
                yClues - evidenceHolder.getImage().getHeight()/2 + 50;
                //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(count)).getX(),y);
    
            }else if(y2 >= 1) {
    
                y = ((Actor)cluesListObj.get(y2)).getImage().getHeight()/2 +
                ((Actor)cluesListObj.get(y2-1)).getY() + 
                ((Actor)cluesListObj.get(y2-1)).getImage().getHeight()/2 +3;
    
                //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(i+count)).getX(),y);
    
            }
            
            ((Actor)cluesListObj.get(y2)).setLocation(((Actor)cluesListObj.get(y2)).getX(),y);
        
        }while( y2< (cluesList.size())-1);
 
    }
    
    public void displayStuff(){
        int xClues = getObjects(EvidenceHolder.class).get(0).getX();
        int yClues = getObjects(EvidenceHolder.class).get(0).getY();
        int yPlace = 0;
        int y2 = -1;
        int y = 0;
        y2++;
        globalCount = -1;
        
        cluesListObj.clear();
        removeObjects(getObjects(CluesMsg.class));

        for(int i = 0; i < cluesList.size(); i++){
            //world = (MyWorld) getWorld();
            // Message m = new NotebookMsg((String)notebookList.get(i));

            cluesListObj.add(new CluesMsg((String)cluesList.get(i)));
            ((CluesMsg)cluesListObj.get(i)).description= (String)cluesListDesc.get(i);
        }
        
        do{
            globalCount++;
            y2++;
            addObject((Actor)cluesListObj.get(globalCount), xClues - evidenceHolder.getImage().getWidth()/2 
            + ((Actor)cluesListObj.get(globalCount)).getImage().getWidth()/2, y);
            
            if(globalCount ==0){
                y = ((Actor)cluesListObj.get(globalCount)).getImage().getHeight()/2 +
                yClues - evidenceHolder.getImage().getHeight()/2 + 50;
                //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(count)).getX(),y);
    
            }else if(globalCount >= 1) {
    
                y = ((Actor)cluesListObj.get(globalCount)).getImage().getHeight()/2 +
                ((Actor)cluesListObj.get(globalCount-1)).getY() + 
                ((Actor)cluesListObj.get(globalCount-1)).getImage().getHeight()/2 +3;
    
                //((Actor)cluesListObj.get(i+count)).setLocation(((Actor)cluesListObj.get(i+count)).getX(),y);
    
            }
            
            ((Actor)cluesListObj.get(globalCount)).setLocation(((Actor)cluesListObj.get(globalCount)).getX(),y);
        
        }while( y2< (cluesList.size()) && ((Actor)cluesListObj.get(globalCount)).getY() > 
            (evidenceHolder.getY() + evidenceHolder.getImage().getHeight()/2 -25) );

    }
    
    public void displayNextPg(){
    
    
    
    }
    
    
    public String doEnter(String s){
        //int count = arrayOfText.indexOf((String)s);
        int count = Arrays.asList(arrayOfText).indexOf(s);
        if(s.contains(witnessName)){
            count++;
            line = arrayOfText[count];
            return ( s.substring(0, s.length()-1)+ "\n" + doEnter(line) );
        }else{
            return "";
        }

    }

    public void displayText(String s){

        // if(s.charAt(s.length()-1) =='\\'){
        //do{
        //doEnter(s);
        /* count++;
        line = arrayOfText[count];
        s= s.substring(0, s.length()-1)+ "\n" + line;
        count++;
        line = arrayOfText[count];*/
        //}while(line.charAt(s.length()-1) =='\\' && line.contains(wintnessName);

        //}

        displayedText = new TextMsg(s);
        addObject(displayedText, txtArea.getX() - txtArea.getImage().getWidth()/2 + displayedText.getImage().getWidth()/2 +10,
            txtArea.getY() - txtArea.getImage().getHeight()/2 + displayedText.getImage().getHeight()/2+70 );

        // addObject(

    }

    public void interrogationMode(){
        fileName = "initialSetup";
        arrayOfText = getFileText("initialSetup.txt");
        readTxtFile("GetWitnessList");

        for(int i =0; i< peopleList.size(); i++){
            witnessName = (String) peopleList.get(i);
            arrayOfText = getFileText(witnessName + ".txt");
            readTxtFile("Question:" + interStage);
            readTxtFile("Clues:");
            peopleListSpeech.add(0);

        }
        //displayMainUI();
        //arrayOfText = getFileText("external.txt");
        //readTxtFile("Speech");
        switchToPerson("Chief");
    }

    public void addPerson(String pers){
        peopleListSpeech.add(0);
        peopleList.add(pers);

    }

    public void giveInput(String s){
        prevLine = s;

        readTxtFile(s);

    }

    public void displayOptions(){
        //addObject(button, txtArea.getX(),txtArea.getY());
        lieButton = new Lie();
        truthButton = new Truth();
        doubtButton = new Doubt();
        addOptionsToWorld();

        //ntbkInteraction = false;
    }

    public void removeOptions(){
        removeObject(truthButton);
        removeObject(lieButton);
        removeObject(doubtButton);

        //removeObject(button);

    }

    public void addOptionsToWorld(){

        addObject(truthButton, txtArea.getX() - txtArea.getImage().getWidth()/2 + truthButton.getImage().getWidth()/2
            +10, txtArea.getY() - txtArea.getImage().getHeight()/2 -140);

        addObject(doubtButton,txtArea.getX() - txtArea.getImage().getWidth()/2 + truthButton.getImage().getWidth()/2
            +10, txtArea.getY() - txtArea.getImage().getHeight()/2 -90);

        addObject(lieButton, txtArea.getX() - txtArea.getImage().getWidth()/2 +  truthButton.getImage().getWidth()/2
            +10, txtArea.getY() - txtArea.getImage().getHeight()/2 -40);

    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        continueSound.setVolume(80);

        incorrectChoiceSound.setVolume(30);
        buttonSound.setVolume(80);
        correctChoiceSound.setVolume(36);
        evidenceHolder = new EvidenceHolder();
        uiSide2 = new UiSide2();
        uiSide = new UiSide();
        txtArea = new TextArea();
        mainUI = new MainUI();
        //addObject((Actor)uiSide, getWidth() - 130, getHeight() -150);
        //addObject (exitButton, 10,10);
        addObject((Actor)uiSide2, getWidth() - 130, 155);
        addObject(mainUI, 10 +530/2, 10 +580/2);
        addObject(imgHolder, 10 +530/2, 180);

        addObject((Actor)evidenceHolder, uiSide2.getX(), uiSide2.getY());

        notebook = new Notebook();
        // addObject((Actor)notebook, getWidth()-130, getHeight()-150); //gw-130, gh-150
        addObject((Actor)txtArea, txtArea.getImage().getWidth()/2+10, getHeight() -  txtArea.getImage().getHeight()/2-13);
        contButton = new ContinueButton();
        selectButton = new SelectButton();
        // contButtonString = contButton.getImage().toString();
        // addObject(new TextMsg(contButtonString),60,60);
        addInteractionButton(contButton);
        exitButton = new ExitButton();
        // addObject (exitButton, 50,50);

        //addObject(new IntroPage(), getWidth()/2, getHeight()/2);
        interrogationMode();
        setPaintOrder(RightArrow.class, IntroPage.class, TextMsg.class, CluesMsg.class,NotebookMsg.class, 
        Button.class,  EvidenceHolder.class, Notebook.class, SelectButton.class,
            ContinueButton.class, ExitButton.class, TextArea.class, ImageHolder.class);
    }
}

