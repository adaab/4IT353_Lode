package UI;

import Logic.App;
import Logic.Client;
import Logic.Observer;
import javafx.application.HostServices;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import logic.Game;
import logic.GameField;
import logic.Ship;
import comm.ClientDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController implements Observer {

    @FXML
    public GridPane viewField;
    @FXML
    public GridPane mainField;
    @FXML
    public GridPane boatSelect;
    @FXML
    public Label opponentLabel;
    @FXML
    public Label playerLabel;
    @FXML
    public Label loggedUser;
    @FXML
    public Label instructions;
    @FXML
    public Button A0;
    @FXML
    public Button A1;
    @FXML
    public Button A2;
    @FXML
    public Button A3;
    @FXML
    public Button A4;
    @FXML
    public Button A5;
    @FXML
    public Button A6;
    @FXML
    public Button A7;
    @FXML
    public Button A8;
    @FXML
    public Button A9;
    @FXML
    public Button A10;
    @FXML
    public Button A11;
    @FXML
    public Button A12;
    @FXML
    public Button B0;
    @FXML
    public Button B1;
    @FXML
    public Button B2;
    @FXML
    public Button B3;
    @FXML
    public Button B4;
    @FXML
    public Button B5;
    @FXML
    public Button B6;
    @FXML
    public Button B7;
    @FXML
    public Button B8;
    @FXML
    public Button B9;
    @FXML
    public Button B10;
    @FXML
    public Button B11;
    @FXML
    public Button B12;
    @FXML
    public Button C0;
    @FXML
    public Button C1;
    @FXML
    public Button C2;
    @FXML
    public Button C3;
    @FXML
    public Button C4;
    @FXML
    public Button C5;
    @FXML
    public Button C6;
    @FXML
    public Button C7;
    @FXML
    public Button C8;
    @FXML
    public Button C9;
    @FXML
    public Button C10;
    @FXML
    public Button C11;
    @FXML
    public Button C12;
    @FXML
    public Button D0;
    @FXML
    public Button D1;
    @FXML
    public Button D2;
    @FXML
    public Button D3;
    @FXML
    public Button D4;
    @FXML
    public Button D5;
    @FXML
    public Button D6;
    @FXML
    public Button D7;
    @FXML
    public Button D8;
    @FXML
    public Button D9;
    @FXML
    public Button D10;
    @FXML
    public Button D11;
    @FXML
    public Button D12;
    @FXML
    public Button E0;
    @FXML
    public Button E1;
    @FXML
    public Button E2;
    @FXML
    public Button E3;
    @FXML
    public Button E4;
    @FXML
    public Button E5;
    @FXML
    public Button E6;
    @FXML
    public Button E7;
    @FXML
    public Button E8;
    @FXML
    public Button E9;
    @FXML
    public Button E10;
    @FXML
    public Button E11;
    @FXML
    public Button E12;
    @FXML
    public Button F0;
    @FXML
    public Button F1;
    @FXML
    public Button F2;
    @FXML
    public Button F3;
    @FXML
    public Button F4;
    @FXML
    public Button F5;
    @FXML
    public Button F6;
    @FXML
    public Button F7;
    @FXML
    public Button F8;
    @FXML
    public Button F9;
    @FXML
    public Button F10;
    @FXML
    public Button F11;
    @FXML
    public Button F12;
    @FXML
    public Button G0;
    @FXML
    public Button G1;
    @FXML
    public Button G2;
    @FXML
    public Button G3;
    @FXML
    public Button G4;
    @FXML
    public Button G5;
    @FXML
    public Button G6;
    @FXML
    public Button G7;
    @FXML
    public Button G8;
    @FXML
    public Button G9;
    @FXML
    public Button G10;
    @FXML
    public Button G11;
    @FXML
    public Button G12;
    @FXML
    public Button H0;
    @FXML
    public Button H1;
    @FXML
    public Button H2;
    @FXML
    public Button H3;
    @FXML
    public Button H4;
    @FXML
    public Button H5;
    @FXML
    public Button H6;
    @FXML
    public Button H7;
    @FXML
    public Button H8;
    @FXML
    public Button H9;
    @FXML
    public Button H10;
    @FXML
    public Button H11;
    @FXML
    public Button H12;
    @FXML
    public Button I0;
    @FXML
    public Button I1;
    @FXML
    public Button I2;
    @FXML
    public Button I3;
    @FXML
    public Button I4;
    @FXML
    public Button I5;
    @FXML
    public Button I6;
    @FXML
    public Button I7;
    @FXML
    public Button I8;
    @FXML
    public Button I9;
    @FXML
    public Button I10;
    @FXML
    public Button I11;
    @FXML
    public Button I12;
    @FXML
    public Button J0;
    @FXML
    public Button J1;
    @FXML
    public Button J2;
    @FXML
    public Button J3;
    @FXML
    public Button J4;
    @FXML
    public Button J5;
    @FXML
    public Button J6;
    @FXML
    public Button J7;
    @FXML
    public Button J8;
    @FXML
    public Button J9;
    @FXML
    public Button J10;
    @FXML
    public Button J11;
    @FXML
    public Button J12;
    @FXML
    public Button K0;
    @FXML
    public Button K1;
    @FXML
    public Button K2;
    @FXML
    public Button K3;
    @FXML
    public Button K4;
    @FXML
    public Button K5;
    @FXML
    public Button K6;
    @FXML
    public Button K7;
    @FXML
    public Button K8;
    @FXML
    public Button K9;
    @FXML
    public Button K10;
    @FXML
    public Button K11;
    @FXML
    public Button K12;
    @FXML
    public Button L0;
    @FXML
    public Button L1;
    @FXML
    public Button L2;
    @FXML
    public Button L3;
    @FXML
    public Button L4;
    @FXML
    public Button L5;
    @FXML
    public Button L6;
    @FXML
    public Button L7;
    @FXML
    public Button L8;
    @FXML
    public Button L9;
    @FXML
    public Button L10;
    @FXML
    public Button L11;
    @FXML
    public Button L12;
    @FXML
    public Button M0;
    @FXML
    public Button M1;
    @FXML
    public Button M2;
    @FXML
    public Button M3;
    @FXML
    public Button M4;
    @FXML
    public Button M5;
    @FXML
    public Button M6;
    @FXML
    public Button M7;
    @FXML
    public Button M8;
    @FXML
    public Button M9;
    @FXML
    public Button M10;
    @FXML
    public Button M11;
    @FXML
    public Button M12;
    @FXML
    public Button N0;
    @FXML
    public Button N1;
    @FXML
    public Button N2;
    @FXML
    public Button N3;
    @FXML
    public Button N4;
    @FXML
    public Button N5;
    @FXML
    public Button N6;
    @FXML
    public Button N7;
    @FXML
    public Button N8;
    @FXML
    public Button N9;
    @FXML
    public Button N10;
    @FXML
    public Button N11;
    @FXML
    public Button N12;
    @FXML
    public Button O0;
    @FXML
    public Button O1;
    @FXML
    public Button O2;
    @FXML
    public Button O3;
    @FXML
    public Button O4;
    @FXML
    public Button O5;
    @FXML
    public Button O6;
    @FXML
    public Button O7;
    @FXML
    public Button O8;
    @FXML
    public Button O9;
    @FXML
    public Button O10;
    @FXML
    public Button O11;
    @FXML
    public Button O12;
    @FXML
    public Button P0;
    @FXML
    public Button P1;
    @FXML
    public Button P2;
    @FXML
    public Button P3;
    @FXML
    public Button P4;
    @FXML
    public Button P5;
    @FXML
    public Button P6;
    @FXML
    public Button P7;
    @FXML
    public Button P8;
    @FXML
    public Button P9;
    @FXML
    public Button P10;
    @FXML
    public Button P11;
    @FXML
    public Button P12;
    @FXML
    public Button xA0;
    @FXML
    public Button xA1;
    @FXML
    public Button xA2;
    @FXML
    public Button xA3;
    @FXML
    public Button xA4;
    @FXML
    public Button xA5;
    @FXML
    public Button xA6;
    @FXML
    public Button xA7;
    @FXML
    public Button xA8;
    @FXML
    public Button xA9;
    @FXML
    public Button xA10;
    @FXML
    public Button xA11;
    @FXML
    public Button xA12;
    @FXML
    public Button xB0;
    @FXML
    public Button xB1;
    @FXML
    public Button xB2;
    @FXML
    public Button xB3;
    @FXML
    public Button xB4;
    @FXML
    public Button xB5;
    @FXML
    public Button xB6;
    @FXML
    public Button xB7;
    @FXML
    public Button xB8;
    @FXML
    public Button xB9;
    @FXML
    public Button xB10;
    @FXML
    public Button xB11;
    @FXML
    public Button xB12;
    @FXML
    public Button xC0;
    @FXML
    public Button xC1;
    @FXML
    public Button xC2;
    @FXML
    public Button xC3;
    @FXML
    public Button xC4;
    @FXML
    public Button xC5;
    @FXML
    public Button xC6;
    @FXML
    public Button xC7;
    @FXML
    public Button xC8;
    @FXML
    public Button xC9;
    @FXML
    public Button xC10;
    @FXML
    public Button xC11;
    @FXML
    public Button xC12;
    @FXML
    public Button xD0;
    @FXML
    public Button xD1;
    @FXML
    public Button xD2;
    @FXML
    public Button xD3;
    @FXML
    public Button xD4;
    @FXML
    public Button xD5;
    @FXML
    public Button xD6;
    @FXML
    public Button xD7;
    @FXML
    public Button xD8;
    @FXML
    public Button xD9;
    @FXML
    public Button xD10;
    @FXML
    public Button xD11;
    @FXML
    public Button xD12;
    @FXML
    public Button xE0;
    @FXML
    public Button xE1;
    @FXML
    public Button xE2;
    @FXML
    public Button xE3;
    @FXML
    public Button xE4;
    @FXML
    public Button xE5;
    @FXML
    public Button xE6;
    @FXML
    public Button xE7;
    @FXML
    public Button xE8;
    @FXML
    public Button xE9;
    @FXML
    public Button xE10;
    @FXML
    public Button xE11;
    @FXML
    public Button xE12;
    @FXML
    public Button xF0;
    @FXML
    public Button xF1;
    @FXML
    public Button xF2;
    @FXML
    public Button xF3;
    @FXML
    public Button xF4;
    @FXML
    public Button xF5;
    @FXML
    public Button xF6;
    @FXML
    public Button xF7;
    @FXML
    public Button xF8;
    @FXML
    public Button xF9;
    @FXML
    public Button xF10;
    @FXML
    public Button xF11;
    @FXML
    public Button xF12;
    @FXML
    public Button xG0;
    @FXML
    public Button xG1;
    @FXML
    public Button xG2;
    @FXML
    public Button xG3;
    @FXML
    public Button xG4;
    @FXML
    public Button xG5;
    @FXML
    public Button xG6;
    @FXML
    public Button xG7;
    @FXML
    public Button xG8;
    @FXML
    public Button xG9;
    @FXML
    public Button xG10;
    @FXML
    public Button xG11;
    @FXML
    public Button xG12;
    @FXML
    public Button xH0;
    @FXML
    public Button xH1;
    @FXML
    public Button xH2;
    @FXML
    public Button xH3;
    @FXML
    public Button xH4;
    @FXML
    public Button xH5;
    @FXML
    public Button xH6;
    @FXML
    public Button xH7;
    @FXML
    public Button xH8;
    @FXML
    public Button xH9;
    @FXML
    public Button xH10;
    @FXML
    public Button xH11;
    @FXML
    public Button xH12;
    @FXML
    public Button xI0;
    @FXML
    public Button xI1;
    @FXML
    public Button xI2;
    @FXML
    public Button xI3;
    @FXML
    public Button xI4;
    @FXML
    public Button xI5;
    @FXML
    public Button xI6;
    @FXML
    public Button xI7;
    @FXML
    public Button xI8;
    @FXML
    public Button xI9;
    @FXML
    public Button xI10;
    @FXML
    public Button xI11;
    @FXML
    public Button xI12;
    @FXML
    public Button xJ0;
    @FXML
    public Button xJ1;
    @FXML
    public Button xJ2;
    @FXML
    public Button xJ3;
    @FXML
    public Button xJ4;
    @FXML
    public Button xJ5;
    @FXML
    public Button xJ6;
    @FXML
    public Button xJ7;
    @FXML
    public Button xJ8;
    @FXML
    public Button xJ9;
    @FXML
    public Button xJ10;
    @FXML
    public Button xJ11;
    @FXML
    public Button xJ12;
    @FXML
    public Button xK0;
    @FXML
    public Button xK1;
    @FXML
    public Button xK2;
    @FXML
    public Button xK3;
    @FXML
    public Button xK4;
    @FXML
    public Button xK5;
    @FXML
    public Button xK6;
    @FXML
    public Button xK7;
    @FXML
    public Button xK8;
    @FXML
    public Button xK9;
    @FXML
    public Button xK10;
    @FXML
    public Button xK11;
    @FXML
    public Button xK12;
    @FXML
    public Button xL0;
    @FXML
    public Button xL1;
    @FXML
    public Button xL2;
    @FXML
    public Button xL3;
    @FXML
    public Button xL4;
    @FXML
    public Button xL5;
    @FXML
    public Button xL6;
    @FXML
    public Button xL7;
    @FXML
    public Button xL8;
    @FXML
    public Button xL9;
    @FXML
    public Button xL10;
    @FXML
    public Button xL11;
    @FXML
    public Button xL12;
    @FXML
    public Button xM0;
    @FXML
    public Button xM1;
    @FXML
    public Button xM2;
    @FXML
    public Button xM3;
    @FXML
    public Button xM4;
    @FXML
    public Button xM5;
    @FXML
    public Button xM6;
    @FXML
    public Button xM7;
    @FXML
    public Button xM8;
    @FXML
    public Button xM9;
    @FXML
    public Button xM10;
    @FXML
    public Button xM11;
    @FXML
    public Button xM12;
    @FXML
    public Button xN0;
    @FXML
    public Button xN1;
    @FXML
    public Button xN2;
    @FXML
    public Button xN3;
    @FXML
    public Button xN4;
    @FXML
    public Button xN5;
    @FXML
    public Button xN6;
    @FXML
    public Button xN7;
    @FXML
    public Button xN8;
    @FXML
    public Button xN9;
    @FXML
    public Button xN10;
    @FXML
    public Button xN11;
    @FXML
    public Button xN12;
    @FXML
    public Button xO0;
    @FXML
    public Button xO1;
    @FXML
    public Button xO2;
    @FXML
    public Button xO3;
    @FXML
    public Button xO4;
    @FXML
    public Button xO5;
    @FXML
    public Button xO6;
    @FXML
    public Button xO7;
    @FXML
    public Button xO8;
    @FXML
    public Button xO9;
    @FXML
    public Button xO10;
    @FXML
    public Button xO11;
    @FXML
    public Button xO12;
    @FXML
    public Button xP0;
    @FXML
    public Button xP1;
    @FXML
    public Button xP2;
    @FXML
    public Button xP3;
    @FXML
    public Button xP4;
    @FXML
    public Button xP5;
    @FXML
    public Button xP6;
    @FXML
    public Button xP7;
    @FXML
    public Button xP8;
    @FXML
    public Button xP9;
    @FXML
    public Button xP10;
    @FXML
    public Button xP11;
    @FXML
    public Button xP12;
    @FXML
    public Button source;
    @FXML
    public ImageView boat1;
    @FXML
    public ImageView boat2;
    @FXML
    public ImageView boat3;
    @FXML
    public ImageView boat4;
    @FXML
    public ImageView boat5;
    @FXML
    public ImageView boat6;
    @FXML
    public ImageView boat7;
    @FXML
    public Pane contentLoaderPane;
    @FXML
    public ProgressIndicator contentLoader;
    @FXML
    public Label score;

    private App app;
    public List<logic.Ship> ships;
    public Integer shipCount = 0;
    private HashSet<logic.GameField> positions1;
    public Ship ship1 = new Ship("ship1", 3, true, positions1 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions2;
    public Ship ship2 = new Ship("ship2", 3, true, positions2 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions3;
    public Ship ship3 = new Ship("ship3", 5, true, positions3 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions4;
    public Ship ship4 = new Ship("ship4", 4, true, positions4 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions5;
    public Ship ship5 = new Ship("ship5", 4, true, positions5 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions6;
    public Ship ship6 = new Ship("ship6", 4, true, positions6 = new HashSet<logic.GameField>());
    private HashSet<logic.GameField> positions7;
    public Ship ship7 = new Ship("ship7", 4, true, positions7 = new HashSet<logic.GameField>());
    public ArrayList<Button> buttonsWithShips;

    public ArrayList<Button> viewFieldButtons;
    public ArrayList<Button> mainFieldButtons;
    public Ship currentShip;

    /**
     *  Třída GameController - controller obsluhující obrazovku
     *  na které se odehrává hra (volba lodí a poté samotná hra)
     *
     *
     *@author     Ada
     *@version    1.0
     *@created    prosinec 2019
     */

    @Override
    public void update() {
    }

    /**
     * metoda napáruje a zaregistuje Aplikaci k game controlleru
     *
     * @author Ada
     * @param app
     */
    public void inicializuj(App app) {
        this.app = app;
        app.register(this);
        update();
        System.out.println("INIT");
    }

    /**
     * metoda připraví obrazovku k spuštění nové hry - k počátečnímu zadání lodí
     *
     * @author Ada
     */
    public void prepareNewGame() {
        viewField.setVisible(false);
        boatSelect.setVisible(true);
        loggedUser.setText(app.player);
        opponentLabel.setVisible(false);
        score.setVisible(false);
        playerLabel.setText("Lodě");
        instructions.setText("Vaším protivníkem bude "+app.opponentId+".\n"+"Kliknutím na políčko v herním poli zvolíte část lodi. Povolené varianty lodí vidíte v pravém menu.");
        boat2.setOpacity(0.3);
        boat3.setOpacity(0.3);
        boat4.setOpacity(0.3);
        boat5.setOpacity(0.3);
        boat6.setOpacity(0.3);
        boat7.setOpacity(0.3);
        ships = new ArrayList<logic.Ship>();
        buttonsWithShips = new ArrayList<Button>();
        mainFieldButtons = (ArrayList<Button>) Stream.of(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, H0, H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, J0, J1, J2, J3, J4, J5, J6, J7, J8, J9, J10, J11, J12, K0, K1, K2, K3, K4, K5, K6, K7, K8, K9, K10, K11, K12, L0, L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, M0, M1, M2, M3, M4, M5, M6, M7, M8, M9, M10, M11, M12, N0, N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, N11, N12, O0, O1, O2, O3, O4, O5, O6, O7, O8, O9, O10, O11, O12, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12).collect(Collectors.toList());
        viewFieldButtons = (ArrayList<Button>) Stream.of(xA0, xA1, xA2, xA3, xA4, xA5, xA6, xA7, xA8, xA9, xA10, xA11, xA12, xB0, xB1, xB2, xB3, xB4, xB5, xB6, xB7, xB8, xB9, xB10, xB11, xB12, xC0, xC1, xC2, xC3, xC4, xC5, xC6, xC7, xC8, xC9, xC10, xC11, xC12, xD0, xD1, xD2, xD3, xD4, xD5, xD6, xD7, xD8, xD9, xD10, xD11, xD12, xE0, xE1, xE2, xE3, xE4, xE5, xE6, xE7, xE8, xE9, xE10, xE11, xE12, xF0, xF1, xF2, xF3, xF4, xF5, xF6, xF7, xF8, xF9, xF10, xF11, xF12, xG0, xG1, xG2, xG3, xG4, xG5, xG6, xG7, xG8, xG9, xG10, xG11, xG12, xH0, xH1, xH2, xH3, xH4, xH5, xH6, xH7, xH8, xH9, xH10, xH11, xH12, xI0, xI1, xI2, xI3, xI4, xI5, xI6, xI7, xI8, xI9, xI10, xI11, xI12, xJ0, xJ1, xJ2, xJ3, xJ4, xJ5, xJ6, xJ7, xJ8, xJ9, xJ10, xJ11, xJ12, xK0, xK1, xK2, xK3, xK4, xK5, xK6, xK7, xK8, xK9, xK10, xK11, xK12, xL0, xL1, xL2, xL3, xL4, xL5, xL6, xL7, xL8, xL9, xL10, xL11, xL12, xM0, xM1, xM2, xM3, xM4, xM5, xM6, xM7, xM8, xM9, xM10, xM11, xM12, xN0, xN1, xN2, xN3, xN4, xN5, xN6, xN7, xN8, xN9, xN10, xN11, xN12, xO0, xO1, xO2, xO3, xO4, xO5, xO6, xO7, xO8, xO9, xO10, xO11, xO12, xP0, xP1, xP2, xP3, xP4, xP5, xP6, xP7, xP8, xP9, xP10, xP11, xP12).collect(Collectors.toList());
    }

    /**
     * metoda obsluhuje výběr hracího pole - pokud jsme ve fázi zadávání lodí rozhoduje, zda přidáváme nové pole nebo rušíme již staré,
     * ve fázi hry poté odesílá výstřel na zvolené pole
     *
     * @param actionEvent
     * @author Ada
     */
    public void handleFieldSelect(javafx.event.ActionEvent actionEvent) {
        score.setVisible(false);
        source = (Button) actionEvent.getSource();
        String id = source.getId();

        if(app.gameState.equals(Game.GameState.NEW)){
            if(buttonsWithShips.contains(source)){
                buttonsWithShips.remove(source);
                shipCount--;
                source.setStyle("-fx-background-color: white");
                currentShip.getPositions().removeIf((field) -> field.getPosition().equals(id));
            } else {
                handleFieldSelectNewField(id);
            }
        } else if (app.gameState.equals(Game.GameState.PLAYING)) {
        source.setStyle("-fx-background-color: #d4cdcd");
        sendShot(id);
        }
    }

    /**
     * metoda obsluhuje výběr hracího pole v případě zadávání lodí - podle aktuálního stavu hracího pole přidává k odpovídající lodi,
     * popř. nastavuje aktuálně volenou loď
     *
     * @author Ada
     * @param id
     */
    public void handleFieldSelectNewField(String id){
        if (app.gameState.equals(Game.GameState.NEW)) {
            source.setStyle("-fx-background-color: #216164");
            buttonsWithShips.add(source);
            shipCount++;
            if (shipCount <= ship1.getSize()) {
                currentShip = ship1;
                ship1.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == ship1.getSize()) {
                    if (validateShip(ship1)) {
                        ships.add(ship1);
                        boat1.setImage(new Image("/icons/tetris (1)_done.png"));
                        boat2.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize())) {
                currentShip = ship2;
                ship2.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize())) {
                    if (validateShip(ship2)) {
                        ships.add(ship2);
                        boat2.setImage(new Image("/icons/tetris (1)_done.png"));
                        boat3.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize() + ship3.getSize())) {
                currentShip = ship3;
                ship3.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize() + ship3.getSize())) {
                    if (validateShip(ship3)) {
                        ships.add(ship3);
                        boat3.setImage(new Image("/icons/tetris (4)_done.png"));
                        boat4.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize())) {
                currentShip = ship4;
                ship4.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize())) {
                    if (validateShip(ship4)) {
                        ships.add(ship4);
                        boat4.setImage(new Image("/icons/tetris_done.png"));
                        boat5.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize())) {
                currentShip = ship5;
                ship5.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize())) {
                    if (validateShip(ship5)) {
                        ships.add(ship5);
                        boat5.setImage(new Image("/icons/tetris_done.png"));
                        boat6.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize() + ship6.getSize())) {
                currentShip = ship6;
                ship6.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize() + ship6.getSize())) {
                    if (validateShip(ship6)) {
                        ships.add(ship6);
                        boat6.setImage(new Image("/icons/tetris (2)_done.png"));
                        boat7.setOpacity(1);
                        buttonsWithShips.forEach((btn) -> btn.setDisable(true));
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            } else if (shipCount <= (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize() + ship6.getSize() + ship7.getSize())) {
                currentShip = ship7;
                ship7.getPositions().add(new logic.GameField(id.substring(0, 1), id.substring(1), GameField.FieldState.ship));
                if (shipCount == (ship1.getSize() + ship2.getSize() + ship3.getSize() + ship4.getSize() + ship5.getSize() + ship6.getSize() + ship7.getSize())) {
                    if (validateShip(ship7)) {
                        ships.add(ship7);
                        boat7.setImage(new Image("/icons/tetris (3)_done.png"));
                        shipsFinished();
                        buttonsWithShips.forEach((btn) -> btn.disableProperty());
                    } else {
                        score.setVisible(true);
                        score.setText("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole");
                    }
                }
            }
        }
    }

    /**
     * metoda volaná poté, co jsou všechny lodě úspěšně zvoleny - tvoří nové dto s daty o lodích,
     * odesílá na server a disabluje hrací pole
     *
     * @author Ada
     */

    public void shipsFinished() {
        contentLoaderPane.setVisible(true);
        contentLoader.setVisible(true);

        ClientDto newGame = new ClientDto();
        newGame.gameId = app.gameId;
        newGame.id = app.player;
        newGame.playerReadyToPlay = true;
        newGame.playerShips = ships;
        try {
            app.getServer().send(newGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonsWithShips.forEach((btn) -> btn.setDisable(false));
        buttonsWithShips.forEach((btn) -> btn.setStyle("-fx-background-color: white"));
    }

    /**
     * metoda upravuje obrazovku na formulář pro probíhající hru
     *
     * @author Ada
     */
    public void setPlayingScene() {
        boatSelect.setVisible(false);
        viewField.setVisible(true);
        viewField.setDisable(true);
        opponentLabel.setVisible(true);
        playerLabel.setText("Tvoje lodě");
        opponentLabel.setText("Lodě protivníka");
        score.setVisible(true);
    }

    /**
     * metoda spouštěná pokaždé po příchodu aktualizace hry ze serveru - nastavuje pole
     * dle aktuálního stavu, aktualizuje body apod.
     *
     * @author Ada
     */
    public void updateGame() {
        //VIEW FIELD UPDATES
        for (GameField field : app.getPlayerField()) {
            if (field.getFieldState().equals(GameField.FieldState.ship)) {
                for (Button btn : viewFieldButtons) {
                    if (btn.getId().substring(1).equals(field.getPosition())) {
                        btn.setStyle("-fx-background-color: #216164");
                    }
                }
            } else if (field.getFieldState().equals(GameField.FieldState.shipHit)) {
                for (Button btn : viewFieldButtons) {
                    if (btn.getId().substring(1).equals(field.getPosition())) {
                        btn.setStyle("-fx-background-color: #9E4751");
                    }
                }
            } else if (field.getFieldState().equals(GameField.FieldState.missed)) {
                for (Button btn : viewFieldButtons) {
                    if (btn.getId().substring(1).equals(field.getPosition())) {
                        btn.setStyle("-fx-background-color: #968d8d");
                    }
                }

            }
        }

        //MAIN FIELD UPDATES
        for (GameField field : app.getOpponentField()) {
            if (field.getFieldState().equals(GameField.FieldState.missed)) {
                for (Button btn : mainFieldButtons) {
                    if (btn.getId().equals(field.getPosition())) {
                        btn.setStyle("-fx-background-color: #968d8d");
                        break;
                    }
                }
            } else if (field.getFieldState().equals(GameField.FieldState.shipHit)) {
                for (Button btn : mainFieldButtons) {
                    if (btn.getId().equals(field.getPosition())) {
                        btn.setStyle("-fx-background-color: #216164");
                        break;
                    }
                }
            }
        }

        //POINTS UPDATE
        score.setText("SKÓRE: "+app.player+": "+app.playerPoints+", "+app.opponentId+": "+app.opponentPoints+"\n");
        if(app.isMyTurn){
            instructions.setText("Jste na tahu. Zvolte v hracím poli pole, do kterého chcete vystřelit.");
            mainField.setDisable(false);
        } else {
            instructions.setText("Hraje protivník, prosím počkejte");
            mainField.setDisable(true);
        }

        contentLoaderPane.setVisible(false);
    }

    /**
     * metoda obsluhuje odeslání výstřelu hráče při hraní hry
     *
     * @param id
     
     */
    public void sendShot(String id){
        ClientDto shot = new ClientDto();
        shot.gameId = app.gameId;
        shot.id = app.player;
        shot.shotX = id.substring(0,1);
        shot.shotY = id.substring(1);
        try {
            app.getServer().send(shot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentLoaderPane.setVisible(true);
    }

    /**
     * metoda validuje tvar zadané lodi - zda odpovídá té lodi,
     * kterou měl právě uživatel zvolit
     *
     * metoda používá pro kontrolu matematický součin souřadnic jednotlivých polí a porovnává ho
     * s součinem souřadnic vzoru
     *
     * @author Ada
     * @param ship
     * @return boolean - je loď korektní a může být uložena?
     */
    public boolean validateShip(Ship ship) {
        Boolean isOkX = false;
        Boolean isOkY = false;
        String[] lettersHelp = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
        ArrayList<String> letters = new ArrayList<String>();
        Arrays.stream(lettersHelp).forEachOrdered(letters::add);
        ArrayList<GameField> positions = new ArrayList<>(ship.getPositions());

        //3 fields default for all ships
        String x1 = positions.get(0).getX();
        String x2 = positions.get(1).getX();
        String x3 = positions.get(2).getX();

        Integer x1Pos = letters.indexOf(x1);
        Integer x2Pos = letters.indexOf(x2);
        Integer x3Pos = letters.indexOf(x3);

        Integer y1 = Integer.parseInt(positions.get(0).getY());
        Integer y2 = Integer.parseInt(positions.get(1).getY());
        Integer y3 = Integer.parseInt(positions.get(2).getY());

        //ship1 validation
        if (ship.getName().equals("ship1") || ship.getName().equals("ship2")) {
            Integer[] x = {x1Pos,x2Pos,x3Pos};
            Integer[] y = {y1,y2,y3};
            Arrays.sort(x);
            Arrays.sort(y);

            isOkX = (x[0]*x[1]*x[2] == x[0]*(x[0]+1)*(x[0]+2)) && (y[0]*y[1]*y[2] ==  Math.round(Math.pow(y[0],3)));
            isOkY = (y[0]*y[1]*y[2] == y[0]*(y[0]+1)*(y[0]+2)) && (x[0]*x[1]*x[2] == Math.round(Math.pow(x[0],3)));
        } else if(ship.getName().equals("ship3") || ship.getName().equals("ship4") || ship.getName().equals("ship5") || ship.getName().equals("ship6") || ship.getName().equals("ship7")){
            //4th field default only for ship3,4,5,6,7
            String x4 = positions.get(3).getX();
            Integer x4Pos = letters.indexOf(x4);
            Integer y4 = Integer.parseInt(positions.get(3).getY());

            if(ship.getName().equals("ship3")) {
                //5th field default only for ship3
                String x5 = positions.get(4).getX();
                Integer x5Pos = letters.indexOf(x5);
                Integer y5 = Integer.parseInt(positions.get(4).getY());
                Integer[] x = {x1Pos, x2Pos, x3Pos, x4Pos, x5Pos};
                Integer[] y = {y1, y2, y3, y4, y5};
                Arrays.sort(x);
                Arrays.sort(y);

                isOkX = (x[0]*x[1]*x[2]*x[3]*x[4] == x[0]*(x[0]+1)*(x[0]+2)*(x[0]+3)*(x[0]+4)) && (y[0]*y[1]*y[2]*y[3]*y[4] == Math.round(Math.pow(y[0],5)));
                isOkY = (y[0]*y[1]*y[2]*y[3]*y[4] == y[0]*(y[0]+1)*(y[0]+2)*(y[0]+3)*(y[0]+4)) && (x[0]*x[1]*x[2]*x[3]*x[4] == Math.round(Math.pow(x[0],5)));
            } else {
                Integer[] x = {x1Pos, x2Pos, x3Pos, x4Pos};
                Integer[] y = {y1, y2, y3, y4};
                Arrays.sort(x);
                Arrays.sort(y);

                if(ship.getName().equals("ship4") || ship.getName().equals("ship5")){
                    isOkX = (x[0]*x[1]*x[2]*x[3]==x[0]*(x[0]+1)*(x[0]+2)*(x[0]+1)) && ((y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0]+1,3)*(y[0]))) || (y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0],3)*(y[0]+1))));
                    isOkY = (y[0]*y[1]*y[2]*y[3]==y[0]*(y[0]+1)*(y[0]+2)*(y[0]+1)) && ((x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0]+1,3)*(x[0]))) || (x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0],3)*(x[0]+1))));
                } else if(ship.getName().equals("ship6")){
                    isOkX = ((x[0]*x[1]*x[2]*x[3]==x[0]*(x[0]+1)*(x[0]+2)*(x[0]) || (x[0]*x[1]*x[2]*x[3]==x[0]*(x[0]+1)*(x[0]+2)*(x[0]+2)) && ((y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0],3)*(y[0]+1))) || (y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0],3)*(y[0]-1))))));
                    isOkY = ((y[0]*y[1]*y[2]*y[3]==y[0]*(y[0]+1)*(y[0]+2)*(y[0]) || (y[0]*y[1]*y[2]*y[3]==y[0]*(y[0]+1)*(y[0]+2)*(y[0]+2)) && ((x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0],3)*(x[0]+1))) || (x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0],3)*(x[0]-1))))));
                } else if(ship.getName().equals("ship7")){
                    isOkX = (x[0]*x[1]*x[2]*x[3]==x[0]*(x[0]+1)*(x[0]+2)*(x[0]+1)) && ((y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0],2)*(Math.round(Math.pow((y[0]+1),2)))) || (y[0]*y[1]*y[2]*y[3] == Math.round(Math.pow(y[0],2)*(Math.round(Math.pow((y[0]-1),2)))))));
                    isOkY = (y[0]*y[1]*y[2]*y[3]==y[0]*(y[0]+1)*(y[0]+2)*(y[0]+1)) && ((x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0],2)*(Math.round(Math.pow((x[0]+1),2)))) || (x[0]*x[1]*x[2]*x[3] == Math.round(Math.pow(x[0],2)*(Math.round(Math.pow((x[0]-1),2)))))));
                }
            }
        }
        boolean isOk = isOkX || isOkY;
        return isOk;
    }

    public void handleError() {
        instructions.setText("Unexpected error: "+app.error.descr);
    }

    public void showHelp(){
        if (Desktop.isDesktopSupported()) {
            try {
                URL url = getClass().getResource("/userGuide.pdf");
                File myFile = new File(url.toURI());
                Desktop.getDesktop().open(myFile);
            } catch (IOException | URISyntaxException ex) {
                // no application registered for PDFs
            }
        }
    }
}