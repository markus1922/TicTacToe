package com.markus.tictactoe;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class TicTacToe extends Application {
    private Cell[][] cell = new Cell[3][3];
    private Label lblStatus = new Label("X's turn to play next");
    private char whoseTurn = 'X';

    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(cell[i][j] = new Cell(), j, i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        Scene scene = new Scene(borderPane, 450, 170);
        primaryStage.setTitle("TicTacToe: Cardials version");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if (cell[i][j].getToken() == ' ') {
                return false;
    }
        }
            }
    return true;
}

public boolean isWon(char token) {
    for (int i = 0; i < 3; i++) {
        if (cell[i][0].getToken() == token
            && cell[i][1].getToken() == token
            && cell[i][2].getToken() == token) {
            return true;
        }
    }

    for (int j = 0; j < 3; j++) {
        if (cell[0][j].getToken() == token
                && cell[1][j].getToken() == token
                && cell[2][j].getToken() == token) {
            return true;
        }
    }

    if (cell[0][0].getToken() == token
        && cell[1][1].getToken() == token
        && cell[2][2].getToken() == token) {
    }
    return false;
}

public class Cell extends Pane {
    private char token = ' ';
    public Cell() {
        setStyle("-fx-border-color:black");
        this.setPrefSize(800, 800);
        this.setOnMouseClicked(e->handleMouseClick());
    }

    public char getToken() {
        return token;
    }

    public void setToken(char c) {
        token = c;
        if (token == 'X') {
            Line line1 = new Line(10, 10, this.getWidth()-10,
                    this.getHeight()-10);
                    line1.endXProperty().bind(this.widthProperty().subtract(10));
                    line1.endYProperty().bind(this.heightProperty().subtract(10));
                    line1.setStroke(Color.BLUE);
                    Line line2 = new Line(10,this.getHeight()-10,
                            this.getWidth()-10,-10);
                    line2.startYProperty().bind(this.heightProperty().subtract(10));
                    line2.endXProperty().bind(this.widthProperty().subtract(10));
                    line2.setStroke(Color.YELLOW);
                    this.getChildren().addAll(line1,line2);
                    lblStatus.setTextFill(Color.GREEN);
            } else if (token == '0') {
                 //Ellipse part
                Ellipse ellipse = new Ellipse(this.getWidth()/2,
                        this.getHeight() / 2, this.getWidth() / 2 -8,
                        this.getHeight() / 2 - 8);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.GREEN);
                ellipse.setFill(Color.WHITE);
                this.getChildren().add(ellipse);
                lblStatus.setTextFill(Color.BLUE);
            }
        }

//Mouse click part
    private void handleMouseClick() {
         if (token == ' ' && whoseTurn != ' ') {
            setToken(whoseTurn);
            if (isWon(whoseTurn)) {
                lblStatus.setText(whoseTurn + " won! Good game. Game over.");
                lblStatus.setTextFill(Color.BLACK);
                lblStatus.setFont(Font.font(25));
                whoseTurn = ' ';
            } else if (isFull()) {
                lblStatus.setText("Draw! Game over. Run it back?");
                lblStatus.setTextFill(Color.RED);
                lblStatus.setFont(Font.font(15));
                whoseTurn = ' ';
            } else {
                whoseTurn = (whoseTurn == 'X') ? '0' : 'X';
                lblStatus.setText(whoseTurn + "'s turn");;
            }
        }
    }
}
public static void main(String[] args) {
    launch(args);
    }
}