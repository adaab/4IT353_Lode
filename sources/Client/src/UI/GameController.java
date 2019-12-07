package UI;

import Logic.App;
import Logic.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import logic.GameField;

import javax.swing.text.html.ImageView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static logic.GameField.FieldState.ship;

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
    public Button source;

    private App app;
    private ArrayList<GameField> gameField;

    @Override
    public void update() {
    }

    public void inicializuj(App app){
        this.app = app;
        app.register(this);
        update();
    }

    public void prepareNewGame(){
        viewField.setVisible(false);
        boatSelect.setVisible(true);
        loggedUser.setText(app.player);
        opponentLabel.setVisible(false);
        playerLabel.setText("Lodě");
        instructions.setText("Kliknutím na políčko v herním poli zvolíte část lodi. Povolené varianty lodí vidíte v pravém menu.");
        this.gameField = app.getPlayerField();
    }

    public void handleFieldSelect(javafx.event.ActionEvent actionEvent) {
        source = (Button) actionEvent.getSource();
        source.setStyle("-fx-background-color: #216164");
        String id = source.getId();
        for(GameField field : gameField) {
            if (id.equals(field.getPosition())) {
                field.setState(ship);
            }
        }
    }
}
