package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import prostredky.*;

import java.util.function.Function;

public class EditorGUI implements Function<DopravniProstredek, DopravniProstredek> {

    private Dialog<DopravniProstredek> dopravniProstredekDialog;
    private DopravniProstredek dopravniProstredek;
    private GridPane gridPane;
    private ComboBox<String> cbProstredkuType;
    private ComboBox<String> cbBarva;
    private ComboBox<String> cbDodavkyType;
    private ComboBox<String> cbSpolcenost;
    private TextField tfSPZ;
    private TextField tfHmotnost;
    private TextField tfVykon;
    private TextField tfTah;
    private TextField tfNosnost;
    private Spinner<Integer> pocetSedadelSpinner;
    private Spinner<Integer> pocetNapravSpinner;

    @Override
    public DopravniProstredek apply(DopravniProstredek dopravniProstredekApply) {
        dopravniProstredek = dopravniProstredekApply;
        dopravniProstredekDialog = new Dialog<>();
        dopravniProstredekDialog.setTitle("Editace aktuálního prostředku");
        dopravniProstredekDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        gridPane = new GridPane();
        gridPane.minHeight(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 125, 10, 10));
        gridType();
        editace();

        dopravniProstredekDialog.getDialogPane().setContent(gridPane);
        dopravniProstredekDialog.showAndWait();
        return dopravniProstredek;
    }

    public EditorGUI() {

    }

    private void gridType() {
        switch (dopravniProstredek.getType()) {
            case DODAVKA:
                gridZakladni();
                gridDodavka();
                break;
            case TRAKTOR:
                gridZakladni();
                gridTraktor();
                break;
            case OSOBNI_AUTOMOBIL:
                gridZakladni();
                gridOsobnik();
                break;
            case NAKLADNI_AUTMOBIL:
                gridZakladni();
                gridNakladak();
                break;
        }
    }

    private void gridZakladni() {
        tfSPZ = new TextField(dopravniProstredek.getSpz());
        gridPane.add(new Label("SPZ:"), 0, 1);
        gridPane.add(tfSPZ, 1, 1);
        tfHmotnost = new TextField(String.valueOf(dopravniProstredek.getHmotnost()));
        gridPane.add(new Label("Hmotnost:"), 0, 2);
        gridPane.add(tfHmotnost, 1, 2);
    }

    private void gridTraktor() {
        Traktor traktor = (Traktor) dopravniProstredek;
        tfTah = new TextField(String.valueOf(traktor.getTah()));
        gridPane.add(new Label("Tah:"), 0, 3);
        gridPane.add(tfTah, 1, 3);

        tfVykon = new TextField(String.valueOf(traktor.getVykon()));
        gridPane.add(new Label("Výkon:"), 0, 4);
        gridPane.add(tfVykon, 1, 4);

        cbSpolcenost = new ComboBox<>();
        for (int i = 0; i < Spolecnost.values().length; i++) {
            cbSpolcenost.getItems().add(Spolecnost.values()[i].getNazev());
        }
        cbSpolcenost.getSelectionModel().select(traktor.getSpolecnost().getNazev());
        gridPane.add(new Label("Společnost:"), 0, 5);
        gridPane.add(cbSpolcenost, 1, 5);

    }

    private void gridDodavka() {
        Dodavka dodavka = (Dodavka) dopravniProstredek;
        cbDodavkyType = new ComboBox<>();
        for (int i = 0; i < DodavkaTyp.values().length; i++) {
            cbDodavkyType.getItems().add(DodavkaTyp.values()[i].getNazev());
        }
        cbDodavkyType.getSelectionModel().select(dodavka.getTypeD().getNazev());
        gridPane.add(new Label("Typ dodávky: "), 0, 3);
        gridPane.add(cbDodavkyType, 1, 3);
    }

    private void gridOsobnik() {
        OsobniAutomobil osobniAutomobil = (OsobniAutomobil) dopravniProstredek;
        cbBarva = new ComboBox<>();
        for (int i = 0; i < Barva.values().length; i++) {
            cbBarva.getItems().add(Barva.values()[i].getBarva());
        }
        cbBarva.getSelectionModel().select(osobniAutomobil.getBarva().getBarva());
        gridPane.add(new Label("Barva:"), 0, 3);
        gridPane.add(cbBarva, 1, 3);

        pocetSedadelSpinner = new Spinner<>();
        SpinnerValueFactory spinnerValueFactorySedadel = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, osobniAutomobil.getPocetSedadel());
        pocetSedadelSpinner.setValueFactory(spinnerValueFactorySedadel);
        gridPane.add(new Label("Počet sedadel:"), 0, 4);
        gridPane.add(pocetSedadelSpinner, 1, 4);

    }

    private void gridNakladak() {
        NakladniAutomobil nakladniAutomobil = (NakladniAutomobil) dopravniProstredek;
        pocetNapravSpinner = new Spinner<>();
        SpinnerValueFactory svcPocet = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, nakladniAutomobil.getPocetNaprav());
        pocetNapravSpinner.setValueFactory(svcPocet);
        gridPane.add(new Label("Počet náprav:"), 0, 3);
        gridPane.add(pocetNapravSpinner, 1, 3);

        tfNosnost = new TextField(String.valueOf(nakladniAutomobil.getNosnost()));
        gridPane.add(new Label("Nosnost:"), 0, 4);
        gridPane.add(tfNosnost, 1, 4);

        cbSpolcenost = new ComboBox<>();
        for (int i = 0; i < Spolecnost.values().length; i++) {
            cbSpolcenost.getItems().add(Spolecnost.values()[i].getNazev());
        }
        cbSpolcenost.getSelectionModel().select(nakladniAutomobil.getSpolecnost().getNazev());
        gridPane.add(new Label("Společnost:"), 0, 5);
        gridPane.add(cbSpolcenost, 1, 5);
    }

    private void editace() {
        dopravniProstredekDialog.setResultConverter(lambda -> {
            if (lambda == ButtonType.OK) {
                String spz = tfSPZ.getText();
                float hmotnost = Float.parseFloat(tfHmotnost.getText());
                switch (dopravniProstredek.getType()) {
                    case DODAVKA:
                        DodavkaTyp dodavkaTyp = DodavkaTyp.list(cbDodavkyType.getValue());
                        Dodavka dodavka = (Dodavka) dopravniProstredek;
                        dodavka.setSpz(spz);
                        dodavka.setHmotnost(hmotnost);
                        dodavka.setTypeD(dodavkaTyp);
                        break;
                    case TRAKTOR:
                        int tah = Integer.parseInt(tfTah.getText());
                        int vykon = Integer.parseInt(tfVykon.getText());
                        Spolecnost spolecnostTR = Spolecnost.list(cbSpolcenost.getValue());
                        Traktor traktor = (Traktor) dopravniProstredek;
                        traktor.setSpz(spz);
                        traktor.setHmotnost(hmotnost);
                        traktor.setTah(tah);
                        traktor.setVykon(vykon);
                        traktor.setSpolecnost(spolecnostTR);
                        break;
                    case OSOBNI_AUTOMOBIL:
                        Barva barva = Barva.list(cbBarva.getValue());
                        int pocetSedadelValue = pocetSedadelSpinner.getValue();
                        OsobniAutomobil osobniAutomobil = (OsobniAutomobil) dopravniProstredek;
                        osobniAutomobil.setSpz(spz);
                        osobniAutomobil.setHmotnost(hmotnost);
                        osobniAutomobil.setBarva(barva);
                        osobniAutomobil.setPocetSedadel(pocetSedadelValue);
                        break;
                    case NAKLADNI_AUTMOBIL:
                        int pocetNaprav = pocetNapravSpinner.getValue();
                        float nosnost = Float.parseFloat(tfNosnost.getText());
                        Spolecnost spolecnostNA = Spolecnost.list(cbSpolcenost.getValue());
                        NakladniAutomobil nakladniAutomobil = (NakladniAutomobil) dopravniProstredek;
                        nakladniAutomobil.setSpz(spz);
                        nakladniAutomobil.setHmotnost(hmotnost);
                        nakladniAutomobil.setPocetNaprav(pocetNaprav);
                        nakladniAutomobil.setNosnost(nosnost);
                        nakladniAutomobil.setSpolecnost(spolecnostNA);
                        break;
                }

                return dopravniProstredek;
            } else {
                return null;
            }
        });
    }

}
