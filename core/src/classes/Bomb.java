package classes;

public class Bomb {
    private final String hintLetter;
    private String answeredWord;
    private boolean isExploded;
    private int explosionCooldown;

    public Bomb(String hintLetter, boolean isExploded, int explosionCooldown){
        this.hintLetter = hintLetter;
        this.isExploded = false;
        this.explosionCooldown = explosionCooldown;
    }

    public void checkAnswer(String answeredWord){

    }

    public void setAnsweredWord(String answeredWord){
        this.answeredWord = answeredWord;
    }

    public String getHintLetter() {
        return hintLetter;
    }

    public int getExplosionCooldown() {
        return explosionCooldown;
    }

    public void explode(){
        isExploded = true;
    }

    public void setExplosionCooldown(int explosionCooldown) {
        this.explosionCooldown = explosionCooldown;
    }

    public String getAnsweredWord() {
        return answeredWord;
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
