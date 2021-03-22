package dev.dboue;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    public static final int MAX_FRAME_SCORE = 10;
    public static final int NUMBER_OF_FRAMES = 10;
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pinsDown) {
        rolls.add(pinsDown);
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < NUMBER_OF_FRAMES; frame++) {
            score += frameScore(rollIndex);
            rollIndex = nextRollIndex(rollIndex);
        }

        return score;
    }

    private int frameScore(final int rollIndex) {
        if (isStrike(rollIndex)) {
            return scoreStrike(rollIndex);
        } else if (isSpareScore(rollIndex)) {
            return spareFrameScore(rollIndex);
        }

        return normalFrameScore(rollIndex);
    }

    private int nextRollIndex(final int rollIndex) {
        if (isStrike(rollIndex)) {
            return rollIndex + 1;
        }

        return rollIndex + 2;
    }

    private boolean isStrike(int rollIndex) {
        return rolls.get(rollIndex) == MAX_FRAME_SCORE;
    }

    private int scoreStrike(int rollIndex) {
        return MAX_FRAME_SCORE + strikeBonus(rollIndex);
    }

    private int strikeBonus(int rollIndex) {
        return rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
    }

    private boolean isSpareScore(int rollIndex) {
        return normalFrameScore(rollIndex) == MAX_FRAME_SCORE;
    }

    private int spareFrameScore(int rollIndex) {
        return MAX_FRAME_SCORE + spareBonus(rollIndex);
    }

    private Integer spareBonus(int rollIndex) {
        return rolls.get(rollIndex + 2);
    }

    private int normalFrameScore(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1);
    }
}
