package classes;

public class Bot extends Player{
    public Bot(int healthValue, String name, boolean isDead) {
        super(healthValue, name, isDead);
    }

    public void answer(Bomb bomb, String answer) {
        System.out.println("Bot " + name + " answered + " + answer);
        if (bomb.checkAnswer(answer)) {
            System.out.println("Correct answer");
        } else {
            reducePlayerHealth();
        }
    }

    public void simulateAnswer(Bomb bomb) {
        int randomIndex = (int) (Math.random() * bomb.getDictionary().size());
        String randomAnswer = bomb.getDictionary().get(randomIndex);
        answer(bomb, randomAnswer);
    }

}
