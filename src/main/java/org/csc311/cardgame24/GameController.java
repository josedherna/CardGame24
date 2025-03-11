package org.csc311.cardgame24;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    @FXML
    private Label scoreLabel;

    private final HashSet<Card> CARD_SET = new HashSet<>();
    ArrayList<Card> cardImagesToDisplay = new ArrayList<>();
    ArrayList<Integer> cardValues = new ArrayList<>();
    private final CardDeck cardDeck = new CardDeck();
    private int score = 0;

    @FXML
    private void initialize() {
        shuffleCards();
        scoreLabel.setText("Score: " + score);
    }

    @FXML
    public void shuffleCards() {
        CARD_SET.clear();
        promptLabel.setText("");
        cardImagesToDisplay.clear();
        while (CARD_SET.size() != 4) {
            CARD_SET.add(cardDeck.pickRandomCard());
        }
        cardImagesToDisplay.addAll(CARD_SET);
        cardImageView1.setImage(cardImagesToDisplay.get(0).getCardImage());
        cardImageView2.setImage(cardImagesToDisplay.get(1).getCardImage());
        cardImageView3.setImage(cardImagesToDisplay.get(2).getCardImage());
        cardImageView4.setImage(cardImagesToDisplay.get(3).getCardImage());
    }

    @FXML
    public void validateExpression() {
        ArrayList<Integer> parsedExpressionNumbers = new ArrayList<>();

        for (Card card : CARD_SET) {
            cardValues.add(card.getCardValue());
        }

        promptLabel.setText("");
        StringBuilder currentNumber = new StringBuilder();
        String expression = expressionTextField.getText();

        if (expression.isEmpty()) {
            promptLabel.setText("Please don't leave the text field blank!");
            cardValues.clear();
            return;
        }
        //Gets numeric values from expression.
        for (int i = 0; i < expression.length(); i++) {
            //Checks if the char is a digit, if it is then concatenate to currentNumber;
            if (Character.isDigit(expression.charAt(i))) {
                currentNumber.append(expression.charAt(i));
            }
            else {
                if (!currentNumber.isEmpty()) {
                    try {
                        //Adds currentNumber to parsedExpressionNumbers array list as an int.
                        parsedExpressionNumbers.add(Integer.parseInt(currentNumber.toString()));
                    } catch (NumberFormatException e) {
                        promptLabel.setText("Please enter a valid expression!");
                        cardValues.clear();
                        return;
                    }
                    //currentNumber is now empty so more digits can be added.
                    currentNumber = new StringBuilder();
                }
            }
        }
        //Adds last char if it is a number and if currentNumber contains a value.
        if (!currentNumber.isEmpty()) {
            try {
                parsedExpressionNumbers.add(Integer.parseInt(currentNumber.toString()));
            } catch (NumberFormatException e) {
                promptLabel.setText("Please enter a valid expression!");
                cardValues.clear();
                return;
            }
            currentNumber = new StringBuilder();
        }

        //If the sizes between the expression and card set are different, then player entered an incorrect expression.
        if (parsedExpressionNumbers.size() != CARD_SET.size()) {
            promptLabel.setText("Please use the four numbers from each card ONLY once!");
            cardValues.clear();
            return;
        }

        //Sorts parsedExpressionNumbers and cardValues so that they can be determined if they have the same card values.
        Collections.sort(parsedExpressionNumbers);
        Collections.sort(cardValues);

        if (parsedExpressionNumbers.equals(cardValues)) {
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            double result = evaluator.evaluate(expression);
            //Player wins round if their expression evaluates to 24
            if (result == 24.0) {
                score += 10;
                scoreLabel.setText("Score: " + score);
                shuffleCards();
                cardValues.clear();
                expressionTextField.clear();
            }
        }
        else {
            promptLabel.setText("Please ONLY use the numbers from the cards!");
            cardValues.clear();
        }
    }
}