package fr.ul.rollingball.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Ball {
    private static float grand = GameWorld.getWidth() / 50f;
    private static float petit = GameWorld.getWidth() / 100f;
    private String taille;
    public Body body;

    public Ball(Vector2 position, World monde){
        taille = "grande";
        BodyDef bd = new BodyDef();
        bd.position.set(position.x, position.y);
        bd.type = BodyDef.BodyType.DynamicBody;
        body = monde.createBody(bd);
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.restitution = 0.25f;
        fd.friction = 0;
        CircleShape cs = new CircleShape();
        cs.setRadius(grand);
        fd.shape = cs;
        body.createFixture(fd);

    }

    public void applyForce(Vector2 v2){
        body.applyForceToCenter(v2, true);
    }

    public float getPosX(){
        return body.getPosition().x;
    }

    public float getPosY(){
        return body.getPosition().y;
    }

    public static float getGrand() {
        return grand;
    }

    public static float getPetit() {
        return petit;
    }

    public Body getBody() {
        return body;
    }

    public boolean isOut() {
        if (getPosX() > GameWorld.getWidth() || getPosX() < 0 || getPosY() > GameWorld.getHeight() || getPosY() < 0)
            return true;
        return false;
    }

    public void swapSize(){
        if (taille == "grande"){
            taille = "petite";
        } else {
            taille = "grande";
        }

        if (taille == "grande"){
            Shape shape = body.getFixtureList().first().getShape();
            shape.setRadius(grand);
        } else if (taille == "petite"){
            Shape shape = body.getFixtureList().first().getShape();
            shape.setRadius(petit);
        }
    }

    public float getRayon() {
        if (taille == "grande") {
            return grand;
        }
        return petit;
    }
}
