package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFactory {
    private Sound alerte;
    private Sound collision;
    private Sound pastille;
    private Sound perte;
    private Sound ptaille;
    private Sound ptemps;
    private Sound victoire;

    public SoundFactory(){
        alerte = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/alerte.mp3"));
        collision = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/collision.wav"));
        pastille = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/pastille.wav"));
        perte = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/perte.mp3"));
        ptaille = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/ptaille.wav"));
        ptemps = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/ptemps.wav"));
        victoire = Gdx.audio.newSound(Gdx.files.internal("Donnees/sounds/victoire.mp3"));
    }

    public static SoundFactory instance = new SoundFactory();


    public static SoundFactory getInstance() {
        return instance;
    }

    public void playAlerte(float volume) {
        alerte.play(volume);
    }

    public void playCollision(float volume) {
        collision.play(volume);
    }

    public void playPastille(float volume) {
        pastille.play(volume);
    }

    public void playPerte(float volume) {
        perte.play(volume);
    }

    public void playPtaille(float volume) {
        ptaille.play(volume);
    }

    public void playPtemps(float volume) {
        ptemps.play(volume);
    }

    public void playVictoire(float volume) {
        victoire.play(volume);
    }
}
