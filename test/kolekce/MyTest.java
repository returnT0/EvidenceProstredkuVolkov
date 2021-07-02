package kolekce;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyTest {

    private static class NewTest {
        private int to;

        public NewTest(int to) {
            this.to = to;
        }

        @Override
        protected NewTest clone() throws CloneNotSupportedException {
            return (NewTest) super.clone();
        }
    }

    private NewTest TO1 = new NewTest(1);
    private NewTest TO2 = new NewTest(2);

    public MyTest() {
    }
    
    @Test
    public void jePrazdny() {
        LinSeznam<NewTest> a = new LinSeznam<>();
        assertTrue(a.jePrazdny());
    }

    @Test
    public void zrus() {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO2);
        a.zrus();
        assertEquals(0, a.size());
    }

    @Test
    public void vlozPrvni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.vlozPrvni(TO2);
        NewTest answer = a.dejPrvni();
        assertEquals(TO2, answer);
    }

    @Test
    public void vlozZaAktualni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.nastavPrvni();
        a.vlozZaAktualni(TO2);
        NewTest answer = a.dejPosledni();
        assertEquals(TO2, answer);
    }

    @Test
    public void vlozNaKonec() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO1);
        a.vlozNaKonec(TO2);
        NewTest answer = a.dejPosledni();
        assertEquals(TO2, answer);
    }

    @Test
    public void odeberAktualni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO2);
        a.vlozNaKonec(TO1);
        a.nastavPrvni();
        NewTest answer = a.odeberAktualni();
        assertEquals(TO2, answer);
    }

    @Test
    public void odeberZaAktualnim() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO2);
        a.vlozNaKonec(TO1);
        a.nastavPrvni();
        NewTest answer = a.odeberZaAktualnim();
        assertEquals(TO1, answer);
    }

    @Test
    public void dalsi() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.vlozPrvni(TO2);
        a.nastavPrvni();
        a.dalsi();
        NewTest answer = a.dejAktualni();
        assertEquals(TO1, answer);
    }

    @Test
    public void nastavPrvni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.nastavPrvni();
        NewTest answer = a.dejPrvni();
        assertEquals(TO1, answer);
    }
// этот не копируй, он, вроде, с ошибкой 
    @Test
    public void nastavPosledni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.vlozNaKonec(TO2);
        a.nastavPosledni();
        NewTest answer = a.dejPosledni();
        assertEquals(TO2, answer);
    }

    @Test
    public void dejPrvni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozPrvni(TO1);
        a.vlozPrvni(TO2);
        NewTest answer = a.dejPrvni();
        assertEquals(TO2, answer);
    }

    @Test
    public void dejPosledni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO1);
        a.vlozNaKonec(TO2);
        NewTest answer = a.dejPosledni();
        assertEquals(TO2, answer);
    }

    @Test
    public void dejAktualni() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO2);
        a.vlozNaKonec(TO1);
        a.nastavPrvni();
        NewTest answer = a.dejAktualni();
        assertEquals(TO2, answer);
    }

    @Test
    public void dejZaAktualnim() throws KolekceException {
        LinSeznam<NewTest> a = new LinSeznam<>();
        a.vlozNaKonec(TO2);
        a.vlozNaKonec(TO1);
        a.nastavPrvni();
        NewTest answer = a.dejZaAktualnim();
        assertEquals(TO1, answer);
    }
    
}
