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

package cn.markmjw.platform.login.wechat;

import android.content.Context;
import android.text.TextUtils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.markmjw.platform.PlatformConfig;
import cn.markmjw.platform.WechatHelper;
import cn.markmjw.platform.login.AuthResult;
import cn.markmjw.platform.login.BaseLoginHandler;
import cn.markmjw.platform.login.ILoginListener;
import cn.markmjw.platform.util.GsonUtil;
import cn.markmjw.platform.util.HttpUtil;

/**
 * Weibo login handler.
 *
 * @author markmjw
 * @since 2015-04-21
 */
public class WechatLoginHandler extends BaseLoginHandler {
    /** Get token url. */
    private static final String URL_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /** Get user info url. */
    private static final String URL_WECHAT_USER = "https://api.weixin.qq.com/sns/userinfo";

    private WechatHelper mManager;

    public WechatLoginHandler(Context context) {
        mManager = WechatHelper.getInstance(context);
    }

    public void login(ILoginListener listener) {
        setCallBack(listener);
        SendAuth.Req request = new SendAuth.Req();
        request.scope = PlatformConfig.getInstance().getWechatScope();
        request.state = PlatformConfig.getInstance().getWechatState();
        mManager.getAPI().sendReq(request);
    }

    public void handleResponse(SendAuth.Resp response) {
        switch (response.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = response.code;
                if (!TextUtils.isEmpty(code)) {
                    callBack(ILoginListener.CODE_LOGIN_ING, "");
                    requestToken(code);
                } else {
                    callBack(ILoginListener.CODE_AUTH_FAILED, "");
                }
                break;

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                callBack(ILoginListener.CODE_AUTH_FAILED, "");
                break;

            case BaseResp.ErrCode.ERR_USER_CANCEL:
                callBack(ILoginListener.CODE_CANCEL_AUTH, "");
                break;
        }
    }

    private void requestToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", PlatformConfig.getInstance().getWechatId());
        params.put("secret", PlatformConfig.getInstance().getWechatSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String url = HttpUtil.buildUrl(URL_TOKEN, params);
        final Request request = new Request.Builder().url(url).build();
        HttpUtil.enqueue(request, new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                callBack(ILoginListener.CODE_AUTH_FAILED, e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String data = response.body().string();
                    WechatLoginResult result = GsonUtil.fromJson(data, WechatLoginResult.class);
                    AuthResult auth = new AuthResult();
                    auth.from = AuthResult.TYPE_WECHAT;
                    auth.id = result.openid;
                    auth.accessToken = result.accessToken;
                    auth.expiresIn = result.expiresIn;
                    auth.refreshToken = result.refreshToken;

                    log("Wechat authorize success!" +
                            "\nOpenId: " + auth.id +
                            "\nAccess token: " + auth.accessToken +
                            "\nExpires in: " + formatDate(auth.expiresIn));

                    callBack(ILoginListener.CODE_AUTH_SUCCESS, auth);
                    if (mRequestInfoEnable) {
                        callBack(ILoginListener.CODE_LOGIN_ING, "");
                        // request user info
                        requestUserInfo(auth.id, auth.accessToken);
                    }
                } else {
                    callBack(ILoginListener.CODE_AUTH_FAILED, response.message());
                }
            }
        });
    }

    private void requestUserInfo(String openId, String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        String url = HttpUtil.buildUrl(URL_WECHAT_USER, params);
        Request request = new Request.Builder().url(url).build();
        HttpUtil.enqueue(request, new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                callBack(ILoginListener.CODE_FAILED, e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String data = response.body().string();
                    WechatUserInfo info = GsonUtil.fromJson(data, WechatUserInfo.class);
                    callBack(ILoginListener.CODE_SUCCESS, info);
                } else {
                    callBack(ILoginListener.CODE_FAILED, response.message());
                }
            }
        });
    }
}
