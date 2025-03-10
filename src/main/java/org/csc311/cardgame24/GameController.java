package org.csc311.cardgame24;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameController {
    @FXML
    private Button hintButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button verifyButton;
    @FXML
    private Label promptLabel;
    @FXML
    private ImageView cardImageView1;
    @FXML
    private ImageView cardImageView2;
    @FXML
    private ImageView cardImageView3;
    @FXML
    private ImageView cardImageView4;
    @FXML
    private TextField expressionTextField;
    @FXML
    private HBox cardHbox;
}