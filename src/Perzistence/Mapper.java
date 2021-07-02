package Perzistence;

import prostredky.*;

import java.util.Locale;
import java.util.function.Function;

public class Mapper {

    public Mapper() {
    }

    public static final Function<String, DopravniProstredek> mapperOut = (line) -> {
        DopravniProstredek dopravniProstredek = null;
        if (line.length() == 0){
            return dopravniProstredek;
        }
        String[] items = line.split(",");
        ProstredekTyp prostredekTyp = ProstredekTyp.list(items[1].trim());
        String spz = items[2].trim();
        float hmotnost = Float.parseFloat(items[3].trim());
        switch (items[1].trim()) {
            case "osobní automobil":
                Barva barva = Barva.list(items[4].trim());
                int pocetSedadel = Integer.parseInt(items[5].trim());
                return  dopravniProstredek = new OsobniAutomobil(spz, hmotnost, barva, pocetSedadel);
                case "dodávka":
                DodavkaTyp dodavkaTyp = DodavkaTyp.list(items[4].trim());
                return dopravniProstredek = new Dodavka(spz, hmotnost, dodavkaTyp);
            case "nákladní automobil":
                int pocetNaprav = Integer.parseInt(items[4].trim());
                float nosnost = Float.parseFloat(items[5].trim());
                Spolecnost spolecnostNA = Spolecnost.list(items[6].trim());
                return dopravniProstredek = new NakladniAutomobil(spz, hmotnost, pocetNaprav, nosnost, spolecnostNA);
            case "traktor":
                int tah = Integer.parseInt(items[4].trim());
                int vykon = Integer.parseInt(items[5].trim());
                Spolecnost spolecnostTR = Spolecnost.list(items[6].trim());
                return dopravniProstredek = new Traktor(spz, hmotnost, tah, vykon,spolecnostTR);
        }
        return null;
    };


    public static final Function<DopravniProstredek, String> mapperTo = (line) -> {
        String prostredek = line.getId() + ", ";
        prostredek += line.getType().getNazev() + ", ";
        prostredek += line.getSpz() + ", ";
        prostredek += line.getHmotnost() + ", ";

        switch (line.getType()) {
            case DODAVKA:
                Dodavka dodavka = (Dodavka) line;
                prostredek += dodavka.getTypeD().getNazev();
                break;
            case TRAKTOR:
                Traktor traktor = (Traktor) line;
                prostredek += traktor.getTah() + ", " + traktor.getVykon() + ", " + traktor.getSpolecnost().getNazev();
                break;
            case OSOBNI_AUTOMOBIL:
                OsobniAutomobil osobniAutomobil = (OsobniAutomobil) line;
                prostredek += osobniAutomobil.getBarva().getBarva() + ", " + osobniAutomobil.getPocetSedadel();
                break;
            case NAKLADNI_AUTMOBIL:
                NakladniAutomobil nakladniAutomobil = (NakladniAutomobil) line;
                prostredek += nakladniAutomobil.getPocetNaprav() + ", " + nakladniAutomobil.getNosnost() + ", " + nakladniAutomobil.getSpolecnost().getNazev();
                break;

        }
        return prostredek;
    };
}
