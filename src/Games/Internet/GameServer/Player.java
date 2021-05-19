package Games.Internet.GameServer;

public class Player {
    String name;
    int score;
    int mistake;
    public Player(String name){
        this.name=name;
        this.score=0;
        this.mistake=0;

    }

    public Player(String name, int score, int mistake) {
        this.name = name;
        score = score;
        mistake = mistake;
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

}

