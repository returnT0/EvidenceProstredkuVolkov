package kolekce;

import prostredky.DopravniProstredek;

import java.util.Iterator;
import java.util.Objects;

public class LinSeznam<E> implements Seznam<E>, Cloneable {

    private class Prvek<E> {

        private E value;
        private Prvek<E> dalsi;

        private Prvek(E value) {
            this.value = value;
        }
    }

    private int pocet;
    private Prvek<E> aktualni, prvni, posledni;

    @Override
    public void nastavPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdný!");
        }
        aktualni = prvni;
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazndý!");
        }
        aktualni = posledni;
    }

    @Override
    public boolean dalsi() throws KolekceException {
        if (Objects.isNull(aktualni)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }
        if (Objects.isNull(aktualni.dalsi)) {
            return false;

        } else {
            aktualni = aktualni.dalsi;
            return true;
        }
    }

    @Override
    public void vlozPrvni(E value) {
        Prvek<E> novy = new Prvek<>(Objects.requireNonNull(value));

        novy.dalsi = prvni;

        if (jePrazdny()) {
            posledni = novy;
        }
        prvni = novy;
        pocet++;
    }

    @Override
    public void vlozNaKonec(E value) {
        Prvek<E> novy = new Prvek<>(Objects.requireNonNull(value));
        novy.dalsi = null;

        if (jePrazdny()) {
            prvni = novy;
            posledni = prvni;
        } else {
            posledni.dalsi = novy;
            posledni = novy;
        }
        pocet++;
    }

    @Override
    public void vlozZaAktualni(E value) throws KolekceException {
        Objects.requireNonNull(value);
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdný!");
        }
        if (Objects.isNull(aktualni)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }

        Prvek<E> novy = new Prvek<>(Objects.requireNonNull(value));
        novy.dalsi = aktualni.dalsi;
        aktualni.dalsi = novy;

        if (Objects.isNull(novy.dalsi)) {
            posledni = novy;
        }
        pocet++;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazndý!");
        }
        return prvni.value;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazndý!");
        }
        return posledni.value;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        if (Objects.isNull(aktualni)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }
        return aktualni.value;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if (Objects.isNull(aktualni)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }
        return aktualni.dalsi.value;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazndý!");
        }
        if (Objects.isNull(aktualni)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }
        Prvek<E> predchozi;
        Prvek<E> zruseny = aktualni;
        for (predchozi = prvni;
                predchozi != aktualni && predchozi.dalsi != aktualni;
                predchozi = predchozi.dalsi) {
        }
        if (aktualni == posledni) {
            predchozi.dalsi = zruseny.dalsi;
            posledni = predchozi;
        } else if (aktualni == prvni) {
            prvni = zruseny.dalsi;
            aktualni = prvni;
        } else {
            predchozi.dalsi = zruseny.dalsi;
            aktualni = predchozi;
        }
        pocet--;
        return zruseny.value;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazndý!");
        }
        if (Objects.isNull(aktualni.dalsi)) {
            throw new KolekceException("Není nastaven aktualní prvek!");
        }
        Prvek<E> zruseny = aktualni.dalsi;
        aktualni.dalsi = aktualni.dalsi.dalsi;
        pocet--;
        return zruseny.value;
    }

    @Override
    public int size() {
        return pocet;
    }

    @Override
    public void zrus() {
        aktualni = prvni = posledni = null;
        pocet = 0;
        DopravniProstredek.setCounter(0);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    // FIXME Odstraňte přetypování. V dobré implementaci nejsou potřeba.
    //(st64534)kód vyvolá chybu, když odstraním přetypování.
    private class MyIterator<E> implements Iterator<E> {

        private Prvek<E> next;
        private Prvek<E> lastReturned;
        private Prvek<E> prev;

        MyIterator() {
            next = (Prvek<E>) prvni;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (next == null) {
                throw new NullPointerException();
            }
            lastReturned = next;
            next = next.dalsi;
            if (Objects.isNull(prev)) {
                prev = (Prvek<E>) prvni;
            } else {
                prev = prev.dalsi;
            }
            return lastReturned.value;
        }
    }
}
