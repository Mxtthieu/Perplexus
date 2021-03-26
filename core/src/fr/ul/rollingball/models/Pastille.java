package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Pastille {
    private static float taille;
    private Body body;
    private boolean ramassed;

    public Pastille(Vector2 position, World monde, float taille){
        this.taille = taille;
        ramassed = false;
        BodyDef bd = new BodyDef();
        bd.position.set(position.x,position.y );
        bd.type = BodyDef.BodyType.StaticBody;
        body = monde.createBody(bd);
        FixtureDef fd = new FixtureDef();
        fd.isSensor = true;
        CircleShape cs = new CircleShape();
        cs.setRadius(taille);
        fd.shape = cs;
        body.createFixture(fd);
        body.setUserData(this);
    }

    public abstract void draw(SpriteBatch sb);

    public abstract void effect();

    public static void setTaille(float taille) {
        Pastille.taille = taille;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setRamassed(boolean ramassed) {
        this.ramassed = ramassed;
    }

    public static float getTaille() {
        return taille;
    }

    public Body getBody() {
        return body;
    }

    public boolean isRamassed() {
        return ramassed;
    }
}
