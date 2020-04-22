  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;
import java.awt.FileDialog;
import java.io.*;
  


/**
 * Write a description of class notebook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notebook extends Actor
{
       String fileName="dialogue.txt";
       List notebookList = new ArrayList();
       World MyWorld;
     
    /**
     * Act - do whatever the notebook wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       
     
      //         for(int i =0; i < notebookList.size(); i++){
            
      //  addObject((Actor)notebookList.get(i), notebook.getX()-80, notebook.getY()-30 -30*i);
        
    }
    
    public Notebook(){
        InputStream input = getClass().getClassLoader().getResourceAsStream("dialogue.txt");
       // displayNotes();
       
        
        
    }
    
    
    public void displayNotes(){
        
        
       try{

          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);

          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);

          //Variable to hold the one line data
          String line;

          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null)   {
              if(line.equals("Question:")){
                  line = bufferReader.readLine();
                  notebookList.add(line);
              }
          }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
          //System.out.println("Error while reading file line by line:" + e.getMessage());                      
       }
   
        
       
        
    }
    
    
}