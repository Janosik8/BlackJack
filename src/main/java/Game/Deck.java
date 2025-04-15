package Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
    String[] types = {"D", "C", "H", "S"};
    ArrayList<Card> deck = new ArrayList<Card>();

    Deck(){
        for (String value : this.values) {
            for (String type : types) {
                Card card = new Card(value, type);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public Card getSome() {
        if(!deck.isEmpty()) {
            return deck.removeLast();
        }
        return null;
    }

}
