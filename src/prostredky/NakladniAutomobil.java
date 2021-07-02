package prostredky;

public class NakladniAutomobil extends DopravniProstredek {

    private int pocetNaprav;
    private float nosnost;
    private Spolecnost spolecnost;

    public NakladniAutomobil(
            String spz,
            float hmotnost,
            int pocetNaprav,
            float nosnost,
            Spolecnost spolecnost) {
        super(ProstredekTyp.NAKLADNI_AUTMOBIL, spz, hmotnost);
        this.pocetNaprav = pocetNaprav;
        this.nosnost = nosnost;
        this.spolecnost = spolecnost;
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }

    public float getNosnost() {
        return nosnost;
    }

    public void setNosnost(float nosnost) {
        this.nosnost = nosnost;
    }

    public Spolecnost getSpolecnost() {
        return spolecnost;
    }

    public void setSpolecnost(Spolecnost spolecnost) {
        this.spolecnost = spolecnost;
    }

    @Override
    public String toString() {
        return super.toString() + "počet náprav: " + pocetNaprav + ", nosnost: " + nosnost + ", společnost: " + spolecnost;
    }

}
