package org.tomspencer;

import java.util.HashMap;
import java.util.Map;

public class Board {

  private static final int FULL_BOARD = 9;
  private final Map<Square, Player> takenSquares;

  public Board() {
    this.takenSquares = new HashMap<>();
  }

  public Map<Square, Player> squares() {
    return takenSquares;
  }

  private Board(Map<Square, Player> takenSquares) {
    this.takenSquares = takenSquares;
  }

  public Board take(Square square, Player player) {
    HashMap<Square, Player> newBoard = new HashMap<>(takenSquares);
    newBoard.put(square, player);
    return new Board(newBoard);
  }

  public boolean alreadyTaken(Square square) {
    return takenSquares.containsKey(square);
  }

  public boolean isFull() {
    return this.takenSquares.entrySet().size() == FULL_BOARD;
  }
}
