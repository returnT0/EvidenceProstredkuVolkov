package prostredky;

public enum DodavkaTyp {
    VALNIK("Valnik"),
    DVOJKABINA("Dvojkabina"),
    MIKRO("Mikrobus"),
    PLACHTOVA("Plachtova");

    public static DodavkaTyp list(String type) {
        DodavkaTyp dodavkaTyp = null;
        switch (type) {
            case "Valnik":
                return DodavkaTyp.VALNIK;
            case "Dvojkabina":
                return DodavkaTyp.DVOJKABINA;
            case "Mikrobus":
                return DodavkaTyp.MIKRO;
            case "Plachtova":
                return DodavkaTyp.PLACHTOVA;
        }
        return dodavkaTyp;
    }

    private final String nazev;

    private DodavkaTyp(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
