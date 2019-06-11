package src.kurtis.sq;

import java.util.Random;

import javax.swing.Timer;

import src.kurtis.sq.Game.STATE;

public class Spawner {
    
    private Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();
    private HorizontalEnemy hEnemy;
    
    private int min;
    private int max;
    
    private int scoreKeep = 0;
    private int timer;
    
    //ints for right side H spawn ... e.g. rhspawn = 50: (game.WIDTH + rhSpawn)
    // the enemy will spawn 50 pixels past the width
    private int rhSpawn;
    private int rwSpawn;
    //same again for the left side
    private int lhSpawn;
    private int lwSpawn;
    private int topWSpawn = 0;
	private int topHSpawn;

	
	final Timer t = new Timer(500,null);
	
	private int count = 250;

    
    public Spawner(Handler handler, HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    
    public void tick(){
        
        scoreKeep++;
        if(scoreKeep >= 150){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(game.diff == 0) {
            	
            	 if (hud.getLevel() == 2) {
                     handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
            	 }
                 if (hud.getLevel() == 3){
                     handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
                 }
                 if (hud.getLevel() == 4){
                     handler.addObject(new fastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
                 }
                 if (hud.getLevel() == 5){
                     handler.addObject(new fastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
                 }
                 if(hud.getLevel() == 6){
                     handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                 }
                 if(hud.getLevel() == 7){
                     handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                 }
                 if (hud.getLevel() == 10){
                     handler.clearEnemys();
                     handler.addObject(new enemyFirstBoss((Game.WIDTH /2) -48, - 120, ID.enemyFirstBoss, handler));
                 }
                 
                 if(hud.HEALTH == 0){
                     handler.clearEnemys();
                 }
                 
                 if(hud.getLevel() == 15)
                 {
                     
                 }
            }else if(game.diff == 1) {
            	
            	
            	// WAVE 1
           	 if (hud.getLevel() == 2) {
           		 
           		rhSpawn = 50;
           	    rwSpawn = 50;
           		
           	    for(int i = 8; i > 0; i--) { 
           			handler.addObject(new HorizontalEnemy((game.WIDTH + rwSpawn), rhSpawn, ID.HorizontalEnemy, handler));
           			rhSpawn += 75;
           			rwSpawn += 75;
           			
           		 }
                }
           	 
           	 // WAVE 2 
           	 	
	          if (hud.getLevel() == 3) {	
	           	lhSpawn = -50;
	           	lwSpawn = 50;
	           	
	           	for (int i = 8; i > 0; i --) {
	           		handler.addObject(new HorizontalEnemyR(lwSpawn, lhSpawn, ID.HorizontalEnemyR, handler));
	           		lhSpawn += 75;
	           		lwSpawn -= 75;
	           	}
	           		
	           	}
	          
	          // WAVE 3
	          
	          if(hud.getLevel() == 5) {
	        	  rhSpawn = 50;
	        	  rwSpawn = 50;
	        	  lhSpawn = -50;
	        	  lwSpawn = 50;
	        	  
	        	  for(int i = 8; i > 0; i--) { 
	           			handler.addObject(new HorizontalEnemy((game.WIDTH + rwSpawn), rhSpawn, ID.HorizontalEnemy, handler));
	           			rhSpawn += 75;
	           			rwSpawn += 75;
	           			
	           		 }
	        	  for(int i = 8; i > 0; i--) {
	        		  handler.addObject(new HorizontalEnemyR(lwSpawn, lhSpawn, ID.HorizontalEnemyR, handler));
	        		  lhSpawn += 75;
	        		  lwSpawn -= 75;
	        	  }
	          }
	          
	          // WAVE 4
	          
	          if(hud.getLevel() == 6) {
	        	  topWSpawn = 0;
	        	  topHSpawn = 590;
	        	  
	        	  for(int i = 12; i > 0; i--) {
	        		  handler.addObject(new verticalEnemyUp(topWSpawn, topHSpawn, ID.verticalEnemyUp, handler));
	        		  topWSpawn += 75;
	        		  topHSpawn += 75;
	        	  }
	        	  
	          }
	          
	          
	          // WAVE 5
	          if(hud.getLevel() == 6) {
	        	  topWSpawn = 0;
	        	  topHSpawn = -585;
	        	  
	        	  for(int i = 12; i > 0; i--) {
	        		  handler.addObject(new verticalEnemyDown(topWSpawn, topHSpawn, ID.verticalEnemyDown, handler));
	        		  topWSpawn += 75;
	        		  topHSpawn -= 75;
	        	  }
	          }

	        /*  
	          // WAVE 6 ...
	          // drop random squares from the top for 60 seconds with a random speed
            	
            	if (hud.getLevel() == 8) {
            		timer = 650;	
            		for(int i = 25; i > 0; i--) {
            			
            			handler.addObject(new verticalEnemyDown(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) -timer, ID.verticalEnemyDown, handler));
            			timer += 50;
            		}
            		}
            	if(hud.getLevel() == 11) {
            		timer = 650;
            			for(int i = 25; i > 0; i--) {            			
            				handler.addObject(new verticalEnemyDown(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) -timer, ID.verticalEnemyDown, handler));
            				timer += 50;
            		}
            	}
            */	
	          
	          // WAVE 7 BOSS...
	          if(hud.getLevel() == 10) {
	                     handler.clearEnemys();
	                     handler.addObject(new enemyFirstBoss((Game.WIDTH /2) -48, - 120, ID.enemyFirstBoss, handler));
	          }
           	 
                if(HUD.HEALTH == 0){
                    handler.clearEnemys();
                }
                
                if(hud.getLevel() == 15)
                {
                    
                }
           }
            
           
        

        }
        
    }
    
}

