module org.csc311.cardgame24 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.csc311.cardgame24 to javafx.fxml;
    exports org.csc311.cardgame24;
}