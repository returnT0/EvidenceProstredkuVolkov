package gui;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import Perzistence.Mapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kolekce.KolekceException;
import kolekce.LinSeznam;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekKlic;
import prostredky.ProstredekTyp;
import sprava.SpravaProstredku;

public class GUI extends Application {

    private static SpravaProstredku spravaProstredku;
    private static final Comparator<DopravniProstredek> SPZcompare
            = (o1, o2) -> o1.getSpz().compareTo(o2.getSpz());
    private static final BorderPane borderPane = new BorderPane();
    private static final VBox vBox = new VBox();
    private static final HBox hBox = new HBox();
    private static ListView<DopravniProstredek> dps = new ListView<>();

    private static void errorLog(String errorLog) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR: zkuste neco jine!!");
        alert.setContentText(errorLog);
        alert.showAndWait();
    }

    private static void informace(String informace) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informace");
        alert.setHeaderText("Nová zpráva od Evidence dopravních prostředků (Volkov)");
        alert.setContentText(informace);
        alert.show();
    }

    private static void ListView() {
        dps = new ListView<>();
        borderPane.setCenter(dps);
    }

    private static void filtrace(ComboBox<String> comboBox) {
        comboBox.setPromptText("Filtr");
        for (int i = 0; i < ProstredekTyp.values().length; i++) {
            comboBox.getItems().addAll(ProstredekTyp.values()[i].getNazev());

        }
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String value = comboBox.getValue();
            switch (value) {
                case "filtrace":
                    dps.getItems().clear();
                    spravaProstredku.vypis(prostredek -> dps.getItems().addAll(prostredek));
                case "dodávka":
                    dps.getItems().clear();
                    spravaProstredku.vypis(prostredek -> {
                        if (prostredek.getType() == ProstredekTyp.DODAVKA) {
                            dps.getItems().addAll(prostredek);
                        }
                    });
                    break;
                case "traktor":
                    dps.getItems().clear();
                    spravaProstredku.vypis(prostredek -> {
                        if (prostredek.getType() == ProstredekTyp.TRAKTOR) {
                            dps.getItems().addAll(prostredek);
                        }
                    });
                    break;
                case "osobní automobil":
                    dps.getItems().clear();
                    spravaProstredku.vypis(prostredek -> {
                        if (prostredek.getType() == ProstredekTyp.OSOBNI_AUTOMOBIL) {
                            dps.getItems().addAll(prostredek);
                        }
                    });
                    break;
                case "nákladní automobil":
                    dps.getItems().clear();
                    spravaProstredku.vypis(prostredek -> {
                        if (prostredek.getType() == ProstredekTyp.NAKLADNI_AUTMOBIL) {
                            dps.getItems().addAll(prostredek);
                        }
                    });
                    break;
            }
        });
    }

    private static void generace() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("GENEROVÁNÍ");
        textInputDialog.setHeaderText("Počet prostředků:");
        Optional<String> pocet = textInputDialog.showAndWait();
        if (pocet.isPresent()) {
            try {
                int pocetDP = Integer.parseInt(textInputDialog.getEditor().getText());
                dps.getItems().clear();
                spravaProstredku.generujData(pocetDP);
                spravaProstredku.vypis(prostredek -> dps.getItems().addAll(prostredek));

            } catch (NumberFormatException e) {
                errorLog("Mělo by být číslo!!!");
            }
        }
    }

    private static void VBox() {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(15));

        Button btnPrvni = btnPrvniOnAction();
        Button btnDalsi = btnDalsiOnAction();
        Button btnPredchozi = btnPredchoziOnAction();
        Button btnPosledni = btnPosledniOnAction();
        Button btnClear = btnClearOnAction();
        Button btnUloz = btnUlozOnAction();
        Button btnNacti = btnNactiOnAction();
        Button btnZalohuj = btnZalohujOnAction();
        Button btnObnov = btnObnovOnAction();

        vBox.getChildren().addAll(btnPrvni, btnDalsi, btnPredchozi, btnPosledni, btnUloz, btnNacti, btnZalohuj, btnObnov,
                btnClear);
        borderPane.setRight(vBox);

    }

    private static Button btnPrvniOnAction(){
        final Button btnPrvni = new Button("První");
        btnPrvni.setOnAction(lambda -> {
            spravaProstredku.prejdiNaPrvniPolozku();
            dps.getSelectionModel().selectFirst();
            informace("Aktualni prvek je nastaven jako prvni!");
        });
        return btnPrvni;
    }

    private static Button btnDalsiOnAction() {
        final Button btnDalsi = new Button("Další");
        btnDalsi.setOnAction(lambda -> {
            spravaProstredku.prejdiNaDalsiPolozku();
            try {
                dps.getSelectionModel().select(spravaProstredku.dejAktualni());
            } catch (KolekceException e) {
                errorLog(e.getMessage());
            }
        });
        return btnDalsi;
    }
    private static Button btnPredchoziOnAction() {
        final Button btnPredchozi = new Button("Předchozí");
        btnPredchozi.setOnAction(lambda -> {
            spravaProstredku.prejdiNaPredchoziPolozku();
            try {
                dps.getSelectionModel().select(spravaProstredku.dejAktualni());
            } catch (KolekceException e) {
                errorLog(e.getMessage());
            }
        });
        return btnPredchozi;
    }

    private static Button btnPosledniOnAction() {
        final Button btnPosledni = new Button("Poslední");
        btnPosledni.setOnAction(lambda -> {
            spravaProstredku.prejdiNaPosledniPolozku();
            dps.getSelectionModel().selectLast();
            informace("Aktualni prvek je nastaven jako posledni!");
        });
        return btnPosledni;
    }

    private static Button btnClearOnAction() {
        final Button btnClear = new Button("Clear");
        btnClear.setOnAction(lambda -> {
            dps.getItems().clear();
        });
        return btnClear;
    }

    private static Button btnUlozOnAction() {
        final Button btnUloz = new Button("Ulož");
        btnUloz.setOnAction(lambda -> {
            spravaProstredku.ulozTextSoubor("file.txt", Mapper.mapperTo);
        });
        return btnUloz;
    }
    private static Button btnNactiOnAction() {
        final Button btnNacti = new Button("Načti");
        btnNacti.setOnAction(lambda -> {
            dps.getItems().clear();
            spravaProstredku.nactiTextSoubor("file.txt", Mapper.mapperOut);
            spravaProstredku.vypis(prostredek -> dps.getItems().add(prostredek));
        });
        return btnNacti;
    }

    private static Button btnZalohujOnAction() {
        final Button btnZalohuj = new Button("Zálohuj");
        btnZalohuj.setOnAction(lambda -> {
            spravaProstredku.ulozDoSouboru("file.bin");
        });
        return btnZalohuj;
    }

    private static Button btnObnovOnAction() {
        final Button btnObnov = new Button("Obnov");
        btnObnov.setOnAction(lambda -> {
            dps.getItems().clear();
            spravaProstredku.nactiZeSouboru("file.bin");
        });
        return btnObnov;
    }

    private static void HBox() {
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15));

        Button btnNajdi = btnNajdiOnAction();
        Button btnEdituj = btnEditujOnAction();
        Button btnZobraz = btnZobrazOnAction();
        Button btnNovy = btnNovyOnAction();
        Button btnVyjmi = btnVyjmiOnAction();
        Button btnGeneruj = btnGenerujOnAction();

        ComboBox<String> comboBoxFiltrace = new ComboBox<>();
        filtrace(comboBoxFiltrace);

        hBox.getChildren().addAll(btnNajdi, btnEdituj, btnNovy, btnVyjmi, btnGeneruj, comboBoxFiltrace, btnZobraz);
        borderPane.setBottom(hBox);
    }

    private static Button btnNajdiOnAction(){
        final Button btnNajdi = new Button("Najdi");
        btnNajdi.setOnAction(lambda -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setTitle("SPZ");
            textInputDialog.setHeaderText("Napište SPZ:");
            Optional<String> nachodka = textInputDialog.showAndWait();
            try {
                if (nachodka.isPresent()) {
                    String SPZ = textInputDialog.getEditor().getText().trim();
                    System.out.println(SPZ);
                    DopravniProstredek dopravniProstredek = spravaProstredku.najdiPolozku(new DopravniProstredekKlic(SPZ));
                    informace("" + dopravniProstredek);

                    if (dopravniProstredek != null) {
                        dps.getSelectionModel().select(dopravniProstredek);
                    }
                }

            } catch (Exception e) {
                errorLog("Neexistující SPZ!!!");
            }
        });
        return btnNajdi;
    }

    private static Button btnEditujOnAction() {
        final Button btnEdituj = new Button("Edituj");
        btnEdituj.setOnAction(lambda -> {
            Function<DopravniProstredek, DopravniProstredek> editor
                    = new EditorGUI();
            spravaProstredku.editujAktualniPolozku(editor);
            dps.getItems().clear();
            spravaProstredku.vypis(prostredek -> dps.getItems().add(prostredek));
        });
        return btnEdituj;
    }

    private static Button btnZobrazOnAction(){
        final Button btnZobraz = new Button("Zobraz");
        btnZobraz.setOnAction(lambda -> {
            dps.getItems().clear();
            spravaProstredku.stream().forEach(dps.getItems()::add);
        });
        return btnZobraz;
    }

    private static Button btnNovyOnAction() {
        final Button btnNovy = new Button("Nový");
        btnNovy.setOnAction(lambda -> {
            NovyGUI novyGUI = new NovyGUI();
            DopravniProstredek prostredek = novyGUI.vypis();
            if (prostredek != null) {
                spravaProstredku.vlozPolozku(prostredek);
                dps.getItems().add(prostredek);
            }
        });
        return btnNovy;
    }

    private static Button btnVyjmiOnAction() {
        final Button btnVyjmi = new Button("Výjmi");
        btnVyjmi.setOnAction(lambda -> {
            spravaProstredku.vyjmiAktualniPolozku();
            dps.getItems().clear();
            spravaProstredku.stream().forEach(dps.getItems()::add);
        });
        return btnVyjmi;
    }

    private static Button btnGenerujOnAction() {
        final Button btnGeneruj = new Button("Generuj");
        btnGeneruj.setOnAction(lambda -> {
            generace();
        });
        return btnGeneruj;
    }


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(borderPane, 800, 400);
        vBox.getChildren().addAll();
        hBox.getChildren().addAll();
        borderPane.setCenter(dps);
        borderPane.setRight(vBox);
        borderPane.setBottom(hBox);
        VBox();
        HBox();
        ListView();

        spravaProstredku = SpravaProstredku.vytvorSpravce(errorLog -> errorLog(errorLog),
                LinSeznam<DopravniProstredek>::new);
        spravaProstredku.nastavKomparator(SPZcompare);
        spravaProstredku.vytvorSeznam(LinSeznam::new);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Evidence dopravních prostředků (Volkov)");
        primaryStage.show();
    }
}