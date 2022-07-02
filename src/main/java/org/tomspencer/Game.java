package org.tomspencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

  private Board board;
  private Computer computer;
  private GameState gameState;

  public Game(Computer computer) {
    this.computer = computer;
    gameState = new GameState(Status.IN_PROGRESS);
    board = new Board();
  }

  public GameState state() {
    return gameState;
  }

  public void play(Square square) {
    if (board.alreadyTaken(square)) {
      throw new SquareAlreadyTakenException();
    }

    board = computer.selectSquareFrom(board.take(square, Player.X));

    if (board.isFull()) {
      gameState = new GameState(Status.DRAW);
    }
  }

  public List<Square> playedBy(Player player) {
    return board.squares()
        .entrySet()
        .stream()
        .filter(e -> e.getValue().equals(player))
        .map(Entry::getKey).collect(Collectors.toList());
  }
}
