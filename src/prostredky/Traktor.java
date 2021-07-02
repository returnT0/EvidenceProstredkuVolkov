package prostredky;

public class Traktor extends DopravniProstredek {

    private int tah;
    private int vykon;
    private Spolecnost spolecnost;

    public Traktor(
            String spz,
            float hmotnost,
            int tah,
            int vykon,
            Spolecnost spolecnost) {
        super(ProstredekTyp.TRAKTOR, spz, hmotnost);
        this.tah = tah;
        this.vykon = vykon;
        this.spolecnost = spolecnost;
    }

    public int getTah() {
        return tah;
    }

    public void setTah(int tah) {
        this.tah = tah;
    }

    public int getVykon() {
        return vykon;
    }

    public void setVykon(int vykon) {
        this.vykon = vykon;
    }

    public Spolecnost getSpolecnost() {
        return spolecnost;
    }

    public void setSpolecnost(Spolecnost spolecnost) {
        this.spolecnost = spolecnost;
    }

    @Override
    public String toString() {
        return super.toString() + "tah: " + tah + ", výkon: " + vykon + ", společnost: " + spolecnost;
    }
}
