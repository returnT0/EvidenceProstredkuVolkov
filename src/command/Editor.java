package command;

import prostredky.*;

import java.util.function.Function;

public class Editor implements Function<DopravniProstredek, DopravniProstredek> {

    public Editor() {
    }

    @Override
    public DopravniProstredek apply(DopravniProstredek prostredek) {
        System.out.println("Vytvorte novou SPZ:");
        prostredek.setSpz(Keyboard.getStringItem("SPZ aktualniho prostredku: " + prostredek.getSpz()));
        System.out.println("Vytvorte novou hmotnost:");
        prostredek.setHmotnost(Keyboard.getFloatItem("Hmotnost aktualniho prostedku: " + prostredek.getHmotnost()));

        switch (prostredek.getType()) {
            case DODAVKA:
                Dodavka dodavka = (Dodavka) prostredek;
                String typeDT = Keyboard.getStringItem("Typ dodavky: " + dodavka.getTypeD().getNazev());
                DodavkaTyp dodavkaTyp = null;
                while (dodavkaTyp == null) {
                    System.out.println("");
                    System.out.println("Typy dodavek: ");
                    typeDT = Keyboard.getStringItem("Valník; "
                            + "Dvojkabina; "
                            + "Mikrobus; "
                            + "Plachtová.");
                    dodavkaTyp = DodavkaTyp.list(typeDT);
                    if (dodavkaTyp == null) {
                        System.err.println("Chybne udaje!!!");
                    }
                }
                dodavka.setTypeD(dodavkaTyp);
                break;
            case TRAKTOR:
                Traktor traktor = (Traktor) prostredek;
                System.out.println("Vytvorte novy tah traktoru");
                traktor.setTah(Keyboard.getIntItem("Tah aktualniho prostedku: " + traktor.getTah()));
                traktor.setVykon(Keyboard.getIntItem("Vykon aktualniho prostedku: " + traktor.getVykon()));
                String typeTS = Keyboard.getStringItem("Typ spolecnosti: " + traktor.getSpolecnost().getNazev());
                Spolecnost spolecnost = null;
                while (spolecnost == null) {
                    System.out.println("");
                    System.out.println("Spolecnost: ");
                    typeTS = Keyboard.getStringItem("Volvo; "
                            + "Fiat; "
                            + "Lancia; "
                            + "Peugeot; "
                            + "Škoda; "
                            + "Land Rover; "
                            + "BMW.");
                    spolecnost = Spolecnost.list(typeTS);
                    if (spolecnost == null) {
                        System.err.println("Chybne udaje!!!");
                    }
                }
                traktor.setSpolecnost(spolecnost);
                break;
            case NAKLADNI_AUTMOBIL:
                NakladniAutomobil nakladniAutomobil = (NakladniAutomobil) prostredek;
                System.out.println("Vytvorte novou nosnost:");
                nakladniAutomobil.setNosnost(Keyboard.getFloatItem("Nostnost aktualniho prostredku: " + nakladniAutomobil.getNosnost()));
                System.out.println("Vytvorte novy pocet naprav: ");
                nakladniAutomobil.setPocetNaprav(Keyboard.getIntItem("Pocet naprav aktualniho prostredku: " + nakladniAutomobil.getPocetNaprav()));
                String typeNS = Keyboard.getStringItem("Typ spolecnosti: " + nakladniAutomobil.getSpolecnost().getNazev());
                Spolecnost spolecnostNA = null;
                while (spolecnostNA == null) {
                    System.out.println("");
                    System.out.println("Spolecnost: ");
                    typeTS = Keyboard.getStringItem("Volvo; "
                            + "Fiat; "
                            + "Lancia; "
                            + "Peugeot; "
                            + "Škoda; "
                            + "Land Rover; "
                            + "BMW.");
                    spolecnostNA = Spolecnost.list(typeNS);
                    if (spolecnostNA == null) {
                        System.err.println("Chybne udaje!!!");
                    }
                }
                nakladniAutomobil.setSpolecnost(spolecnostNA);
                break;
            case OSOBNI_AUTOMOBIL:
                OsobniAutomobil osobniAutomobil = (OsobniAutomobil) prostredek;
                System.out.println("Vytvorte novy pocet sidadel: ");
                osobniAutomobil.setPocetSedadel(Keyboard.getIntItem("Pocet sidadel akrualniho prostredku: " + osobniAutomobil.getPocetSedadel()));
                String barvaDP = Keyboard.getStringItem("Barva aktualniho prostredku: " + osobniAutomobil.getBarva().getBarva());
                Barva barva = null;
                while (barva == null) {
                    System.out.println("");
                    System.out.println("Barva: ");
                    barvaDP = Keyboard.getStringItem("Černa; "
                            + "Bila; "
                            + "Modra; "
                            + "Červena; "
                            + "Hněda; "
                            + "Střibna; "
                            + "Zelena.");
                    barva = Barva.list(barvaDP);
                    if (barva == null) {
                        System.err.println("Chybne udaje!!!");
                    }
                }
                osobniAutomobil.setBarva(barva);
                break;
        }
        System.out.print("\nEditovany vami prostredek: " + prostredek + "\n");
        return prostredek;
    }

}
