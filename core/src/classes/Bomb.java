package classes;

public class Bomb {
    private String hintLetter;
    private String answeredWord;
    private boolean isExploded;
    private int explosionCooldown;

    public Bomb(String hintLetter, String answeredWord, boolean isExploded, int explosionCooldown){
        this.hintLetter = hintLetter;
        this.answeredWord = answeredWord;
        this.isExploded = false;
        this.explosionCooldown = explosionCooldown;
    }

    public String getBufferLetter() {
        return hintLetter;
    }

    public void setBufferLetter(String hintLetter) {
        this.hintLetter = hintLetter;
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
}


