
public class Result {
    private int lives = 6;
    public int getLives(){
        return this.lives;
    }
    public void setLives(int lives){
        this.lives = lives;
    }
    public void reduceLives(){
        this.lives --;
    }
}