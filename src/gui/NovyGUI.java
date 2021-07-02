package gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import prostredky.*;

import java.util.Arrays;

public class NovyGUI {

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


    public NovyGUI() {
        dopravniProstredekDialog = new Dialog<>();
        dopravniProstredekDialog.setTitle("Vytvoření nového prostředku");
        dopravniProstredekDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        gridPane = new GridPane();
        gridPane.minHeight(300);
        gridPane.setHgap(20);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 70, 20, 10));
        gridPane.isResizable();
        prostredekTypGUI();
        gridZakladni();
        gridOsobnik();
        gridType();
        dejProstredek();

        dopravniProstredekDialog.getDialogPane().setContent(gridPane);
        dopravniProstredekDialog.showAndWait();
    }

    private void prostredekTypGUI(){
        cbProstredkuType = new ComboBox<>();
//        (FXCollections.observableArrayList(Arrays.asList(ProstredekTyp.getProstredky())));
        for (int i = 0; i < ProstredekTyp.values().length; i++) {
            cbProstredkuType.getItems().add(ProstredekTyp.values()[i].getNazev());
        }
        cbProstredkuType.getSelectionModel().selectFirst();
    }

    private void clear() {
        gridPane.getChildren().clear();
    }



    private void gridType() {
        cbProstredkuType.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (cbProstredkuType.getValue()) {
                case "dodávka":
                    clear();
                    gridZakladni();
                    gridDodavka();
                    dopravniProstredekDialog.getDialogPane().setContent(gridPane);
                    break;
                case "traktor":
                    clear();
                    gridZakladni();
                    gridTraktor();
                    dopravniProstredekDialog.getDialogPane().setContent(gridPane);
                    break;
                case "osobní automobil":
                    clear();
                    gridZakladni();
                    gridOsobnik();
                    dopravniProstredekDialog.getDialogPane().setContent(gridPane);
                    break;
                case "nákladní automobil":
                    clear();
                    gridZakladni();
                    gridNakladak();
                    dopravniProstredekDialog.getDialogPane().setContent(gridPane);
                    break;
            }
        });
    }

    private void gridZakladni() {
        gridPane.add(new Label("Typ prostředku:"),0,0);
        gridPane.add(cbProstredkuType, 1,0);
        tfSPZ = new TextField("AA0000");
        gridPane.add(new Label("SPZ:"), 0, 1);
        gridPane.add(tfSPZ, 1, 1);

        tfHmotnost = new TextField("0.0");
        gridPane.add(new Label("Hmotnost:"), 0, 2);
        gridPane.add(tfHmotnost, 1, 2);
    }

    private void gridTraktor() {
        tfTah = new TextField("0");
        gridPane.add(new Label("Tah:"), 0, 3);
        gridPane.add(tfTah, 1, 3);

        tfVykon = new TextField("0");
        gridPane.add(new Label("Výkon:"), 0, 4);
        gridPane.add(tfVykon, 1, 4);

        cbSpolcenost = new ComboBox<>();
        for (int i = 0; i < Spolecnost.values().length; i++) {
            cbSpolcenost.getItems().add(Spolecnost.values()[i].getNazev());
        }
        cbSpolcenost.getSelectionModel().selectFirst();
        gridPane.add(new Label("Společnost:"), 0, 5);
        gridPane.add(cbSpolcenost, 1, 5);

    }

    private void gridDodavka() {
        cbDodavkyType = new ComboBox<>();
        for (int i = 0; i < DodavkaTyp.values().length; i++) {
            cbDodavkyType.getItems().add(DodavkaTyp.values()[i].getNazev());
        }
        cbDodavkyType.getSelectionModel().selectFirst();
        gridPane.add(new Label("Typ dodávky: "), 0, 3);
        gridPane.add(cbDodavkyType, 1, 3);
    }

    private void gridOsobnik() {
        cbBarva = new ComboBox<>();
        for (int i = 0; i < Barva.values().length; i++) {
            cbBarva.getItems().add(Barva.values()[i].getBarva());
        }
        cbBarva.getSelectionModel().selectFirst();
        gridPane.add(new Label("Barva:"), 0, 3);
        gridPane.add(cbBarva, 1, 3);

        pocetSedadelSpinner = new Spinner<>();
        SpinnerValueFactory spinnerValueFactorySedadel = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);
        pocetSedadelSpinner.setValueFactory(spinnerValueFactorySedadel);
        gridPane.add(new Label("Počet sedadel:"), 0, 4);
        gridPane.add(pocetSedadelSpinner, 1, 4);

    }

    private void gridNakladak() {
        pocetNapravSpinner = new Spinner<>();
        SpinnerValueFactory svcPocet = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        pocetNapravSpinner.setValueFactory(svcPocet);
        gridPane.add(new Label("Počet náprav:"), 0, 3);
        gridPane.add(pocetNapravSpinner, 1, 3);

        tfNosnost = new TextField("0");
        gridPane.add(new Label("Nosnost:"), 0, 4);
        gridPane.add(tfNosnost, 1, 4);

        cbSpolcenost = new ComboBox<>();
        for (int i = 0; i < Spolecnost.values().length; i++) {
            cbSpolcenost.getItems().add(Spolecnost.values()[i].getNazev());
        }
        cbSpolcenost.getSelectionModel().selectFirst();
        gridPane.add(new Label("Společnost:"), 0, 5);
        gridPane.add(cbSpolcenost, 1, 5);
    }

    private void dejProstredek() {
        dopravniProstredekDialog.setResultConverter(lambda -> {
            if (lambda == ButtonType.OK) {
                String spz = tfSPZ.getText();
                float hmotnost = Float.parseFloat(tfHmotnost.getText());
                ProstredekTyp prostredekTyp = ProstredekTyp.list(cbProstredkuType.getValue());
                switch (prostredekTyp) {
                    case DODAVKA:
                        DodavkaTyp dodavkaTyp = DodavkaTyp.list(cbDodavkyType.getValue());
                        return dopravniProstredek = new Dodavka(spz, hmotnost, dodavkaTyp);
                    case TRAKTOR:
                        int tah = 0;
                        int vykon = 0;
                        Spolecnost spolecnostTR = Spolecnost.list(cbSpolcenost.getValue());
                        return dopravniProstredek = new Traktor(spz, hmotnost, tah, vykon, spolecnostTR);
                    case OSOBNI_AUTOMOBIL:
                        Barva barva = Barva.list(cbBarva.getValue());
                        int pocetSedadelValue = pocetSedadelSpinner.getValue();
                        return dopravniProstredek = new OsobniAutomobil(spz, hmotnost, barva, pocetSedadelValue);
                    case NAKLADNI_AUTMOBIL:
                        int pocetNaprav = pocetNapravSpinner.getValue();
                        float nosnost = Float.parseFloat(tfNosnost.getText());
                        Spolecnost spolecnostNA = Spolecnost.list(cbSpolcenost.getValue());
                        return dopravniProstredek = new NakladniAutomobil(spz, hmotnost, pocetNaprav, nosnost, spolecnostNA);

                }

                return dopravniProstredek;
            } else {
                return null;
            }
        });
    }

    public DopravniProstredek vypis() {
        return dopravniProstredek;
    }

}
