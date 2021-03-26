package fr.ul.rollingball.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.rollingball.dataFactories.SoundFactory;

public class TimePastille extends Pastille {
    private TextureAtlas atlas;
    private Animation anim;
    private float tpsanim;

    public TimePastille(Vector2 position, World monde, float taille) {
        super(position, monde, taille);
        atlas = new TextureAtlas(Gdx.files.internal("Donnees/images/pastilleTemps.pack"));
        anim = new Animation(1f/4f, atlas.getRegions());
        tpsanim = 0;
    }
    @Override
    public void draw(SpriteBatch sb) {
        tpsanim += Gdx.graphics.getDeltaTime();
        sb.draw((TextureRegion)anim.getKeyFrame(tpsanim, true),
                getBody().getPosition().x - getTaille(),
                getBody().getPosition().y - getTaille(),
                getTaille()*2,
                getTaille()*2);
    }

    @Override
    public void effect() {
        SoundFactory.getInstance().playPtemps(0.1f);
    }
}
