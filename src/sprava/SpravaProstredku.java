package sprava;

import Perzistence.Perzistence;
import generator.Generator;
import kolekce.KolekceException;
import kolekce.Seznam;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekKlic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpravaProstredku implements Ovladani {

    private Seznam<DopravniProstredek> seznam;
    private Comparator<? super DopravniProstredek> comparator;
    private Consumer<String> errorLog;

    public SpravaProstredku(Consumer<String> myError) {
        this.errorLog = myError;
    }

    public void vlozZaAktualni(DopravniProstredek data) throws KolekceException {
        seznam.vlozZaAktualni(data);
    }

    public void vlozPredAktualni(DopravniProstredek data) {
        seznam.vlozPredAktualni(data);
    }

    public boolean jePrazdny() throws KolekceException {
        return seznam.jePrazdny();
    }

    public DopravniProstredek dejPrvni() throws KolekceException {
        return seznam.dejPrvni();
    }

    public DopravniProstredek dejAktualni() throws KolekceException {
        return seznam.dejAktualni();
    }

    public DopravniProstredek dejPredchozi() throws KolekceException {
        return seznam.dejPredAktualnim();
    }

    public DopravniProstredek dejZaAktualnim() throws KolekceException {
        return seznam.dejZaAktualnim();
    }

    public DopravniProstredek dejPosledni() throws KolekceException {
        return seznam.dejPosledni();
    }

    public DopravniProstredek odeberZaAktualnim() throws KolekceException {
        return seznam.odeberZaAktualnim();
    }

    public DopravniProstredek odeberPredAktualnim() throws KolekceException {
        return seznam.odeberPredAktualnim();
    }

    public static SpravaProstredku vytvorSpravce(Consumer<String> errorLog, Supplier<Seznam<DopravniProstredek>> supplier) {
        SpravaProstredku spravaProstredku = new SpravaProstredku(errorLog);
        spravaProstredku.vytvorSeznam(supplier);
        return spravaProstredku;
    }

    @Override
    public void vytvorSeznam(Supplier<Seznam<DopravniProstredek>> supplier) {
        Objects.requireNonNull(supplier);
        seznam = supplier.get();
    }

    @Override
    public void vytvorSeznam(Function<Integer, Seznam<DopravniProstredek>> function, int size) throws KolekceException {
        seznam = function.apply(size);
        if (seznam == null) {
            throw new KolekceException("Seznam je prazdný");
        }
    }

    @Override
    public void nastavKomparator(Comparator<? super DopravniProstredek> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public void vlozPolozku(DopravniProstredek data) throws NullPointerException {
        Objects.requireNonNull(data);
        seznam.vlozNaKonec(data);
    }

    @Override
    public DopravniProstredek najdiPolozku(DopravniProstredekKlic klic) {
        return (DopravniProstredek) stream().
                filter((polozka) -> comparator.compare(polozka, klic) == 0).
                findFirst().get();
    }

    @Override
    public void prejdiNaPrvniPolozku() {
        try {
            seznam.nastavPrvni();
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void prejdiNaPosledniPolozku() {
        try {
            seznam.nastavPosledni();
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void prejdiNaDalsiPolozku() {
        try {
            seznam.dalsi();
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void prejdiNaPredchoziPolozku() {
        try {
            DopravniProstredek aktualni = seznam.dejAktualni();
            DopravniProstredek predchozi = seznam.dejPrvni();
            seznam.nastavPrvni();
            while (predchozi != seznam.dejPosledni()) {
                DopravniProstredek newAktualni = seznam.dejAktualni();
                if (newAktualni == seznam.dejPosledni()) {
                    return;
                }
                DopravniProstredek novy = seznam.dejZaAktualnim();
                if (novy == aktualni) {
                    return;
                }
                seznam.dalsi();
                predchozi = newAktualni;
            }
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public DopravniProstredek nastavAktualniPolozku(DopravniProstredekKlic klic) {
        try {
            String spz = klic.getSpz();
            if (spz.isEmpty()) {
                return null;
            }
            seznam.nastavPrvni();
            for (int i = 0; i < seznam.size(); i++) {
                DopravniProstredek a = seznam.dejAktualni();
                if (spz.equals(a.getSpz())) {
                    return seznam.dejAktualni();
                }
                seznam.dalsi();
            }
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public DopravniProstredek vyjmiAktualniPolozku() {
        try {
            return seznam.odeberAktualni();
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
            return null;
        }
    }

    @Override
    public DopravniProstredek dejKopiiAktualniPolozky() {
        try {
            return (DopravniProstredek) seznam.dejAktualni().clone();
        } catch (KolekceException | CloneNotSupportedException e) {
            errorLog.accept(e.getMessage());
            return null;
        }
    }

    @Override
    public void editujAktualniPolozku(Function<DopravniProstredek, DopravniProstredek> editor) {
        try {
            editor.apply(seznam.dejAktualni());
        } catch (KolekceException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void ulozDoSouboru(String soubor) {
        try {
            Perzistence.uloz(soubor, seznam);
        } catch (IOException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void nactiZeSouboru(String soubor) {
        try {
            seznam.zrus();
            Perzistence.nacti(soubor, seznam);
        } catch (IOException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void vypis(Consumer<DopravniProstredek> writer) {
        stream().forEach(writer);
    }

    @Override
    public void nactiTextSoubor(String soubor, Function<String, DopravniProstredek> mapper) {
//        try {
//            seznam.zrus();
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(new FileInputStream(soubor)));
//            String line;
//            DopravniProstredek dp = null;
//            while ((line = rd.readLine()) != null) {
//                System.out.println(line);
//            }
//            Files.lines(Paths.get(soubor), StandardCharsets.UTF_8).
//                    filter(Objects::nonNull).
//                    map(mapper).forEach((action) -> seznam.vlozNaKonec(action));
//        } catch (IOException e) {
//            errorLog.accept(e.getMessage());
//        }
        try {
            seznam.zrus();
            Files.lines(Paths.get(soubor),StandardCharsets.UTF_8)
                    .filter(t -> t !=null)
                    .map(mapper)
                    .forEach(t -> {seznam.vlozNaKonec(t); });
        } catch (IOException e) {
            errorLog.accept(e.getMessage());
        }
    }

    @Override
    public void ulozTextSoubor(String soubor, Function<DopravniProstredek, String> mapper) {
//        try (PrintWriter printWriter = new PrintWriter(soubor, "UTF-8")) {
//            stream().map(mapper).forEachOrdered(printWriter::println);
//        } catch (FileNotFoundException | UnsupportedEncodingException e) {
//            errorLog.accept(e.getMessage());
//        }
        try(PrintWriter pw = new PrintWriter(soubor, "UTF-8")) {
            stream().map(mapper).forEachOrdered(pw::println);

        } catch (FileNotFoundException|UnsupportedEncodingException ex) {
            errorLog.accept(ex.getMessage());
        }
    }

    @Override
    public void generujData(int pocetProstredku) {
        zrus();
        seznam = Generator.generuj(pocetProstredku);

    }

    @Override
    public void nastavErrorLog(Consumer<String> errorLog) {
        this.errorLog = errorLog;
    }

    public void errorLog(Exception exception) {
        if (errorLog == null) {
            Logger.getLogger(SpravaProstredku.class.getName()).log(Level.WARNING,
                    "still dont know where is an error");
        } else {
            errorLog.accept(SpravaProstredku.class.getName());
        }
    }

    public void errorLog(String msg) {
        if (errorLog == null) {
            Logger.getLogger(SpravaProstredku.class.getName()).log(Level.WARNING,
                    "sorry, mate...idk where's an error");
        } else {
            errorLog.accept(SpravaProstredku.class.getName() + "");
        }
    }

    @Override
    public int dejAktualniPocetPolozek() {
        return seznam.size();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public Iterator<DopravniProstredek> iterator() {
        return seznam.iterator();
    }

}
