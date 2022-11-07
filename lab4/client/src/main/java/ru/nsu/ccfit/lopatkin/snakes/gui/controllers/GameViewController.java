package ru.nsu.ccfit.lopatkin.snakes.gui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.config.GameConfig;
import ru.nsu.ccfit.lopatkin.snakes.game.cell.Point;
import ru.nsu.ccfit.lopatkin.snakes.game.player.PlayerWithScore;
import ru.nsu.ccfit.lopatkin.snakes.gui.utils.GameInfoWithButton;

public class GameViewController implements View {

    private static final Paint FRUIT_COLOR = Color.GREEN;
    private static final Paint EMPTY_CELL_COLOR = Color.WHITE;

    @FXML
    private TableColumn<GameInfoWithButton, String> masterColumn;
    @FXML
    private TableColumn<GameInfoWithButton, Integer> playersNumberColumn;
    @FXML
    private TableColumn<GameInfoWithButton, String> fieldSizeColumn;
    @FXML
    private TableColumn<GameInfoWithButton, String> foodColumn;
    @FXML
    private TableColumn<GameInfoWithButton, Button> connectButtonColumn;
    @FXML
    private TableColumn<PlayerWithScore, String> playerNameColumn;
    @FXML
    private TableColumn<PlayerWithScore, Integer> playerScoreColumn;
    @FXML
    private Label gameOwner;
    @FXML
    private Label foodAmount;
    @FXML
    private Label fieldSize;
    @FXML
    private TableView<PlayerWithScore> playersRankingTable;
    @FXML
    private Button exitButton;
    @FXML
    private Button newGameButton;
    @FXML
    private TableView<GameInfoWithButton> gameListTable;
    @FXML
    private BorderPane gameFieldPane;

    @FXML
    void initialize() {
        assert connectButtonColumn != null : "fx:id=\"connectButtonColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'GameView.fxml'.";
        assert fieldSize != null : "fx:id=\"fieldSize\" was not injected: check your FXML file 'GameView.fxml'.";
        assert fieldSizeColumn != null : "fx:id=\"fieldSizeColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert foodAmount != null : "fx:id=\"foodAmount\" was not injected: check your FXML file 'GameView.fxml'.";
        assert foodColumn != null : "fx:id=\"foodColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert gameFieldPane != null : "fx:id=\"gameFieldPane\" was not injected: check your FXML file 'GameView.fxml'.";
        assert gameListTable != null : "fx:id=\"gameListTable\" was not injected: check your FXML file 'GameView.fxml'.";
        assert gameOwner != null : "fx:id=\"gameOwner\" was not injected: check your FXML file 'GameView.fxml'.";
        assert masterColumn != null : "fx:id=\"masterColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert newGameButton != null : "fx:id=\"newGameButton\" was not injected: check your FXML file 'GameView.fxml'.";
        assert playerNameColumn != null : "fx:id=\"playerNameColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert playerScoreColumn != null : "fx:id=\"playerScoreColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert playersNumberColumn != null : "fx:id=\"playersNumberColumn\" was not injected: check your FXML file 'GameView.fxml'.";
        assert playersRankingTable != null : "fx:id=\"playersRankingTable\" was not injected: check your FXML file 'GameView.fxml'.";

    }

    private final ObservableList<PlayerWithScore> playersObservableList = FXCollections.observableArrayList();
    private final ObservableList<GameInfoWithButton> gameInfoObservableList = FXCollections.observableArrayList();

    private Rectangle[][] fieldCells;


    private Stage stage;
    private GameConfig gameConfig;

    private GamePresenter gamePresenter;

    @Override
    public void drawFruit(@NotNull Point point) {

    }

    @Override
    public void drawEmptyCell(@NotNull Point point) {

    }

    @Override
    public void drawSnakePoint(@NotNull Point point, @NotNull Color playerSnakeColor) {

    }

    @Override
    public void updateCurrentGameInfo(@NotNull String owner, int gameFieldHeight, int gameFieldWidth, int foodNumber) {

    }

    @Override
    public void showUserListInfo(@NotNull List<PlayerWithScore> playerWithScoreList) {

    }

    @Override
    public void setConfig(@NotNull GameConfig gameConfig) {

    }

    @Override
    public void showGameList(@NotNull Set<GameInfoWithButton> gameInfoWithButtons) {

    }
}