package command;

import kolekce.KolekceException;
import prostredky.*;

import java.io.IOException;
import java.util.Scanner;

import static command.CommandLineMain.spravaProstredku;

public class Novy{

    public static void novy() throws IOException, KolekceException {
        DopravniProstredek dp = null;
        System.out.println("Typ dopravniho prostredku: ");
        System.out.println("1 - Osobni auto");
        System.out.println("2 - Nakldani auto");
        System.out.println("3 - Traktor");
        System.out.println("4 - Dodavka");
        Scanner scan = new Scanner(System.in);
        float hmotnost;
        String SPZ;
        Barva barva;
        Spolecnost spolecnost;
        DodavkaTyp dodavkaTyp;
        int pocetNaprav, nosnost, tah, vykon, pocetSedadel;
        switch (scan.nextInt()) {
            case 1:
                System.out.println("SPZ (AA0000): ");
                SPZ = scan.next();
                System.out.println("Hmotnost: ");
                hmotnost = scan.nextFloat();
                System.out.println("Barva: ");
                for (int i = 0; i < Barva.values().length; i++) {
                    System.out.println(i + " - " + Barva.values()[i].toString());
                }
                barva = Barva.values()[scan.nextInt()];
                System.out.println("Pocet sidadel: ");
                pocetSedadel = scan.nextInt();
                dp = new OsobniAutomobil(SPZ, hmotnost, barva, pocetSedadel);
                break;
            case 2:
                System.out.println("SPZ (AA0000): ");
                SPZ = scan.next();
                System.out.println("Hmotnost: ");
                hmotnost = scan.nextFloat();
                System.out.println("Pocet naprav: ");
                pocetNaprav = scan.nextInt();
                System.out.println("Nosnost: ");
                nosnost = scan.nextInt();
                System.out.println("Spolecnost: ");
                for (int i = 0; i < Spolecnost.values().length; i++) {
                    System.out.println(i + " - " + Spolecnost.values()[i].toString());
                }
                spolecnost = Spolecnost.values()[scan.nextInt()];
                dp = new NakladniAutomobil(SPZ, hmotnost, pocetNaprav, nosnost, spolecnost);
                break;
            case 3:
                System.out.println("SPZ (AA0000): ");
                SPZ = scan.next();
                System.out.println("Hmotnost: ");
                hmotnost = scan.nextFloat();
                System.out.println("Tah: ");
                tah = scan.nextInt();
                System.out.println("Vykon: ");
                vykon = scan.nextInt();
                System.out.println("Spolecnost: ");
                for (int i = 0; i < Spolecnost.values().length; i++) {
                    System.out.println(i + " - " + Spolecnost.values()[i].toString());
                }
                spolecnost = Spolecnost.values()[scan.nextInt()];
                dp = new Traktor(SPZ, hmotnost, tah, vykon, spolecnost);
                break;
            case 4:
                System.out.println("SPZ (AA0000): ");
                SPZ = scan.next();
                System.out.println("Hmotnost: ");
                hmotnost = scan.nextFloat();
                System.out.println("Typ dodavky : ");
                for (int i = 0; i < DodavkaTyp.values().length; i++) {
                    System.out.println(i + " - " + DodavkaTyp.values()[i].toString());
                }
                dodavkaTyp = DodavkaTyp.values()[scan.nextInt()];
                dp = new Dodavka(SPZ, hmotnost, dodavkaTyp);
                break;
        }
        System.out.println("Novy dp byl nastaven poslednim v seznamu: " + dp);
        System.out.println("Novy seznam: ");
        spravaProstredku.vlozPolozku(dp);
        spravaProstredku.vypis(System.out::println);
    }
}