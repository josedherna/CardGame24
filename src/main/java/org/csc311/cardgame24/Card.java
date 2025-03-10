package org.csc311.cardgame24;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

public class Card {
    private final Image cardImage;
    private final int cardValue;
    private String imagePath;

    Card(String imageName, int value) {
        URL imageURl = getClass().getResource("card_images/" + imageName);
        if (imageURl != null) {
            cardImage = new Image(imageURl.toExternalForm());
            imagePath = imageURl.toExternalForm();
        }
        else {
            System.out.println("Image not found");
            System.out.println(imageName);
            cardImage = null;
        }

        cardValue = value;
    }

    @Override
    public boolean equals(Object obj) {
        //Cards are equal if the path and the value are the same.
        if (obj instanceof Card card) {
            return this.imagePath.equals(card.imagePath) && this.cardValue == card.cardValue;
        }
        return false;
    }

    @Override
    public int hashCode() {
        //Hashes the cards based on card image path and card value.
        return Objects.hash(imagePath, cardValue);
    }

    public int getCardValue() {
        return cardValue;
    }

    public Image getCardImage() {
        return cardImage;
    }
}
