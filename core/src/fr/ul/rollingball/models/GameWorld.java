package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.rollingball.views.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;

public class GameWorld {
    private Ball2D ball;
    private static float width = 80;
    private static float height = 60;
    private World monde;
    private ArrayList<Pastille> pastilles;
    private Maze maze;

    public GameWorld(GameScreen gs){
        monde = new World(new Vector2(0,0), true);
        maze = new Maze(this, 0);
        pastilles = new ArrayList<>();
        maze.loadLaby(pastilles);
        ball = new Ball2D(new Vector2(maze.getPos().x, maze.getPos().y), monde);
        createContactListener();
    }

    public void createContactListener(){
        monde.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object o;
                if (contact.getFixtureA().getBody() == ball.getBody()){
                    o = contact.getFixtureB().getBody().getUserData();
                }
                else{
                    o = contact.getFixtureA().getBody().getUserData();
                }
                if (o != null) {
                    if (o.getClass().getSuperclass().getSimpleName().equals("Pastille")) {
                        Pastille p = (Pastille) o;
                        p.setRamassed(true);
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    public Ball2D getBall() {
        return ball;
    }

    public void draw(SpriteBatch sb){
        maze.draw(sb);
        ball.draw(sb);
        sb.begin();
        for(Pastille p : pastilles){
            if (!p.isRamassed()){
                p.draw(sb);
            }
        }
        sb.end();
    }

    public World getMonde() {
        return monde;
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public String updatePastille(){
        ArrayList<Pastille> pastillesToRemove = new ArrayList<>();
        String st = new String();
        for (Pastille p : pastilles){
            if(p.isRamassed()){
                monde.destroyBody(p.getBody());
                p.effect();
                pastillesToRemove.add(p);
                if (p.getClass().getSimpleName().equals("ScorePastille")){
                    st = "ScorePastille";
                } else if (p.getClass().getSimpleName().equals("SizePastille")){
                    st = "SizePastille";
                } else if (p.getClass().getSimpleName().equals("TimePastille")){
                    st = "TimePastille";

                } else {
                    st = ".";

                }
            }
        }

        pastilles.removeAll(pastillesToRemove);
        pastillesToRemove.removeAll(pastillesToRemove);
        return st;
    }

    public void changeLaby(){
        for(Pastille p : pastilles){
            monde.destroyBody(p.getBody());
        }
        pastilles.clear();
        monde.destroyBody(ball.getBody());
        maze.incrNLaby();
        maze.loadLaby(pastilles);
        ball = new Ball2D(maze.getPos(), monde);
    }

    public boolean isVictory(){
        return ball.isOut();
    }

    public Maze getMaze() {
        return maze;
    }

    public void swapSize(){
        ball.swapSize();
    }

}
