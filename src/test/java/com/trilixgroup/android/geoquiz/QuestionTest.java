package com.trilixgroup.android.geoquiz;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by tylerfunk on 6/20/16.
 */
public class QuestionTest {
    @Test
    public void checkAnswerTrue() {
        Question q = new Question(0, false);
        assertArrayEquals(
                new boolean[]{true, false},
                new boolean[]{q.checkAnswer(false), q.checkAnswer(true)}
        );
    }

    @Test
    public void checkAnswerFalse() {
        Question q = new Question(0, true);
        assertArrayEquals(
                new boolean[]{false, true},
                new boolean[]{q.checkAnswer(false), q.checkAnswer(true)}
        );
    }
}
