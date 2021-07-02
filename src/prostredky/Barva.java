package prostredky;

public enum Barva {
    CERNA("Černa"),
    BILA("Bila"),
    MODRA("Modra"),
    CERVENA("Červena"),
    HNEDA("Hněda"),
    STRIBNA("Střibna"),
    ZELENA("Zelena");

    public static Barva list(String type) {
        Barva barva = null;
        switch (type) {
            case "Černa":
                return Barva.CERNA;
            case "Bila":
                return Barva.BILA;
            case "Modra":
                return Barva.MODRA;
            case "Červena":
                return Barva.CERVENA;
            case "Zelena":
                return Barva.ZELENA;
            case "Hněda":
                return Barva.HNEDA;
            case "Střibna":
                return Barva.STRIBNA;
        }
        return barva;
    }

    private final String barva;

    private Barva(String barva) {
        this.barva = barva;
    }

    public String getBarva() {
        return barva;
    }

    @Override
    public String toString() {
        return barva;
    }

}
