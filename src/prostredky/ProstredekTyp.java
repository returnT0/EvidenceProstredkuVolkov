package prostredky;

public enum ProstredekTyp {
    OSOBNI_AUTOMOBIL("osobní automobil"),
    NAKLADNI_AUTMOBIL("nákladní automobil"),
    DODAVKA("dodávka"),
    TRAKTOR("traktor"),
    TEST("test"),
    KLIC("klíč"),
    NON_FILTER("nefiltruj");

    private final String nazev;

    private ProstredekTyp(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public static ProstredekTyp list(String prostredek) {
        ProstredekTyp prostredekTyp = null;
        switch (prostredek) {
            case "osobní automobil":
                return ProstredekTyp.OSOBNI_AUTOMOBIL;
            case "nákladní automobil":
                return ProstredekTyp.NAKLADNI_AUTMOBIL;
            case "dodávka":
                return ProstredekTyp.DODAVKA;
            case "traktor":
                return ProstredekTyp.TRAKTOR;
        }
        return prostredekTyp;
    }

    public static ProstredekTyp listFilter(String prostredek) {
        ProstredekTyp prostredekTyp = null;
        switch (prostredek) {
            case "osobní automobil":
                return ProstredekTyp.OSOBNI_AUTOMOBIL;
            case "nákladní automobil":
                return ProstredekTyp.NAKLADNI_AUTMOBIL;
            case "dodávka":
                return ProstredekTyp.DODAVKA;
            case "traktor":
                return ProstredekTyp.TRAKTOR;
//            case "test":
//                return ProstredekTyp.TEST;
//            case "klíč":
//                return ProstredekTyp.KLIC;
            case "nefiltruj":
                return ProstredekTyp.NON_FILTER;
        }
        return prostredekTyp;
    }

    public static Enum[] getProstredky() {
        Enum[] vycet = {OSOBNI_AUTOMOBIL, NAKLADNI_AUTMOBIL, DODAVKA, TRAKTOR};
        return vycet;
    }

    public static Enum[] getProstredkyFilter() {
        Enum[] vycet = {OSOBNI_AUTOMOBIL, NAKLADNI_AUTMOBIL, DODAVKA, TRAKTOR, NON_FILTER};
        return vycet;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
