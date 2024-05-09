package classes;


import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bot extends Player{

    private int difficulty;
    private int health;
    private String name;
    private Boolean isDead;

    private final static Image HEAD = new Image("assets/player-head.png");

    private final static int WIDTH = 200;
    private final static int HEIGHT = 200;
    private final static int INITIAL_X = 0;
    private final static int INITIAL_Y = 0;


    public Bot(int health, String name, boolean isDead, int difficulty) {
        super(health, name, isDead);
        this.difficulty = difficulty;
        this.health = health;
        this.name = name;
        this.isDead = isDead;
    }

    public int answer(Bomb bomb, String answer) {
        System.out.println("Bot " + name + " answered: " + answer);
        if (bomb.checkAnswer(answer)) {
            System.out.println("Correct answer");
            return 1;
        } else {
            System.out.println("Incorrect answer, try again");
            return -1;

        }
    }

    public String simulateAnswer(Bomb bomb) {
        double difficultyFactor = 0.0;

        switch (difficulty) {
            case 1:
                difficultyFactor = 0.33; // Low difficulty: 33% chance of correct answer
                break;
            case 2:
                difficultyFactor = 0.5; // Medium difficulty: 50% chance of correct answer
                break;
            case 3:
                difficultyFactor = 0.66; // High difficulty: 66% chance of correct answer
                break;
            default:
                difficultyFactor = 0.5; // Default to medium difficulty
                break;
        }

        double random = Math.random();

        if (random < difficultyFactor) {
            String hint = bomb.getHint();
            ArrayList<String> dictionary = bomb.getDictionary();

            ArrayList<String> filteredWords = new ArrayList<>();
            for (String word : dictionary) {
                if (word.contains(hint)) {
                    filteredWords.add(word);
                }
            }

            // Select a random word from filtered words
            if (!filteredWords.isEmpty()) {
                int randomIndex = (int) (Math.random() * filteredWords.size());
                String randomAnswer = filteredWords.get(randomIndex);
                return randomAnswer;
            }
        }
        return "x";
    }

}