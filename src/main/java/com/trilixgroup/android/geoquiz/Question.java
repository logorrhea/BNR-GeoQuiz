package com.trilixgroup.android.geoquiz;

import android.os.Parcel;
import android.os.Parcelable;

class Question implements Parcelable {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mCheated;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mCheated = false;
    }

    public Question(int textResId, boolean answerTrue, boolean cheated) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mCheated = cheated;
    }

    protected Question(Parcel in) {
        mTextResId = in.readInt();
        mAnswerTrue = in.readByte() != 0;
        mCheated = in.readByte() != 0;
    }

    public boolean didCheat() {
        return mCheated;
    }

    public void setCheated(boolean cheated) {
        mCheated = cheated;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getTextResId() {
        return mTextResId;
    }

    public boolean checkAnswer(boolean answer) {
        return answer == mAnswerTrue;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTextResId);
        dest.writeByte((byte) (mAnswerTrue ? 1 : 0));
        dest.writeByte((byte) (mCheated ? 1 : 0));
    }
}

