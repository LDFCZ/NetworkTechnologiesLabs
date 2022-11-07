package ru.nsu.ccfit.lopatkin.snakes.game;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.config.GameConfig;
import ru.nsu.ccfit.lopatkin.snakes.game.cell.Point;
import ru.nsu.ccfit.lopatkin.snakes.game.player.PlayerWithScore;
import ru.nsu.ccfit.lopatkin.snakes.game.snake.SnakeInfo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record GameState(@NotNull List<Point> fruits, @NotNull List<PlayerWithScore> activePlayers,
                        @NotNull List<SnakeInfo> snakeInfos, @NotNull GameConfig gameConfig,
                        int stateID) implements Serializable {

    public GameState(@NotNull List<Point> fruits,
                     @NotNull List<PlayerWithScore> activePlayers,
                     @NotNull List<SnakeInfo> snakeInfos,
                     @NotNull GameConfig gameConfig,
                     int stateID) {
        this.fruits = Collections.unmodifiableList(
                Objects.requireNonNull(fruits, "Fruits list cant be null")
        );
        this.activePlayers = Collections.unmodifiableList(
                Objects.requireNonNull(activePlayers, "Active players list cant be null")
        );
        this.snakeInfos = Collections.unmodifiableList(
                Objects.requireNonNull(snakeInfos, "Snake infos list cant be null")
        );
        this.gameConfig = Objects.requireNonNull(gameConfig, "Game config cant be null");
        this.stateID = stateID;
    }
}
