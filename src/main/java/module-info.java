module com.trevor.game.javafxfirstgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.trevor.game.javafxfirstgame to javafx.fxml;
    exports com.trevor.game.javafxfirstgame;
}