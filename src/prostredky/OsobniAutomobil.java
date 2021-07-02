package prostredky;

public class OsobniAutomobil extends DopravniProstredek {

    private Barva barva;
    private int pocetSedadel;

    public OsobniAutomobil(
            String spz,
            float hmotnost,
            Barva barva,
            int pocetSedadel) {
        super(ProstredekTyp.OSOBNI_AUTOMOBIL, spz, hmotnost);
        this.barva = barva;
        this.pocetSedadel = pocetSedadel;

    }

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    @Override
    public String toString() {
        return super.toString() + "barva: " + barva + ", počet sedadel: " + pocetSedadel;
    }

}
