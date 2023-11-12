

public class Toy {
    private Integer id;
    private String title;
    private Integer number;
    private Integer frequency;

    public Toy(Integer id, String title, Integer number, Integer frequency){
        this.id = id;
        this.title = title;
        this.number = number;
        this.frequency = frequency;
    }

    public void setID(Integer id) {
        this.id = id;
    }
    public Integer getID() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getNumber() {
        return number;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    public Integer getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Title: " + this.title + ", Number: " + this.number + ", Frequency: " + this.frequency;
        
    }
    
    
    public int compare(Toy t1, Toy t2) {
        return (int) (t1.getID() - t2.getID());
    }
}