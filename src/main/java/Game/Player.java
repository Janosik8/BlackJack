package Game;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> cards = new ArrayList<Card>();
    int total = 0;

    public void addCard(Card card){
            cards.add(card);
            total += card.getValue();
    }


    public void PrintMyCards(){
        for(Card card : cards){
            System.out.println(card.toString());
        }
        System.out.println("total value: "+this.total);
    }

    public ArrayList<Card> getMyCards(){
        return cards;
    }

    public int getTotal(){
        return total;
    }

    public int cardsCount (){
        return cards.size();
    }
}
