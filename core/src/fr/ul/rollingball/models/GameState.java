package fr.ul.rollingball.models;

import com.badlogic.gdx.utils.Timer;
import fr.ul.rollingball.dataFactories.SoundFactory;

public class GameState {
    private static int startingTime = 60;
    private enum State {
        PLAYING,
        WIN,
        LOOSE,
        STOP
    }
    private State state;
    private int score;
    private int timeLeft;
    private int nbPastnormCatched;
    private Timer.Task time;

    public GameState(){
        score = 0;
        timeLeft = 0;
        nbPastnormCatched = 0;
        state = State.PLAYING;
        time = new Timer.Task() {
            @Override
            public void run() {
                countDown();
            }
        };
        Timer timer = new Timer();
        timer.scheduleTask(time, 1, 1);

    }

    public void countDown(){
        if (isPlaying()) {
            if (timeLeft == 0) {
                setState(State.LOOSE);
            } else if (timeLeft <= 10) {
                SoundFactory.getInstance().playAlerte(0.1f);
            }
            timeLeft--;
        }
    }

    public State getCurrentState(){
        return state;
    }

    public boolean isPlaying(){
        return state == State.PLAYING;
    }

    public boolean isWin(){
        return state == State.WIN;
    }

    public boolean isLoose(){
        return state == State.LOOSE;
    }

    public boolean isStop(){
        return state == State.STOP;
    }

    public int getScore() {
        return score;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getNbPastnormCatched() {
        return nbPastnormCatched;
    }

    public static int getStartingTime() {
        return startingTime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setNbPastnormCatched(int nbPastnormCatched) {
        this.nbPastnormCatched = nbPastnormCatched;
    }

    public void setState(State newState){
        state = newState;
    }

    public void setStatePlaying(){
        state = State.PLAYING;
    }

    public void setStateWin(){
        state = State.WIN;
    }

    public void setStateLoose(){
        state = State.LOOSE;
    }

    public void setStateStop(){
        state = State.STOP;
    }
}
