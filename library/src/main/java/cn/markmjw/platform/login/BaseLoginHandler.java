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
 * @since 2015-04-08
 */
public abstract class BaseLoginHandler {
    protected static final String TAG = "LoginHandler";

    protected boolean mRequestInfoEnable = true;
    protected boolean mLogEnable = true;

    private ILoginListener mLoginListener;

    public BaseLoginHandler() {

    }

    public void setRequestUserInfo(boolean requestEnable) {
        mRequestInfoEnable = requestEnable;
    }

    public void setLogEnable(boolean enable) {
        mRequestInfoEnable = mLogEnable;
    }

    protected void setCallBack(ILoginListener listener) {
        mLoginListener = listener;
    }

    protected synchronized void callBack(int statusCode, Object data) {
        if (null != mLoginListener) {
            mLoginListener.loginStatus(statusCode, data);
        }
    }

    protected String formatDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd hh:MM:ss", Locale.CHINA).format(new Date(time));
    }

    protected void log(String message) {
        if (mLogEnable) {
            Log.i(TAG, message);
        }
    }
}
