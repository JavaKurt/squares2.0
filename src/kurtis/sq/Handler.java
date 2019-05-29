package kurtis.sq;

//The handler class will maintain, update and render all objects to the window

import java.awt.Graphics;
import java.util.LinkedList;

//loops through all of the objects and individually updates and renders them to the screen
//----CREATE A LIST OF ALL OF THE GAME OBJECS-----
public class Handler {
 
 LinkedList<GameObject> object = new LinkedList<GameObject>();
 
 public int spd = 5;
 
 public void tick(){
     for(int i = 0; i < object.size(); i++){
         GameObject tempObject = object.get(i);
         
         tempObject.tick();
     }
     
 }
 
 public void render(Graphics g){
     for(int i = 0; i < object.size(); i++){
     GameObject tempObject = object.get(i);
     
     tempObject.render(g);
 }
 }
 
 public void clearEnemys(){
     for(int i = 0; i < object.size(); i++){
     GameObject tempObject = object.get(i);
     
     if(tempObject.getId() != ID.Player) {
         removeObject(tempObject);
         i--;
     }
 }
 }
  
 public void clearPlayer(){
     for(int i = 0; i < object.size(); i++){
         GameObject tempObject = object.get(i);
         
         if(tempObject.getId() != ID.Enemy){
             removeObject(tempObject);
             i--;
         }
     }
 }
 
 //add a game object to the list above
 public void addObject(GameObject object){
     this.object.add(object);
 }
 //remove a game object from the list above
 public void removeObject(GameObject object){
     this.object.remove(object);
 }
 
}

