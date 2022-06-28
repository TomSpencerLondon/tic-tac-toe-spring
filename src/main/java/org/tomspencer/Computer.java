package org.tomspencer;

import java.util.Optional;
import java.util.Random;

public class Computer {
  private final Random random = new Random();

  public Board selectSquareFrom(Board board) {
    Optional<Square> square = Square.squareFrom(random.nextInt(9) + 1);

    if (square.isPresent() && !board.alreadyTaken(square.get())) {
      return board.take(square.get(), Player.O);
    }

    return board;
  }
}
