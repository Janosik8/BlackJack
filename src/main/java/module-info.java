module blackjack.blackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens blackjack.blackjack to javafx.fxml;
    exports blackjack.blackjack;
}