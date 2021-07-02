package prostredky;

import java.io.Serializable;

public class DopravniProstredek implements Serializable,
        Cloneable, Comparable<DopravniProstredek> {

    private static int counter = 0;
    private final int id;
    private ProstredekTyp type;
    private String spz;
    private float hmotnost;

    public DopravniProstredek(int id, String spz, float hmotnost) {
        this.id = id;
        this.spz = spz;
        this.hmotnost = hmotnost;
    }

    protected DopravniProstredek(ProstredekTyp type, int id, String spz) {
        this.id = id;
        this.type = type;
        this.spz = spz;
    }

    public DopravniProstredek(ProstredekTyp type) {
        id = counter++;
        this.type = type;
    }

    public DopravniProstredek(ProstredekTyp type, String spz, float hmotnost) {
        this(type);
        this.spz = spz;
        this.hmotnost = hmotnost;
    }

    public int getId() {
        return id;
    }

    public ProstredekTyp getType() {
        return type;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        DopravniProstredek.counter = counter;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public float getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(float hmotnost) {
        this.hmotnost = hmotnost;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", typ prostředku: " + type + ", SPZ: " + spz + ", hmotnost: " + hmotnost + ", ";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(DopravniProstredek prostredek) {
        return spz.compareTo(prostredek.getSpz());
    }

}
