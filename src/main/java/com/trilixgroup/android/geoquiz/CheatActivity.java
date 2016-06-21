package com.trilixgroup.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.trilixgroup.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.trilixgroup.android.geoquiz.answer_shown";
    private static final String CHEATED_INDEX = "cheatActivity_cheated";

    private boolean mAnswerIsTrue;
    private boolean mCheated = false;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putBoolean(CHEATED_INDEX, mCheated);
        Log.d(TAG, "mCheated stored");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.text_view_answer);
        mShowAnswerButton = (Button) findViewById(R.id.button_show_answer);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheated = true;
                setTextViewText();
                setAnswerShownResult(true);
            }
        });

        if (savedInstanceState != null) {
            mCheated = savedInstanceState.getBoolean(CHEATED_INDEX, false);

            if (mCheated) {
                setTextViewText();
                setAnswerShownResult(true);
            }
        }
    }

    private void setTextViewText() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent data) {
        return data.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
