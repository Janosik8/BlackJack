package Game;

import java.util.Objects;

public class Card {
    String value;
    String type;
    Card(String value,String type){
        this.value = value;
        this.type  = type;
    }

    public String toString(){
        return value + "-"+ type;
    }

    public int getValue(){
        if(Objects.equals(value, "A")){return 11;}
        if(Objects.equals(value, "J") || Objects.equals(value, "Q") || Objects.equals(value, "K")){return 10;}
        return Integer.parseInt(value);
    }
}
