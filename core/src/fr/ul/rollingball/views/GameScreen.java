package fr.ul.rollingball.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import fr.ul.rollingball.controllers.GestureListener;
import fr.ul.rollingball.controllers.KeyboardListener;
import fr.ul.rollingball.dataFactories.SoundFactory;
import fr.ul.rollingball.dataFactories.TextureFactory;
import fr.ul.rollingball.models.GameState;
import fr.ul.rollingball.models.GameWorld;


public class GameScreen extends ScreenAdapter {
    private SpriteBatch sb;
    private SpriteBatch texte;
    private OrthographicCamera camera;
    private OrthographicCamera camTxt;
    private GameWorld monde;
    private GameState currentState;
    private KeyboardListener listen;
    private GestureListener glisten;
    private Box2DDebugRenderer debugRenderer;
    private BitmapFont bmfont;
    private Timer timer;
    private Task labcomplete;
    private static int timeItMs = 1000/60;
    private int sysTime;


    public GameScreen(){
        sb = new SpriteBatch();
        camera = new OrthographicCamera(GameWorld.getWidth(),GameWorld.getHeight());
        camera.position.set(GameWorld.getWidth()/2f,GameWorld.getHeight()/2f,10.0f);
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        texte = new SpriteBatch();
        camTxt = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camTxt.position.set(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f,10.0f);
        camTxt.update();
        texte.setProjectionMatrix(camTxt.combined);

        monde = new GameWorld(this);
        currentState = new GameState();
        listen = new KeyboardListener();
        glisten = new GestureListener();
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(listen);
        im.addProcessor(new GestureDetector(glisten));
        Gdx.input.setInputProcessor(im);
        Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        debugRenderer = new Box2DDebugRenderer();
        timer = new Timer();
        labcomplete = new Task() {
            @Override
            public void run() {
                changeLaby();
            }
        };
        FreeTypeFontGenerator fGen = new FreeTypeFontGenerator(Gdx.files.internal("Donnees/fonts/Comic_Sans_MS_Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fParams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fParams.size = 40;
        fParams.color = new Color(1f, 1f, 0f, 0.75f);
        fParams.borderColor = Color.BLACK;
        fParams.borderWidth = 3 * Gdx.graphics.getWidth()/1024f;
        bmfont = fGen.generateFont(fParams);
        currentState.setStatePlaying();
        currentState.setTimeLeft(5);
        sysTime = (int)(System.currentTimeMillis() % Integer.MAX_VALUE);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();

        if (currentState.isStop()) {
            Gdx.app.exit();
        }

        if (!currentState.isPlaying()){
            if (!listen.isDebugmode()) {
                monde.draw(sb);
            }
            if (timer.isEmpty()){
                timer.scheduleTask(labcomplete, 3.0f);
            }
            if (currentState.isWin()){
                if (!listen.isDebugmode()) {
                    sb.begin();
                    sb.draw(TextureFactory.getInstance().getBravo(),
                            GameWorld.getWidth() / 2f - 2.5f * TextureFactory.getInstance().getBravo().getWidth() / (GameWorld.getWidth()),
                            GameWorld.getHeight() / 2f - 2.5f * TextureFactory.getInstance().getBravo().getHeight() / (GameWorld.getHeight()),
                            GameWorld.getWidth() / 2f,
                            GameWorld.getHeight() / 2f);
                    sb.end();
                    texte.begin();
                    bmfont.draw(texte,
                            "Pastilles normales avalées : " + currentState.getNbPastnormCatched(),
                            1.0f * Gdx.graphics.getWidth() / 4f,
                            Gdx.graphics.getHeight() / 4f);
                            if (monde.getMaze().getNlaby() == 5){
                                bmfont.draw(texte,
                                        "Score final : " + currentState.getScore(),
                                        1.0f * Gdx.graphics.getWidth()/4f,
                                        Gdx.graphics.getHeight()/4f - 10);
                            }
                    texte.end();
                }
            } else if (currentState.isLoose()) {
                if (!listen.isDebugmode()) {
                    sb.begin();
                    sb.draw(TextureFactory.getInstance().getPerte(),
                            GameWorld.getWidth() / 2f - 2.5f * TextureFactory.getInstance().getPerte().getWidth() / (GameWorld.getWidth()),
                            GameWorld.getHeight() / 2f - 2.5f * TextureFactory.getInstance().getPerte().getHeight() / (GameWorld.getHeight()),
                            GameWorld.getWidth() / 2f,
                            GameWorld.getHeight() / 2f);
                    sb.end();
                    texte.begin();
                    bmfont.draw(texte,
                            "Pastilles normales avalées : " + currentState.getNbPastnormCatched(),
                            1.0f * Gdx.graphics.getWidth() / 4f,
                            Gdx.graphics.getHeight() / 4f);
                    texte.end();
                }
            }

        }

        if (currentState.isPlaying()) {
            int deltaT = (int)(System.currentTimeMillis()% Integer.MAX_VALUE) - sysTime;
            sysTime += deltaT;
            if (sysTime > timeItMs) {
                sysTime = (int)(System.currentTimeMillis()% Integer.MAX_VALUE);
                if (!listen.isDebugmode()) {
                    monde.draw(sb);
                    texte.begin();
                    bmfont.draw(texte,
                            "Score : " + currentState.getScore(),
                            3 * Gdx.graphics.getWidth() / 4f,
                            Gdx.graphics.getHeight() - 10);
                    bmfont.draw(texte,
                            "Time : " + currentState.getTimeLeft(),
                            Gdx.graphics.getWidth() / 2f,
                            Gdx.graphics.getHeight() - 10);
                    texte.end();
                }
            }
        }
        if (listen.isDebugmode()) {
            debugRenderer.render(monde.getMonde(), camera.combined);
        }
    }

    public void update(){
        Vector2 force = new Vector2(10*Gdx.input.getAccelerometerY(), -10*Gdx.input.getAccelerometerX());
        if (currentState.getTimeLeft() == 0 && !currentState.isLoose()){
            currentState.setStateLoose();
            SoundFactory.getInstance().playPerte(0.1f);
        }
        if (monde.isVictory() && !currentState.isWin()) {
            currentState.setStateWin();
            SoundFactory.getInstance().playVictoire(0.1f);
        }
        if (currentState.getTimeLeft() > 0 && !currentState.isLoose() && !currentState.isWin()) {
            currentState.setStatePlaying();
            String pastille = monde.updatePastille();
            System.out.println(pastille);
            if (pastille.equals("ScorePastille")) {
                upScore();
                upPastNormCatched();
            } else if (pastille.equals("TimePastille")) {
                addTime(10);
            } else if (pastille.equals("SizePastille")) {
                monde.swapSize();
            }
        }
        if (currentState.isPlaying()){
            if (glisten.isGeste()){
                Vector2 buff = new Vector2(glisten.getAccel());
                monde.getBall().applyForce(new Vector2(
                        force.x + buff.x + listen.getAccel().x,
                        force.y + buff.y + listen.getAccel().y ));
            } else {
                monde.getBall().applyForce(new Vector2(force.x + listen.getAccel().x, force.y + listen.getAccel().y));
            }
            monde.getMonde().step(Gdx.graphics.getDeltaTime(), 6, 2);
        }
        if (listen.isExit()){
            currentState.setStateStop();
        }
    }

    @Override
    public void dispose(){
        sb.dispose();
        texte.dispose();
        bmfont.dispose();
        monde.getMonde().dispose();
    }

    public void changeLaby(){
        if (currentState.isWin()) {
            if (monde.getMaze().getNlaby() <= 5) {
                monde.changeLaby();
                currentState.setTimeLeft(currentState.getTimeLeft() + currentState.getNbPastnormCatched());
                currentState.setNbPastnormCatched(0);
            } else {
                currentState.setStateStop();
            }
        }

        if (currentState.isLoose()) {
            currentState.setScore(0);
            currentState.setNbPastnormCatched(0);
            monde.getMaze().resetNlaby();
            monde.changeLaby();
            currentState.setTimeLeft(GameState.getStartingTime());
        }
        currentState.setStatePlaying();
        listen.resetAccel();
    }



    public GameState getCurrentState(){
        return currentState;
    }

    public void addTime(int t){
        currentState.setTimeLeft(currentState.getTimeLeft() + t);
    }

    public void upScore(){
        currentState.setScore(currentState.getScore() + 1);
    }

    public void upPastNormCatched(){
        currentState.setNbPastnormCatched(currentState.getNbPastnormCatched() + 1);
    }

    @Override
    public void show(){
        currentState.setStatePlaying();
    }
}
