package org.csc311.cardgame24;

public class CardDeck {
    private final Card[] deck;

    CardDeck() {
        deck = new Card[52];
        createDeck();
    }

    private void createDeck() {
        String[] face = new String[] {"ace", "jack", "king", "queen"};
        int faceIndex = 0;
        int index = 0;
        int value = 2;

        while (index < 36) {
            //Value is put at the beginning since each png starts with a number.
            deck[index] = new Card(value + "_of_clubs.png", value);
            index++;
            deck[index] = new Card(value + "_of_diamonds.png", value);
            index++;
            deck[index] = new Card(value + "_of_hearts.png", value);
            index++;
            deck[index] = new Card(value + "_of_spades.png", value);
            index++;

            value++;
        }

        //The value of the first card with a face (ace).
        value = 1;

        while (index < 52) {
            //Reads from face array to get card images from png files that don't start with a number.
            deck[index] = new Card(face[faceIndex] + "_of_clubs.png", value);
            index++;
            deck[index] = new Card(face[faceIndex] + "_of_diamonds.png", value);
            index++;
            deck[index] = new Card(face[faceIndex] + "_of_hearts.png", value);
            index++;
            deck[index] = new Card(face[faceIndex] + "_of_spades.png", value);
            index++;

            faceIndex++;
            //The rest of the faces have the value of 11 and more.
            if (faceIndex == 1) {
                value = 11;
            }
            else {
                value++;
            }
        }
    }

    public Card pickRandomCard() {
        int index = (int) (Math.random() * 52);
        return deck[index];
    }
}
