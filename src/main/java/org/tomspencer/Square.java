package org.tomspencer;

import java.util.Arrays;
import java.util.Optional;

public enum Square {
  TOP_LEFT(1), TOP_MIDDLE(2), TOP_RIGHT(3),
  CENTER_LEFT(4), CENTER_MIDDLE(5), CENTER_RIGHT(6),
  BOTTOM_LEFT(7), BOTTOM_MIDDLE(8), BOTTOM_RIGHT(9);

  private final int value;

  Square(int value) {
    this.value = value;
  }

  public static Optional<Square> squareFrom(int value) {
    return Arrays.stream(values())
        .filter(square -> square.value == value)
        .findFirst();
  }
}
