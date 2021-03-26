package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
    private Texture badlogic;
    private Texture bille;
    private Texture bravo;
    private Texture intro;
    private Texture laby0;
    private Texture laby1;
    private Texture laby2;
    private Texture laby3;
    private Texture laby4;
    private Texture laby5;
    private Texture mur;
    private Texture pastille_normale;
    private Texture pastillenormale;
    private Texture pastille_taille;
    private Texture pastilletaille;
    private Texture pastille_temps;
    private Texture pastilletemps;
    private Texture perte;
    private Texture piste;

    public TextureFactory(){
        badlogic = new Texture("Donnees/images/badlogic.jpg");
        bille = new Texture("Donnees/images/Bille2D.png");
        bravo = new Texture("Donnees/images/Bravo.bmp");
        intro = new Texture("Donnees/images/Intro.jpg");
        laby0 = new Texture("Donnees/images/Laby0.png");
        laby1 = new Texture("Donnees/images/Laby1.png");
        laby2 = new Texture("Donnees/images/Laby2.png");
        laby3 = new Texture("Donnees/images/Laby3.png");
        laby4 = new Texture("Donnees/images/Laby4.png");
        laby5 = new Texture("Donnees/images/Laby5.png");
        mur = new Texture("Donnees/images/Murs.jpg");
        pastille_normale = new Texture("Donnees/images/pastilleNormale.bmp");
        pastillenormale = new Texture("Donnees/images/pastilleNormale.png");
        pastille_taille = new Texture("Donnees/images/pastilleTaille.bmp");
        pastilletaille = new Texture("Donnees/images/pastilleTaille.png");
        pastille_temps = new Texture("Donnees/images/pastilleTemps.bmp");
        pastilletemps = new Texture("Donnees/images/pastilleTemps.png");
        perte = new Texture("Donnees/images/Perte.bmp");
        piste = new Texture("Donnees/images/Piste.jpg");
    }

    public static TextureFactory instance = new TextureFactory();


    public static TextureFactory getInstance() {
        return instance;
    }

    public Texture getBadlogic() {
        return badlogic;
    }

    public Texture getBille() {
        return bille;
    }

    public Texture getBravo() {
        return bravo;
    }

    public Texture getIntro() {
        return intro;
    }

    public Texture getLaby0() {
        return laby0;
    }

    public Texture getLaby1() {
        return laby1;
    }

    public Texture getLaby2() {
        return laby2;
    }

    public Texture getLaby3() {
        return laby3;
    }

    public Texture getLaby4() {
        return laby4;
    }

    public Texture getLaby5() {
        return laby5;
    }

    public Texture getMur() {
        return mur;
    }

    public Texture getPastille_normale() {
        return pastille_normale;
    }

    public Texture getPastillenormale() {
        return pastillenormale;
    }

    public Texture getPastille_taille() {
        return pastille_taille;
    }

    public Texture getPastilletaille() {
        return pastilletaille;
    }

    public Texture getPastille_temps() {
        return pastille_temps;
    }

    public Texture getPastilletemps() {
        return pastilletemps;
    }

    public Texture getPerte() {
        return perte;
    }

    public Texture getPiste() {
        return piste;
    }
}