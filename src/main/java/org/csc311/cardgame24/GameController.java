package org.csc311.cardgame24;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashSet;

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

    private final HashSet<Card> cardSet = new HashSet<>();
    ArrayList<Card> cardImagesToDisplay = new ArrayList<>();
    private final CardDeck cardDeck = new CardDeck();

    @FXML
    private void initialize() {
        shuffleCards();
    }

    @FXML
    public void shuffleCards() {
        cardSet.clear();
        cardImagesToDisplay.clear();
        while (cardSet.size() != 4) {
            cardSet.add(cardDeck.pickRandomCard());
        }
        cardImagesToDisplay.addAll(cardSet);
        cardImageView1.setImage(cardImagesToDisplay.get(0).getCardImage());
        cardImageView2.setImage(cardImagesToDisplay.get(1).getCardImage());
        cardImageView3.setImage(cardImagesToDisplay.get(2).getCardImage());
        cardImageView4.setImage(cardImagesToDisplay.get(3).getCardImage());
    }
}