package fr.ul.rollingball.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import fr.ul.rollingball.dataFactories.SoundFactory;
import fr.ul.rollingball.dataFactories.TextureFactory;

public class SplashScreen extends ScreenAdapter {
    private SpriteBatch sb;

    public SplashScreen() {
        sb = new SpriteBatch();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(TextureFactory.getInstance().getIntro(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
        Timer timer = new Timer();
        Timer.Task timertask = new Timer.Task() {
            @Override
            public void run() {
                SoundFactory.getInstance().playVictoire(0.1f);
            }
        };
        if (timer.isEmpty()) {
            timer.scheduleTask(timertask, 0.0f);
        }
    }

    public void dispose () {
        sb.dispose();
    }
}
