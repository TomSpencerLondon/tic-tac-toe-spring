package org.tomspencer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  void startGame_x_to_play() {
    Computer computer = new ComputerStub();
    Game game = new Game(computer);

    assertThat(game.state())
        .isEqualTo(new GameState(Status.IN_PROGRESS));
  }

  @Test
  void afterPlayerPlays_Game_plays_move() {
    ComputerStub computer = new ComputerStub();
    computer.setNextSquare(Square.TOP_LEFT);
    Game game = new Game(computer);

    game.play(Square.TOP_RIGHT);

    assertThat(game.state())
        .isEqualTo(new GameState(Status.IN_PROGRESS));
    assertThat(game.playedBy(Player.X))
        .containsExactly(Square.TOP_RIGHT);
  }

  @Test
  void alternatePlayer_and_game() {
    ComputerStub computer = new ComputerStub();
    computer.setNextSquare(Square.TOP_LEFT);
    Game game = new Game(computer);
    game.play(Square.TOP_RIGHT);
    computer.setNextSquare(Square.CENTER_LEFT);
    game.play(Square.CENTER_RIGHT);

    assertThat(game.state())
        .isEqualTo(new GameState(Status.IN_PROGRESS));
    assertThat(game.playedBy(Player.X))
        .containsExactly(Square.TOP_RIGHT, Square.CENTER_RIGHT);
  }
}
