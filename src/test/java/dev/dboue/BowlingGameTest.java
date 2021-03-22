package dev.dboue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void shouldScoreNoPoints() {
        //GIVEN
        //WHEN
        IntStream.range(0, 20)
            .forEach(i -> {
                bowlingGame.roll(0);
            });

        //THEN
        assertThat(bowlingGame.score()).isZero();
    }

    @Test
    void shouldScoreOnePointEachRoll() {
        //GIVEN
        //WHEN
        IntStream.range(0, 20)
            .forEach(i -> {
                bowlingGame.roll(1);
            });

        //THEN
        assertThat(bowlingGame.score()).isEqualTo(20);
    }

    @Test
    void shouldScoreSpare() {
        //GIVEN
        //WHEN
        bowlingGame.roll(7);
        bowlingGame.roll(3);
        bowlingGame.roll(2);
        bowlingGame.roll(2);
        IntStream.range(0, 16)
            .forEach(i -> {
                bowlingGame.roll(0);
            });

        //THEN
        assertThat(bowlingGame.score()).isEqualTo((7 + 3) + 2 + 2 + 2);
    }

    @Test
    void shouldScoreStrike() {
        //GIVEN
        //WHEN
        bowlingGame.roll(10);
        bowlingGame.roll(3);
        bowlingGame.roll(2);
        IntStream.range(0, 16)
            .forEach(i -> {
                bowlingGame.roll(0);
            });

        //THEN
        assertThat(bowlingGame.score()).isEqualTo((10) + (3 + 2) + (3 + 2));
    }

    @Test
    void shouldScorePerfectGame() {
        //GIVEN
        //WHEN
        IntStream.range(0, 12)
            .forEach(i -> {
                bowlingGame.roll(10);
            });

        //THEN
        assertThat(bowlingGame.score()).isEqualTo(300);
    }
}
