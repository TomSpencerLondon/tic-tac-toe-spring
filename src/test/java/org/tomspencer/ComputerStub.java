package org.tomspencer;

import java.util.Optional;

public class ComputerStub extends Computer {

  private Square nextSquare = Square.TOP_LEFT;

  public void setNextSquare(Square square) {
    this.nextSquare = square;
  }

  public Board selectSquareFrom(Board board) {
    Board take = board.take(nextSquare, Player.O);

    return take;
  }
}
