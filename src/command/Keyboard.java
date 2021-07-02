package command;

import java.util.InputMismatchException;
import java.util.Scanner;
import prostredky.Barva;

public class Keyboard {

    private static Scanner keyboard = new Scanner(System.in);

    private Keyboard() {

    }

    public static String getStringItem(String vyzva) {
        System.out.println(vyzva);
        return keyboard.nextLine().trim();
    }

    public static Integer getIntItem(String vyzva) {
        System.out.println(vyzva);
        return keyboard.nextInt();
    }

    public static Float getFloatItem(String vyzva) {
        System.out.println(vyzva);
        return keyboard.nextFloat();
    }

    public static Barva getBarvaItem(String vyzva, Barva barva) {
        boolean opakuj = false;
        do {
            if (barva != null) {
                vyzva = vyzva + "(" + barva.name() + "):";
            }
            String barvaText = getStringItem(vyzva);
            if (barvaText.length() == 0) {
                return barva;
            }
            if (barva == null) {
                opakuj = true;
                System.out.println("Není barva, zadej znovu");
            }
        } while (opakuj);
        return barva;
    }

}
