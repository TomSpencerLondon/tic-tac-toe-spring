package org.tomspencer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.tomspencer.Status.DRAW;

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
        .containsExactlyInAnyOrder(Square.TOP_RIGHT, Square.CENTER_RIGHT);
  }

  @Test
  void givenComputerMove_PlayerNotAllowedSameSquare() {
    ComputerStub computer = new ComputerStub();
    computer.setNextSquare(Square.TOP_LEFT);
    Game game = new Game(computer);
    game.play(Square.TOP_RIGHT);

    assertThatThrownBy(() -> game.play(Square.TOP_LEFT))
        .isInstanceOf(SquareAlreadyTakenException.class);
  }

  @Test
  void giveDrawReturnDraw() {
    ComputerStub computer = new ComputerStub();
    Game game = new Game(computer);
    computer.setNextSquare(Square.TOP_MIDDLE);
    game.play(Square.TOP_LEFT);
    computer.setNextSquare(Square.CENTER_LEFT);
    game.play(Square.TOP_RIGHT);
    computer.setNextSquare(Square.BOTTOM_LEFT);
    game.play(Square.CENTER_MIDDLE);
    computer.setNextSquare(Square.BOTTOM_RIGHT);
    game.play(Square.CENTER_RIGHT);
    game.play(Square.BOTTOM_MIDDLE);

    assertThat(game.state())
        .isEqualTo(new GameState(Status.DRAW));
    assertThat(game.playedBy(Player.X))
        .containsExactlyInAnyOrder(
            Square.TOP_LEFT,
            Square.TOP_RIGHT,
            Square.CENTER_MIDDLE,
            Square.CENTER_RIGHT,
            Square.BOTTOM_MIDDLE
        );
  }
}
