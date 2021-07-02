package generator;

import java.util.Random;
import kolekce.LinSeznam;
import prostredky.Barva;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.ProstredekTyp;
import prostredky.Spolecnost;
import prostredky.Traktor;

public class Generator {

    private static final Random random = new Random();

    public Generator() {
    }

    public static DopravniProstredek generujProstredek() {
        int type = random.nextInt(ProstredekTyp.getProstredky().length);
        switch (ProstredekTyp.values()[type]) {
            case OSOBNI_AUTOMOBIL:
                return novyOsobniAutomobil();
            case DODAVKA:
                return novaDodavka();
            case NAKLADNI_AUTMOBIL:
                return novyNakladniAutomobile();
            case TRAKTOR:
                return novyTraktor();
        }
        return null;
    }

    public static LinSeznam<DopravniProstredek> generuj(int pocetProstredku) {
        LinSeznam<DopravniProstredek> seznam = new LinSeznam<>();
        for (int i = 0; i < pocetProstredku; i++) {
            seznam.vlozNaKonec(generujProstredek());
        }
        return seznam;
    }

    private static DopravniProstredek novyOsobniAutomobil() {
        DopravniProstredek prostredek;
        prostredek = new OsobniAutomobil(
                novaSPZ(),
                novaHmotnost(2000),
                novaBarva(),
                novyPocetSedadel()) {
        };
        return prostredek;
    }

    private static DopravniProstredek novaDodavka() {
        DopravniProstredek prostredek;
        prostredek = new Dodavka(
                novaSPZ(),
                novaHmotnost(3000),
                novyTypDodavky());
        return prostredek;
    }

    private static DopravniProstredek novyNakladniAutomobile() {
        DopravniProstredek prostredek;
        prostredek = new NakladniAutomobil(
                novaSPZ(),
                novaHmotnost(5000),
                novyPocetNaprav(),
                novaNosnost(),
                novaSpolecnost()) {
        };
        return prostredek;
    }

    private static DopravniProstredek novyTraktor() {
        DopravniProstredek prostredek;
        prostredek = new Traktor(
                novaSPZ(),
                novaHmotnost(9000),
                novyTah(),
                novyVykon(),
                novaSpolecnost()) {
        };
        return prostredek;
    }

    private static String novaSPZ() {
        StringBuilder spz = new StringBuilder();
        spz.append((char) ('A' + random.nextInt(26))).
                append((char) ('A' + random.nextInt(26))).
                append((char) ('0' + random.nextInt(10))).
                append((char) ('0' + random.nextInt(10))).
                append((char) ('0' + random.nextInt(10))).
                append((char) ('0' + random.nextInt(10)));
        return spz.toString();
    }

    private static float novaHmotnost(int hmotnost) {
        return random.nextInt(hmotnost / 500) * 500 + 1000;
    }

    private static Barva novaBarva() {
        return Barva.values()[random.nextInt(Barva.values().length)];
    }

    private static int novyPocetSedadel() {
        return random.nextInt(8) + 2;
    }

    private static DodavkaTyp novyTypDodavky() {
        return DodavkaTyp.values()[random.nextInt(DodavkaTyp.values().length)];
    }

    private static int novyVykon() {
        return random.nextInt(100) * 5;
    }

    private static Spolecnost novaSpolecnost() {
        return Spolecnost.values()[random.nextInt(Spolecnost.values().length)];
    }

    private static int novyPocetNaprav() {
        return random.nextInt(8) + 2;
    }

    private static float novaNosnost() {
        return random.nextInt(100) * 500 + 5000;
    }

    private static int novyTah() {
        return random.nextInt(1000) * 10 + 1000;
    }

}
