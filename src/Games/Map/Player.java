package Games.Map;

public class Player {
    String name;
    String characteristic;
    int score;
    int mistake;
    public Player(String name, String characteristic){
        this.name=name;
        this.characteristic = characteristic;
        this.score=0;
        this.mistake=0;
    }

    public Player(String name, String characteristic, int score, int mistake) {
        this.name = name;
        this.characteristic = characteristic;
        this.score = score;
        this.mistake = mistake;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getMistake() {
        return mistake;
    }
    public void addScore(int num){
        this.score+=num;
    }
    public void loseScore(int num){
        this.score-=num;
    }
    public void addMistake(){
        mistake++;
    }

    public String toString(){
        String str = name + " " + score + " " + mistake;
        return str;
    }

}

