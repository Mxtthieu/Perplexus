package fr.ul.rollingball.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.ArrayList;

public class Maze {
    private GameWorld world;
    private int nlaby;
    private Pixmap pixmap, pixmur, pixlaby;
    private Texture laby;
    private Vector2 pos;
    private ArrayList<Body> mur;
    private float ratiox;
    private float ratioy;

    public Maze(GameWorld monde, int n){
        world = monde;
        nlaby = n;
        mur = new ArrayList<>();
        pos = new Vector2();
        pixmur = new Pixmap(Gdx.files.internal("Donnees/images/Murs.jpg"));
    }

    public Texture getLaby() {
        return laby;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(laby,0,0,(int)GameWorld.getWidth(),(int)GameWorld.getHeight());
        sb.end();
    }

    public void resetNlaby() {
        this.nlaby = -1;
    }

    public void incrNLaby(){ this.nlaby++; }

    public void dispose(){
        laby.dispose();
        pixmap.dispose();
        pixmur.dispose();
        pixlaby.dispose();
    }

    public void loadLaby(ArrayList<Pastille> pastilles){
        for(Body b : mur){
            world.getMonde().destroyBody(b);
        }
        mur.clear();
        pixmap = new Pixmap(Gdx.files.internal("Donnees/images/Laby"+nlaby+".png"));
        ratiox = GameWorld.getWidth()/pixmap.getWidth();
        ratioy = GameWorld.getHeight()/pixmap.getHeight();
        readObjects(pastilles);
    }

    public void readObjects(ArrayList<Pastille> pastilles){
        pixlaby = new Pixmap(Gdx.files.internal("Donnees/images/Piste.jpg"));
        for(int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if ((pixmap.getPixel(i, j) & 255) == 0) { // Mur
                    if (has4Neighbor(i, j)) {
                        BodyDef bd = new BodyDef();
                        bd.position.set(ratiox * i, ratioy * (pixmap.getHeight() - j));
                        bd.type = BodyDef.BodyType.StaticBody;
                        Body body = world.getMonde().createBody(bd);
                        FixtureDef fd = new FixtureDef();
                        CircleShape cs = new CircleShape();
                        cs.setRadius(ratiox);
                        fd.shape = cs;
                        body.createFixture(fd);
                        body.setUserData('M');
                        mur.add(body);
                    }
                    pixlaby.drawPixel(i, j, pixmur.getPixel(i, j));
                }
                if ((pixmap.getPixel(i, j) & 255) == 100) { // Balle
                    pos.x = ratiox * (i + 1f);
                    pos.y = ratioy * (pixmap.getHeight() - j - 5f);
                    pixmap.setColor(255);
                    pixmap.fillCircle(i, j, 10);
                }
                if ((pixmap.getPixel(i, j) & 255) == 128) { // Pastille normale
                    pastilles.add(new ScorePastille(new Vector2(ratiox * (i + 1f),
                            ratioy * (pixmap.getHeight() - j - 5f)), world.getMonde(), ratiox * 5));
                    pixmap.setColor(255);
                    pixmap.fillCircle(i+5, j+1, 5);
                }
                if ((pixmap.getPixel(i, j) & 255) == 200) { // Pastille taille
                    pastilles.add(new SizePastille(new Vector2(ratiox * (i + 1f), ratioy * (pixmap.getHeight() - j - 5f)), world.getMonde(), ratiox * 5));
                    pixmap.setColor(255);
                    pixmap.fillCircle(i+5, j+1, 5);
                }
                if ((pixmap.getPixel(i, j) & 255) == 225) { // Pastille temps
                    pastilles.add(new TimePastille(new Vector2(ratiox * (i + 1f), ratioy * (pixmap.getHeight() - j - 5f)), world.getMonde(), ratiox * 5));
                    pixmap.setColor(255);
                    pixmap.fillCircle(i+5, j+1, 5);
                }
                if ((pixmap.getPixel(i, j) & 255) == 255) {
                    pixlaby.drawPixel(i, j, (int) ((pixlaby.getPixel(i, j) & 255) / 1.25f));
                }
            }
        }
        laby = new Texture(pixlaby);
    }

    public boolean has4Neighbor(int x, int y){
       int left = pixmap.getPixel(x-1, y) & 255;
       int right = pixmap.getPixel(x+1, y) & 255;
       int top = pixmap.getPixel(x, y-1) & 255;
       int bot= pixmap.getPixel(x, y+1) & 255;
       if (left != 0 || right != 0  || top != 0 || bot != 0 ){
           return true;
       } else {
           return false;
       }
    }

    public int getNlaby() {
        return nlaby;
    }
}
