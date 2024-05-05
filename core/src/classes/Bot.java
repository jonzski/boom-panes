package classes;

import java.util.ArrayList;

public class Bot extends Player{

    private int difficulty;

    public Bot(int healthValue, String name, boolean isDead, int difficulty) {
        super(healthValue, name, isDead);
        this.difficulty = difficulty;
    }

    public Boolean answer(Bomb bomb, String answer) {
        System.out.println("Bot " + name + " answered: " + answer);
        if (bomb.checkAnswer(answer)) {
            System.out.println("Correct answer");
            return true;
        } else {
            System.out.println("Incorrect answer, try again");
            return false;
//            this.reducePlayerHealth();
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

            // Filter dictionary words based on hint
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
