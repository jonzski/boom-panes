package classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Scanner;

public class Bomb {
    private boolean isExploded;
    private int explosionCooldown;
    private Image bombImage = new Image(new Texture("bomb.png"));
    private ArrayList<String> dictionary = new ArrayList<>();
    private String hint;
    private Input.TextInputListener textInput;

    public Bomb(boolean isExploded, int explosionCooldown){
        this.isExploded = false;
        this.explosionCooldown = explosionCooldown;
        initializeDictionary();
        this.hint = randomizeHint();
    }

    public void initializeDictionary() {
        FileHandle file = Gdx.files.internal("Dictionary.txt");
        Scanner scanner = new Scanner(file.readString());

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (word.length() > 3) {
                dictionary.add(word);
            }
        }
        System.out.println("Done initializing dictionary");
    }

    private String randomizeHint() {
        int randomIndex = (int) (Math.random() * dictionary.size());
        String word = dictionary.get(randomIndex);

        int randomChoice = (int) (Math.random() * (word.length() - 2)); // Random position within the word
        int randomLength = (int) (Math.random() * 2) + 2; // Random length between 2 and 3

        System.out.println("Hint: " + word.substring(randomChoice, randomChoice + randomLength));
        return word.substring(randomChoice, randomChoice + randomLength);
    }

    public boolean checkAnswer(String answer){
        if (dictionary.contains(answer)){
            if(answer.contains(hint)){
                this.hint = randomizeHint();
                return true;
            }
        }
        return false;
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

    public boolean isExploded() {
        return isExploded;
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public String getHint() {
        return hint;
    }
}
