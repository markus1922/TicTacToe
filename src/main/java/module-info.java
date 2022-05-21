module com.markus.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.markus.tictactoe to javafx.fxml;
    exports com.markus.tictactoe;
}