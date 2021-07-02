package command;

import Perzistence.Mapper;
import kolekce.KolekceException;
import prostredky.*;
import sprava.SpravaProstredku;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;
import kolekce.LinSeznam;

public class CommandLineMain {

    protected static SpravaProstredku spravaProstredku;
    private static final Comparator<DopravniProstredek> compareSPZ = (o1, o2) -> o1.getSpz().compareTo(o2.getSpz());
    private static final String FILE_NAME_BIN = "file.bin";
    private static final String FILE_NAME_TXT = "file.txt";
    private static final Function<DopravniProstredek, DopravniProstredek> editace = new Editor();
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        spravaProstredku = SpravaProstredku.vytvorSpravce(System.err::println, LinSeznam<DopravniProstredek>::new);
        spravaProstredku.vytvorSeznam(LinSeznam::new);
        spravaProstredku.nastavKomparator(compareSPZ);
        runCases();
        System.out.println("Konec programu.");
        System.out.println("Nazev prace: Evidence prostredku.");
        System.out.println("Student: Vladislav Volkov (I20292 - FEI)");
        System.out.println("Profesor: Ing. Karel Šimerda");
    }

    private static void runCases() throws IOException {
        String click;

        do {
            try {
                click = Keyboard.getStringItem("Zadejte přikaz...");
                switch (click) {
                    case "help":
                    case "h":
                        vypisHelp();
                        break;
                    case "novy":
                    case "no":
                        vytvorNovouPolozku();
                        break;
                    case "najdi":
                        najdiPolozku();
                        break;
                    case "dej":
                    case "de":
                        dej();
                        break;
                    case "edituj":
                        edit();
                        break;
                    case "vyjmi":
                        vyjmi();
                        break;
                    case "prvni":
                    case "pr":
                        prejdiNaPrvniPolozku();
                        break;
                    case "dalsi":
                        prejdiNaDalsiPolozku();
                        break;
                    case "posledni":
                    case "po":
                        prejdiNaPosledniPolozku();
                        break;
                    case "pocet":
                        pocetProstredku();
                        break;
                    case "obnov":
                        obnovZBin();
                        break;
                    case "zalohuj":
                        ulozDoBin();
                        break;
                    case "vypis":
                    case "vy":
                    case "v":
                        vypis();
                        break;
                    case "nactitext":
                    case "nt":
                        nactiZTxt();
                        break;
                    case "uloztext":
                    case "ut":
                        ulozDoTxt();
                        break;
                    case "generuj":
                    case "g":
                        generujNaVypis();
                        break;
                    case "zrus":
                        zrus();
                        break;
                    case "exit":
                        return;
                    default:
                        System.err.println("Neznamý příkaz: " + click.length());
                }
            } catch (KolekceException ex) {
                System.err.println("ERROR " + ex.getMessage());
            }
        } while (true);

    }

    private static void prejdiNaDalsiPolozku() {
        spravaProstredku.prejdiNaDalsiPolozku();
    }

    private static void zrus() {
        spravaProstredku.zrus();
    }

    private static void edit() throws KolekceException {
        spravaProstredku.editujAktualniPolozku(editace);
    }

    public static void generujNaVypis() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Pocet prvku = ");
        int prvky = scan.nextInt();
        spravaProstredku.generujData(prvky);
        System.out.println("Prostredky byly vygenerovany: " + prvky);
    }

    private static void pocetProstredku() {
        System.out.println("Pocet prostredku = " + spravaProstredku.dejAktualniPocetPolozek());
    }

    private static void vypis() {
        spravaProstredku.vypis(System.out::println);
    }

    private static void vyjmi() throws KolekceException {
        DopravniProstredek pr, dp;
        pr = spravaProstredku.dejPrvni();
        dp = spravaProstredku.vyjmiAktualniPolozku();
        if (pr == dp) {
            spravaProstredku.prejdiNaPrvniPolozku();
        }
        System.out.println("Odstraneny prostredek: " + dp);
        System.out.println("Novy seznam: ");
        spravaProstredku.vypis(System.out::println);
    }

    private static void ulozDoBin() {
        spravaProstredku.ulozDoSouboru(FILE_NAME_BIN);
    }

    private static void ulozDoTxt() {
        spravaProstredku.ulozTextSoubor(FILE_NAME_TXT, Mapper.mapperTo);
    }

    private static void obnovZBin() {
        spravaProstredku.nactiZeSouboru(FILE_NAME_BIN);
    }

    private static void nactiZTxt() {
        spravaProstredku.nactiTextSoubor(FILE_NAME_TXT, Mapper.mapperOut);
    }

    private static void vytvorNovouPolozku() throws IOException, KolekceException {
        Novy.novy();
    }


    private static void najdiPolozku() {
        System.out.println("SPZ: = ");
        Scanner scanner = new Scanner(System.in);
        String spz = scanner.next();
        DopravniProstredek dp = spravaProstredku.najdiPolozku(new DopravniProstredekKlic(spz.toUpperCase()));
        if (dp != null) {
            System.out.println(dp);
        } else {
            System.err.println("dopravni prostredek s " + spz + " nebyl nalezen");
        }
    }

    private static void prejdiNaPosledniPolozku() {
        spravaProstredku.prejdiNaPosledniPolozku();
    }

    private static void prejdiNaPrvniPolozku() {
        spravaProstredku.prejdiNaPrvniPolozku();
    }

    private static void dej() throws KolekceException {
        System.out.println(spravaProstredku.dejKopiiAktualniPolozky());
    }

    private static void vypisHelp() {
        System.out.println(""
                + "help, h - výpis průkazu\n"
                + "novy, no - vytvoř novou instanci a vlož dopravního prostředku za aktualní\n"
                + "najdi, na, n - najdi v seznamu dopravní prostředek podle státní poznávací značky\n"
                + "dej, de - zobraz aktuální dopravní prostředky v seznamu\n"
                + "vyjmi - vyjmi aktuální dopravní prostředek ze seznamu\n"
                + "edituj, edit - edituj dopravní prostředek v seznamu\n"
                + "prvni, pr - nastav jako aktuální první dopravní prostředek v seznamu\n"
                + "dalsi, da - přejdi na další dopravní prostředek posledni, po - přejdi na poslední dopravní prostředek\n"
                + "počet - zobraz počet položek v seznamu\n"
                + "obnov - obnov seznam dopravních prostředku z binárního souboru\n"
                + "zalohuj - zálohuj seznam dopravních prostředku do binárního souboru\n"
                + "vypis, v - zobraz seznam dopravních prostředků\n"
                + "nactitext, nt - načti seznam dopravnich prostředků do textového souboru\n"
                + "uloztext, ut - ulož seznam dopravnich prostředků do textového souboru\n"
                + "generuj, g - generuj náhodné dopravní prostředky pro testování\n"
                + "zrus - zruš všechny dopravní prostředky v seznamu\n"
                + "exit - ukončeni programu\n"
        );
    }
}
