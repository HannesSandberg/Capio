package state;
import gamecontroller.MouseAndKeyBoardPlayerController;
import gamecontroller.PlayerController;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import physics.Physics;
import character.Player;
import Game.Game;
import Level.Level;
 

    public class LevelState extends BasicGameState {
    	 
        private Level  level;
        private String startinglevel;
        private Player player;
        private PlayerController playerController;
        private Physics physics;
     
        public LevelState(String startingLevel){
            this.startinglevel = startingLevel;
        }
     
        public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
			//at the start of the game we don't have a player yet
			player = new Player(220,220);
            //once we initialize our level, we want to load the right level
            level = new Level(startinglevel,player);
     
            
           
           
            level.addCharacter(new Player(450,450));
            level.addCharacter(new Player(700,700));
            level.addCharacter(new Player(1500,1000));
            level.addCharacter(new Player(1000,1000));
            level.addCharacter(new Player(1000,450));
            level.addCharacter(player);
            physics = new Physics();     
            //and we create a controller, for now we use the MouseAndKeyBoardPlayerController
            playerController = new MouseAndKeyBoardPlayerController(player);
        }
     
        public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
            //every update we have to handle the input from the player
        	playerController.handleInput(container.getInput(), delta);
            physics.handlePhysics(level, delta);
          
        }
     
        public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
            g.scale(Game.SCALE, Game.SCALE);
            //render the level
            level.render(g);
            
            
        }
     
        //this method is overriden from basicgamestate and will trigger once you press any key on your keyboard
        public void keyPressed(int key, char code){
            //if the key is escape, close our application
            if(key == Input.KEY_ESCAPE){
                System.exit(0);
            }
        }
     
        public int getID() {
            //this is the id for changing states
            return 0;
        }
     
    }
