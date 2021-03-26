package fr.ul.rollingball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import fr.ul.rollingball.views.GameScreen;
import fr.ul.rollingball.views.SplashScreen;

public class RollingBall extends Game {
	private SplashScreen ss;
	private GameScreen gs;
	private Timer timer;
	private Timer.Task timertask;

	@Override
	public void create() {
		ss = new SplashScreen();
		setScreen(ss);
		timer = new Timer();
		timertask = new Timer.Task() {
			@Override
			public void run() {
				swapScreen();
			}
		};
		timer.scheduleTask(timertask, 3);
	}

	public void swapScreen(){
		ss.dispose();
		gs = new GameScreen();
		setScreen(gs);
	}

	public void dispose(){
		gs.dispose();
	}
}
