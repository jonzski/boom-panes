package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Bomb {
    private String answeredWord;
    private boolean isExploded;
    private int explosionCooldown;
    private Image bombImage = new Image(new Texture("bomb.png"));
    private ArrayList<String> dictionary = new ArrayList<>();

    public Bomb(boolean isExploded, int explosionCooldown){
        this.isExploded = false;
        this.explosionCooldown = explosionCooldown;
        initializeDictionary();
    }

    public void initializeDictionary() {
        try {
            InputStream dictionary = getClass().getResourceAsStream("Test.txt");
            Scanner scanner = new Scanner(dictionary);
            while (scanner.hasNextLine()) {
                this.dictionary.add(scanner.nextLine());
                System.out.println(this.dictionary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAnswer(String answeredWord){

    }

    public void setAnsweredWord(String answeredWord){
        this.answeredWord = answeredWord;
    }


    public void explode(){
        isExploded = true;
    }

    public Image getBombImage() {
        return bombImage;
    }

    public void setBomb(float x, float y) {
        bombImage.setPosition(x, y);
        bombImage.setSize(150, 150);
    }

    public void updateCooldownAndExplode() {
        if (explosionCooldown > 0) {
            explosionCooldown--; // Decrement the cooldown
            if (explosionCooldown == 0) {
                explode();
            }
        }
    }
}
