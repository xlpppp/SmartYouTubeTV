package com.liskovsoft.smartyoutubetv.flavors.exoplayer.interceptors;

import androidx.annotation.NonNull;
import com.liskovsoft.sharedutils.helpers.Helpers;
import com.liskovsoft.smartyoutubetv.misc.myquerystring.MyQueryString;
import com.liskovsoft.smartyoutubetv.misc.myquerystring.MyQueryStringFactory;

public class LoungeData {
    public static final int STATE_PLAYING = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_UNDEFINED = -1;
    private static final String KEY_STATE = "req0_state";
    private static final String KEY_CURRENT_TIME = "req0_currentTime";
    private int mState = STATE_UNDEFINED;
    private int mCurrentTime;
    private MyQueryString mQueryString;

    public static LoungeData parse(String postData) {
        LoungeData loungeData = new LoungeData();

        if (postData != null) {
            MyQueryString myQueryString = MyQueryStringFactory.parse(postData);
            String state = myQueryString.get(KEY_STATE);
            String currentTime = myQueryString.get(KEY_CURRENT_TIME);

            loungeData.mQueryString = myQueryString;

            if (Helpers.isNumeric(state)) {
                loungeData.mState = Integer.parseInt(state);
            }

            if (Helpers.isNumeric(currentTime)) {
                loungeData.mCurrentTime = Integer.parseInt(currentTime);
            }
        }

        return loungeData;
    }

    public int getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(int currentTime) {
        mCurrentTime = currentTime;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    @NonNull
    @Override
    public String toString() {
        String result = "";

        if (mQueryString != null) {
            mQueryString.set(KEY_STATE, mState);
            mQueryString.set(KEY_CURRENT_TIME, mCurrentTime);
            result = mQueryString.toString();
        }

        return result;
    }
}
