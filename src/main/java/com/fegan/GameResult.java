package com.fegan;

public enum GameResult {
    WIN,
    LOSS,
    DRAW;

    @Override
    public String toString() {
        return switch (this) {
            case WIN -> "Win";
            case LOSS -> "Loss";
            case DRAW -> "Draw";
        };
    }
}
