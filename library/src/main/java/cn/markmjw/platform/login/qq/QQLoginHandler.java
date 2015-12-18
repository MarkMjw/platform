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

package cn.markmjw.platform.login.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import cn.markmjw.platform.QQHelper;
import cn.markmjw.platform.login.AuthResult;
import cn.markmjw.platform.login.BaseLoginHandler;
import cn.markmjw.platform.login.ILoginListener;
import cn.markmjw.platform.util.GsonUtil;

/**
 * QQ login handler.
 *
 * @author markmjw
 * @since 2015-04-07
 */
public class QQLoginHandler extends BaseLoginHandler {
    private QQHelper mManager;
    private Context mContext;

    public QQLoginHandler(Context context) {
        mContext = context.getApplicationContext();
        mManager = QQHelper.getInstance(context);
    }

    public void login(Activity activity, ILoginListener listener) {
        setCallBack(listener);
        mManager.getTencent().login(activity, "all", mAuthListener);
    }

    public void logout(Activity activity) {
        mManager.getTencent().logout(activity);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mManager.getTencent().onActivityResult(requestCode, resultCode, data);
    }

    private IUiListener mAuthListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                callBack(ILoginListener.CODE_AUTH_FAILED, "");
                // release resource
                mManager.getTencent().releaseResource();
                return;
            }

            QQLoginResult result = GsonUtil.fromJson(response + "", QQLoginResult.class);
            AuthResult auth = new AuthResult();
            auth.from = AuthResult.TYPE_QQ;
            auth.id = result.openid;
            auth.accessToken = result.access_token;
            auth.expiresIn = result.expires_in;

            mManager.getTencent().setAccessToken(auth.accessToken, auth.expiresIn + "");
            mManager.getTencent().setOpenId(auth.id);
            long expiresIn = mManager.getTencent().getExpiresIn();
            log("QQ authorize success!" +
                    "\nOpenId: " + auth.id +
                    "\nAccess token: " + auth.accessToken +
                    "\nExpires in: " + formatDate(expiresIn));

            callBack(ILoginListener.CODE_AUTH_SUCCESS, auth);
            if (mRequestInfoEnable) {
                callBack(ILoginListener.CODE_LOGIN_ING, "");
                // request user info
                new UserInfo(mContext, mManager.getTencent().getQQToken())
                        .getUserInfo(mGetInfoListener);
            }
        }

        @Override
        public void onError(UiError e) {
            callBack(ILoginListener.CODE_AUTH_EXCEPTION, e.errorMessage);
            // release resource
            mManager.getTencent().releaseResource();
        }

        @Override
        public void onCancel() {
            callBack(ILoginListener.CODE_CANCEL_AUTH, null);
            // release resource
            mManager.getTencent().releaseResource();
        }
    };

    private IUiListener mGetInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                callBack(ILoginListener.CODE_AUTH_FAILED, "");
                // release resource
                mManager.getTencent().releaseResource();
                return;
            }

            // request user info success
            QQUserInfo info = GsonUtil.fromJson(response + "", QQUserInfo.class);
            callBack(ILoginListener.CODE_SUCCESS, info);

            // release resource
            mManager.getTencent().releaseResource();
        }

        @Override
        public void onError(UiError e) {
            callBack(ILoginListener.CODE_FAILED, e.errorMessage);
            // release resource
            mManager.getTencent().releaseResource();
        }

        @Override
        public void onCancel() {
            callBack(ILoginListener.CODE_CANCEL_AUTH, null);
            // release resource
            mManager.getTencent().releaseResource();
        }
    };
}
