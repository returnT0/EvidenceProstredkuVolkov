package Perzistence;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

import kolekce.LinSeznam;
import kolekce.Seznam;

public final class Perzistence {

    private Perzistence() {
    }

    public static <E> void uloz(String jmenoSouboru, Seznam<E> seznam)
            throws IOException {
        Objects.requireNonNull(seznam);
        ObjectOutputStream vystup;
        vystup = new ObjectOutputStream(
                new FileOutputStream(jmenoSouboru));
        vystup.writeInt(seznam.size());
        Iterator<E> it = seznam.iterator();
        while (it.hasNext()) {
            vystup.writeObject(it.next());
        }

    }

    public static <E> LinSeznam<E> nacti(String jmenoSouboru, Seznam<E> seznam)
            throws IOException {
        try {
            Objects.requireNonNull(seznam);
            try (ObjectInputStream vstup
                         = new ObjectInputStream(
                    new FileInputStream(jmenoSouboru))) {
                seznam.zrus();
                int pocet = vstup.readInt();
                for (int i = 0; i < pocet; i++) {
                    seznam.vlozNaKonec((E) vstup.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return (LinSeznam<E>) seznam;
    }
}