package Games.Internet.GameClient;

public class Player {
    String name;
    int score;
    int mistake;
    int id;
    public Player(String name,int id){
        this.name=name;
        this.score=0;
        this.mistake=0;
        this.id=id;
    }

    public Player(String name, int score, int mistake,int id) {
        this.name = name;
        this.score = score;
        this.mistake = mistake;
        this.id=id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        String str = name + " " + score + " " + mistake;
        return str;
    }

}

