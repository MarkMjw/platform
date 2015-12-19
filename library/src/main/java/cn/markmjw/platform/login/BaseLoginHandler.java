/*
 * Copyright (C) 2015 MarkMjw
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.markmjw.platform.login;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The abstract login handler.
 *
 * @author markmjw
 * @since 1.0.0
 */
public abstract class BaseLoginHandler {
    protected static final String TAG = "LoginHandler";

    protected boolean mRequestInfoEnable = true;
    protected boolean mLogEnable = true;

    private ILoginListener mLoginListener;

    public BaseLoginHandler() {

    }

    /**
     * set request user info able.
     *
     * @param requestEnable true, it's will be request user information after auth success.
     */
    public void setRequestUserInfo(boolean requestEnable) {
        mRequestInfoEnable = requestEnable;
    }

    /**
     * set can be log able.
     *
     * @param enable true, it's print log. otherwise did't.
     */
    public void setLogEnable(boolean enable) {
        mRequestInfoEnable = mLogEnable;
    }

    /**
     * set login callback
     *
     * @param listener the callback
     */
    protected void setCallBack(ILoginListener listener) {
        mLoginListener = listener;
    }

    /**
     * callback
     *
     * @param statusCode status code
     * @param data       callback data
     */
    protected synchronized void callBack(int statusCode, Object data) {
        if (null != mLoginListener) {
            mLoginListener.loginStatus(statusCode, data);
        }
    }

    /**
     * format date with yyyy-MM-dd hh:MM:ss
     *
     * @param time the date time
     * @return format string
     */
    protected String formatDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd hh:MM:ss", Locale.CHINA).format(new Date(time));
    }

    /**
     * print log if {@link #mLogEnable} is true.
     *
     * @param message the log message
     */
    protected void log(String message) {
        if (mLogEnable) {
            Log.i(TAG, message);
        }
    }
}
