package prostredky;

public enum Spolecnost {
    VOLVO("Volvo"),
    FIAT("Fiat"),
    LANCIA("Lancia"),
    PEUGEOT("Peugeot"),
    SKODA("Škoda"),
    LAND_ROVER("Land Rover"),
    BMW("BMW");

    public static Spolecnost list(String type) {
        Spolecnost spolecnost = null;
        switch (type) {
            case "Volvo":
                return Spolecnost.VOLVO;
            case "Fiat":
                return Spolecnost.FIAT;
            case "Lancia":
                return Spolecnost.LANCIA;
            case "Peugeot":
                return Spolecnost.PEUGEOT;
            case "BMW":
                return Spolecnost.BMW;
            case "Škoda":
                return Spolecnost.SKODA;
            case "Land Rover":
                return Spolecnost.LAND_ROVER;

        }
        return spolecnost;
    }
    private final String nazevSpolecnosti;

    private Spolecnost(String nazev) {
        this.nazevSpolecnosti = nazev;

    }

    public String getNazev() {
        return nazevSpolecnosti;
    }

    @Override
    public String toString() {
        return nazevSpolecnosti;
    }

}
