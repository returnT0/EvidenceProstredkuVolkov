package prostredky;

public class Dodavka extends DopravniProstredek {

    private DodavkaTyp typeD;

    public Dodavka(
            String spz,
            float hmotnost,
            DodavkaTyp typeD) {
        super(ProstredekTyp.DODAVKA, spz, hmotnost);
        this.typeD = typeD;
    }

    public DodavkaTyp getTypeD() {
        return typeD;
    }

    public void setTypeD(DodavkaTyp typeD) {
        this.typeD = typeD;
    }

    @Override
    public String toString() {
        return super.toString() + "typ dodávky: " + typeD;
    }

}
