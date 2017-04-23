package sample;


public class Cell {
    private int id;
    private String name;
    private float score;

    public Cell(int id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public float getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
