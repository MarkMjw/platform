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

package cn.markmjw.platform.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tencent.mm.sdk.modelmsg.SendAuth;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.markmjw.platform.PlatformConfig;
import cn.markmjw.platform.login.AuthResult;
import cn.markmjw.platform.login.ILoginListener;
import cn.markmjw.platform.login.qq.QQLoginHandler;
import cn.markmjw.platform.login.wechat.WechatLoginHandler;
import cn.markmjw.platform.login.weibo.WeiboLoginHandler;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.login_auth_info)
    TextView mLoginAuthInfo;
    @Bind(R.id.login_user_info)
    TextView mLoginUserInfo;

    private ILifecycleListener mLifeListener;

    private MaterialDialog mProgressDialog;

    private WechatLoginHandler mWechatHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // TODO change the value with your config
        PlatformConfig.getInstance()
                .initWeibo("WEIBO.KEY", "WEIBO.SECRET", "WEIBO.CALLBACK", "")
                .initWechat("WECHAT.APPID", "WECHAT.SECRET", "WECHAT.SCOPE", "WECHAT.STATE")
                .initQQ("QQ.APPID", "QQ.SECRET");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mLifeListener != null) {
            mLifeListener.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWechatHandler = null;
    }

    @OnClick(R.id.login_with_weibo)
    protected void onWeiboClick() {
        loginWeibo();
    }

    @OnClick(R.id.login_with_wechat)
    protected void onWechatClick() {
        loginWechat();
    }

    @OnClick(R.id.login_with_qq)
    protected void onQQClick() {
        loginQQ();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(SendAuth.Resp response) {
        EventBus.getDefault().unregister(this);
        if (null != mWechatHandler) {
            mWechatHandler.handleResponse(response);
        }
    }

    private void setLifecycleListener(ILifecycleListener lifeListener) {
        mLifeListener = lifeListener;
    }

    private void loginWeibo() {
        final WeiboLoginHandler handler = new WeiboLoginHandler();
        handler.setLogEnable(true);
        handler.setRequestUserInfo(true);
        this.setLifecycleListener(new ILifecycleListener() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                handler.onActivityResult(requestCode, resultCode, data);
                MainActivity.this.setLifecycleListener(null);
            }
        });
        handler.login(this, new LoginListener());
    }

    private void loginWechat() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mWechatHandler = new WechatLoginHandler(this);
        mWechatHandler.setLogEnable(true);
        mWechatHandler.setRequestUserInfo(true);
        mWechatHandler.login(new LoginListener());
    }

    private void loginQQ() {
        final QQLoginHandler handler = new QQLoginHandler(this);
        handler.setLogEnable(true);
        handler.setRequestUserInfo(true);
        this.setLifecycleListener(new ILifecycleListener() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                handler.onActivityResult(requestCode, resultCode, data);
                MainActivity.this.setLifecycleListener(null);
            }
        });
        handler.login(this, new LoginListener());
    }

    private void onLoginFinish(int code, Object data) {
        switch (code) {
            case ILoginListener.CODE_SUCCESS:
                dismissProgressDialog();
                shortToast(R.string.login_success);
                // TODO: 2015/12/18
                // save user info and other logic
                mLoginUserInfo.setText(null != data ? data.toString() : "");
                break;

            case ILoginListener.CODE_FAILED:
                dismissProgressDialog();
                shortToast(R.string.login_failed);
                break;

            case ILoginListener.CODE_LOGIN_ING:
                showProgressDialog(R.string.login_ing, true);
                break;

            case ILoginListener.CODE_AUTH_SUCCESS:
                if (data instanceof AuthResult) {
                    AuthResult result = (AuthResult) data;
                    // TODO: 2015/12/18
                    // save login data and other logic
                    mLoginAuthInfo.setText(result.toString());
                } else {
                    dismissProgressDialog();
                    showMessageDialog(R.string.auth_error);
                }
                break;

            case ILoginListener.CODE_AUTH_EXCEPTION:
                showMessageDialog(R.string.auth_exception);
                break;

            case ILoginListener.CODE_CANCEL_AUTH:
                showMessageDialog(R.string.auth_cancel);
                break;

            case ILoginListener.CODE_AUTH_FAILED:
                showMessageDialog(R.string.auth_error);
                break;
        }
    }

    public void shortToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(int resId, boolean cancelAble) {
        showProgressDialog(getString(resId), cancelAble);
    }

    public void showProgressDialog(String message, boolean cancelAble) {
        if (null == mProgressDialog) {
            mProgressDialog = new MaterialDialog.Builder(this)
                    .progress(true, 0)
                    .widgetColorRes(android.R.color.holo_red_light)
                    .build();
            mProgressDialog.setCanceledOnTouchOutside(false);
        }

        mProgressDialog.setContent(message);
        mProgressDialog.setCancelable(cancelAble);
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showMessageDialog(int message) {
        showMessageDialog(getString(message));
    }

    public void showMessageDialog(String message) {
        new MaterialDialog.Builder(this)
                .title(R.string.tip)
                .content(message)
                .cancelable(true)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction action) {
                        dialog.dismiss();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction action) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private class LoginListener implements ILoginListener {
        @Override
        public void loginStatus(final int code, final Object data) {
            // running in main thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onLoginFinish(code, data);
                }
            });
        }
    }

    public interface ILifecycleListener {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
