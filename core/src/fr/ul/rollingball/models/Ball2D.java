package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.rollingball.dataFactories.TextureFactory;

public class Ball2D extends Ball {
    public Ball2D(Vector2 position, World monde) {
        super(position, monde);
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(TextureFactory.getInstance().getBille(),
                getPosX() - getRayon() ,
                getPosY() - getRayon(), getRayon()*2, getRayon()*2);
        sb.end();
    }

}
