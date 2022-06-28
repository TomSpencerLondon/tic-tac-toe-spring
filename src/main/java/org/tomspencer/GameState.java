package org.tomspencer;

public class GameState {


  private Status status;

  public GameState(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GameState gameState = (GameState) o;

    return status == gameState.status;
  }

  @Override
  public int hashCode() {
    return status.hashCode();
  }
}
