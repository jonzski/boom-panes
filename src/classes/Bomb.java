package classes;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bomb extends Sprite{

    private boolean isExploded;
    private int explosionCooldown;
    private ArrayList<String> dictionary = new ArrayList<>();
    private String hint;

    private final static Image BOMB = new Image("assets/bomb.png");


    public Bomb(boolean isExploded, int explosionCooldown, double xPos, double yPos, double width, double height) {
        super(xPos, yPos, width, height, BOMB);
        this.isExploded = isExploded;
        this.explosionCooldown = explosionCooldown;
        this.hint = randomizeHint();
        initializeDictionary();
    }

    public void initializeDictionary() {
        File file = new File("src/assets/Dictionary.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (word.length() > 3) {
                    dictionary.add(word);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
