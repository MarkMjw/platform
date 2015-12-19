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

package cn.markmjw.platform;

/**
 * Platform config.
 *
 * @author markmjw
 * @since 1.0.0
 */
public class PlatformConfig {
    private static PlatformConfig mInstance;

    private String mWeiboKey;
    private String mWeiboSecret;
    private String mWeiboCallback;
    private String mWeiboScope;

    private String mWechatId;
    private String mWechatSecret;
    private String mWechatScope;
    private String mWechatState;

    private String mQQId;
    private String mQQSecret;

    private PlatformConfig() {
    }

    public static PlatformConfig getInstance() {
        if (mInstance == null) {
            mInstance = new PlatformConfig();
        }
        return mInstance;
    }

    /**
     * init weibo config
     *
     * @param key      app key
     * @param secret   app secret
     * @param callback the callback url
     * @param scope    the scope
     * @return this object
     */
    public PlatformConfig initWeibo(String key, String secret, String callback, String scope) {
        mWeiboKey = key;
        mWeiboSecret = secret;
        mWeiboCallback = callback;
        mWeiboScope = scope;
        return this;
    }

    /**
     * init wechat config
     *
     * @param key    app key
     * @param secret app secret
     * @param scope  scope
     * @param state  state
     * @return this object
     */
    public PlatformConfig initWechat(String key, String secret, String scope, String state) {
        mWechatId = key;
        mWechatSecret = secret;
        mWechatScope = scope;
        mWechatState = state;
        return this;
    }

    /**
     * init QQ config
     *
     * @param key    app key
     * @param secret app secret
     * @return this object
     */
    public PlatformConfig initQQ(String key, String secret) {
        mQQId = key;
        mQQSecret = secret;
        return this;
    }

    public String getWechatId() {
        return mWechatId;
    }

    public String getWechatSecret() {
        return mWechatSecret;
    }

    public String getWeiboKey() {
        return mWeiboKey;
    }

    public String getWeiboSecret() {
        return mWeiboSecret;
    }

    public String getWeiboCallback() {
        return mWeiboCallback;
    }

    public String getWeiboScope() {
        return mWeiboScope;
    }

    public String getQQId() {
        return mQQId;
    }

    public String getQQSecret() {
        return mQQSecret;
    }

    public String getWechatScope() {
        return mWechatScope;
    }

    public String getWechatState() {
        return mWechatState;
    }

    public void setWeiboKey(String mWeiboKey) {
        this.mWeiboKey = mWeiboKey;
    }

    public void setWeiboSecret(String mWeiboSecret) {
        this.mWeiboSecret = mWeiboSecret;
    }

    public void setWeiboCallback(String mWeiboCallback) {
        this.mWeiboCallback = mWeiboCallback;
    }

    public void setWeiboScope(String mWeiboScope) {
        this.mWeiboScope = mWeiboScope;
    }

    public void setWechatId(String mWechatId) {
        this.mWechatId = mWechatId;
    }

    public void setWechatSecret(String mWechatSecret) {
        this.mWechatSecret = mWechatSecret;
    }

    public void setWechatScope(String mWechatScope) {
        this.mWechatScope = mWechatScope;
    }

    public void setWechatState(String mWechatState) {
        this.mWechatState = mWechatState;
    }

    public void setQQId(String mQQId) {
        this.mQQId = mQQId;
    }

    public void setQQSecret(String mQQSecret) {
        this.mQQSecret = mQQSecret;
    }
}
