package kurtis.sq;

import java.util.Random;

public class Spawner {
    
    private Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();
    
    private int scoreKeep = 0;
    
    public Spawner(Handler handler, HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    
    public void tick(){
        
        scoreKeep++;
        if(scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(game.diff == 0) {
            	
            	 if (hud.getLevel() == 2){
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
            	
           	 if (hud.getLevel() == 2){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
                }
                if (hud.getLevel() == 3){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
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
           }
            
           
        

        }
        
    }
}

