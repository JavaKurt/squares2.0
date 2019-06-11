package src.kurtis.sq;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.kurtis.sq.Game.STATE;


public class KeyInput extends KeyAdapter{
    
    public Handler handler;
    private boolean[] keyDown = new boolean[4];
    
    Game game;
    HUD hud;
    
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        
        this.game = game;
        
        keyDown[0] = false; //key up
        keyDown[1] = false; // key down
        keyDown[2] = false; // key right
        keyDown[3] = false; //key left
    }
    
    public void keyPressed(KeyEvent e){
        
        //sets the variable key to a letter binding, when the key is pressed, the number value which corresponds to the letter will be printed
        int key = e.getKeyCode();
        //System.out.println("ASCII key: " + key);
        
        //loop through all of the objects in a game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
                
                //key events for player 1          
                //pretend tempObject is ID.Player
                if(key == KeyEvent.VK_W){ tempObject.setVelY(-handler.spd); keyDown[0] = true; }
                if(key == KeyEvent.VK_S){ tempObject.setVelY(handler.spd); keyDown[1] = true; }
                
                if(key == KeyEvent.VK_D){ tempObject.setVelX(handler.spd); keyDown[2] = true; }
                if(key == KeyEvent.VK_A){ tempObject.setVelX(-handler.spd); keyDown[3] = true; }
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE) 
        {  	
        	if(game.gameState == STATE.Game) 
        	{	
        		if(Game.paused) {
        			Game.paused = false;
        		} else 
            		Game.paused = true;	
        	}
        }
        
        if(key == KeyEvent.VK_SPACE) {
        	if(Game.gameState == STATE.Game) {
        		Game.gameState = STATE.Shop;
        	} else if(Game.gameState == STATE.Shop){
        		Game.gameState = STATE.Game;
        	}
        }
        if(key == KeyEvent.VK_M) {
        	if(Game.gameState == STATE.Game) {
        		handler.clearPlayer();
        		hud.score = 0;
        		hud.level = 1;
        		handler.clearEnemys();
        		Game.gameState = STATE.Menu;
        	}
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        //loop through all of the objects in a game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
                
                //key events for player 1          
                //pretend tempObject is ID.Player
                if(key == KeyEvent.VK_W) keyDown[0] = false;    //tempObject.setVelY(0);
                if(key == KeyEvent.VK_S)keyDown[1] = false;   //tempObject.setVelY(0);  
                if(key == KeyEvent.VK_D) keyDown[2] = false; //tempObject.setVelX(0);
                if(key == KeyEvent.VK_A) keyDown[3] = false;  //tempObject.setVelX(0);
               
                
                // vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                
                // horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
                else if(keyDown[2] && !keyDown[3]) tempObject.setVelX(5);
                else if(!keyDown[2] && keyDown[3]) tempObject.setVelX(-5);
            }
        }
    }
    
}
