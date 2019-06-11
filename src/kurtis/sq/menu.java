package src.kurtis.sq;


import static src.kurtis.sq.Game.HEIGHT;
import src.kurtis.sq.Game.STATE;

import static src.kurtis.sq.Game.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class menu extends MouseAdapter{
    
    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int mx;
    private int my;
    
    public menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    
    public void mousePressed(MouseEvent e){
        
        mx = e.getX();
        my = e.getY();

       if(game.gameState == STATE.Menu){
           
           //SELECT DIFFICULTY BUTTON
            if(mouseOver(mx, my, 300, 250, 200, 60)){
                
            	game.gameState = STATE.Select;
            	return;
                
            } 

            //help button
            if(mouseOver(mx, my, 300, 350, 200, 60)){
                game.gameState = STATE.Help;
            } 
            
            
            
            //QUIT BUTTON
            if(mouseOver(mx, my, 300, 450, 200, 60)){
                System.exit(1);
            }
   
            
       }
       
       if(game.gameState == STATE.Select) {
    	   
    	   //NORMAL BUTTON
    	   if(mouseOver(mx, my , 150, 150, 200, 60)) {
    		 game.gameState = STATE.Game;
               handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
               handler.clearEnemys();
               handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));    
           	  // AudioPlayer.getMusic("game_music").loop();
    		   
    		   game.diff = 0;

    		   
    	   }
    	   
    	   //level 1 button
    	   if(mouseOver(mx, my, 50, 300, 40, 40)){
    		   game.gameState = STATE.Game;
    		   handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
    		   handler.clearEnemys();
    		   game.diff = 1;
    	   }
    	   
    	   //level 2 button
    	   
    	   
    	   
    	   //HARD BUTTON
    	//   if(mouseOver(mx, my, 450, 150, 200, 60)) {
    	//	   game.gameState = STATE.Game;
        //       handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        //       handler.clearEnemys();
        //       handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));    
           	//AudioPlayer.getMusic("game_music").loop();
    	//	   System.out.println("hard button");
    		   
    	//	   game.diff = 1;
    		   
    	//   }

    	   //BACK BUTTON 
    	   if(mouseOver(mx, my, 300, 450, 200, 60)) {
    		   game.gameState = STATE.Menu;
    		   System.out.println("back button");
    	   }
    	   
    	   
       }
       
       
       //Try again - play = 300, 250, 200, 60
            //menu - help = 300, 350, 200, 60
            //quit - quit = 300, 450, 200, 60
    
        //back button
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 300, 450, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }
        
        //try again button
        if(game.gameState == STATE.End){
        	
        	
            if(mouseOver(mx, my, 300, 250, 200, 60)){
            game.gameState = STATE.Game;
            hud.setLevel(1);
            hud.setScore(0);
            handler.clearEnemys();
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));  
        }
        //back to menu button
        
        if(game.gameState == STATE.End){
        	AudioPlayer.getMusic("game_music").stop();
            if(mouseOver(mx, my, 300, 350, 200, 60)){
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                handler.clearPlayer();
                return;
            }
        }
        
        
        //QUIT BUTTON
            if(mouseOver(mx, my, 300, 450, 200, 60)){
                System.exit(1);
            }

    }
        if (game.gameState == STATE.Win) {
        	//PLAY FIREWORKS LEL
        	if(mouseOver(mx, my, 300, 350, 200, 60)) {
        		//menu
        		game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                handler.clearPlayer();
        	}
        	if(mouseOver(mx, my, 300, 450, 200, 60)) {
        		//quit 
        		System.exit(1);
        	}
        }
}
    
   public void mouseEntered(MouseEvent e){
           
    }
    
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }
    
    public void tick(){
        
    }
    
    
    public void render(Graphics g){
       
        if(game.gameState == STATE.Menu){
        
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.pink);
        g.drawString("Squares 1.0", 275, 100);
  
                
        g.setFont(fnt2);
        g.setColor(Color.pink);
        g.drawRect(300, 250, 200, 60);
        g.fillRect(300, 250, 200, 60);
        g.setColor(Color.black);
        g.drawString("Play", 360, 295);

        g.setColor(Color.pink);
        g.drawRect(300, 350, 200, 60);
        g.fillRect(300, 350, 200, 60);
        g.setColor(Color.black);
        g.drawString("Help", 360, 395);
        
        // if mouse position is within the rectangle coords... 
    
        
        g.setColor(Color.pink);
        g.drawRect(300, 450, 200, 60);
        g.fillRect(300, 450, 200, 60);
        g.setColor(Color.blue);
        g.drawString("Quit", 360, 495);
        
        } else if(game.gameState == STATE.Help){
        
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.pink);
            g.drawString("HELP", 335, 100);
            // help button position = g.drawRect(310, 250, 200, 64);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("1. Use awsd to move.", 125,200);
            g.drawString("2. Survive for as long as possible.", 125, 250);
            g.drawString("3. Like and subscribe for more gr8 content.", 125, 300);
            
            g.setColor(Color.pink);
            g.drawRect(300, 450, 200, 60);
            g.fillRect(300, 450, 200, 60);
            g.setColor(Color.black);
            g.drawString("back", 360, 495);
        }else if(game.gameState == STATE.End){
        
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 250, 100);
            // help button position = g.drawRect(310, 250, 200, 64);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("You lost with a score of: " + hud.getScore(),200,200);

            g.setColor(Color.white);
            g.drawRect(300, 250, 200, 60);
            g.fillRect(300, 250, 200, 60);
            g.setColor(Color.black);                                   
            
            g.drawString("Try again", 330, 295);
                            //horizontal^ ,  ^vertical
                            
            g.setColor(Color.white);
            g.drawRect(300, 350, 200, 60);
            g.fillRect(300, 350, 200, 60);
            g.setColor(Color.black);
            g.drawString("Menu", 360, 395);
            
            
            g.setColor(Color.white);
            g.drawRect(300, 450, 200, 60);
            g.fillRect(300, 450, 200, 60);
            g.setColor(Color.black);
            g.drawString("Quit", 370, 495);
                            
        } else if(game.gameState == STATE.Select){
            
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            
            //draw title
            g.setFont(fnt);
            g.setColor(Color.pink);
            g.drawString("SELECT DIFFICULTY", 150, 100);
      
            // draw normal button and write "normal"    
            g.setFont(fnt2);
            g.setColor(Color.pink);
            g.drawRect(150, 150, 200, 60);
            g.fillRect(150, 150, 200, 60);
            g.setColor(Color.black);
            g.drawString("Normal", 200, 200);
           
            //draw hard button, write hard
            g.setColor(Color.pink);
            g.drawRect(450, 150, 200, 60);
            g.fillRect(450, 150, 200, 60);
            g.setColor(Color.black);
            g.drawString("Hard", 520, 200);
            
            //draw back button, write hard
            g.setColor(Color.pink);
            g.drawRect(300, 450, 200, 60);
            g.fillRect(300, 450, 200, 60);
            g.setColor(Color.blue);
            g.drawString("Back", 360, 495);
            
            
            //draw levels
            g.setColor(Color.white);
            g.drawRect(50, 300, 40, 40);
            g.fillRect(50, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("1", 63, 335);

            g.setColor(Color.white);
            g.drawRect(130, 300, 40, 40);
            g.fillRect(130, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("2", 143, 335);

            g.setColor(Color.white);
            g.drawRect(210, 300, 40, 40);
            g.fillRect(210, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("3", 223, 335);
        
            g.setColor(Color.white);
            g.drawRect(290, 300, 40, 40);
            g.fillRect(290, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("4", 303, 335);
            
            g.setColor(Color.white);
            g.drawRect(370, 300, 40, 40);
            g.fillRect(370, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("5", 383, 335);
            
            g.setColor(Color.white);
            g.drawRect(450, 300, 40, 40);
            g.fillRect(450, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("6", 463, 335);
            
            g.setColor(Color.white);
            g.drawRect(530, 300, 40, 40);
            g.fillRect(530, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("7", 543, 335);
            
            g.setColor(Color.white);
            g.drawRect(610, 300, 40, 40);
            g.fillRect(610, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("8", 623, 335);
            
            g.setColor(Color.white);
            g.drawRect(690, 300, 40, 40);
            g.fillRect(690, 300, 40, 40);
            g.setColor(Color.black);
            g.drawString("9", 703, 335);
            
            g.setColor(Color.white);
            g.drawRect(370, 380, 40, 40);
            g.fillRect(370, 380, 40, 40);
            g.setColor(Color.black);
            g.drawString("10", 372, 415);
            
    }
        else if(game.gameState == STATE.Win){
        	
        	Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("CONGRATULATIONS", 150, 100);
            // help button position = g.drawRect(310, 250, 200, 64);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("You have completed level 1!",200,200);                                 
                            
            g.setColor(Color.white);
            g.drawRect(300, 350, 200, 60);
            g.fillRect(300, 350, 200, 60);
            g.setColor(Color.black);
            g.drawString("Menu", 360, 395);
            
            
            g.setColor(Color.white);
            g.drawRect(300, 450, 200, 60);
            g.fillRect(300, 450, 200, 60);
            g.setColor(Color.black);
            g.drawString("Quit", 370, 495);
        	
        }
    
    }
}
    

