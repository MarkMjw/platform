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

/**
 * 本客户端以及第三方平台相关常量
 *
 * @author markmjw
 * @date 2015-08-09
 */
public enum AppKey {
    ART("dev1.0", "HrepMUeHP6Kc9mQmnI0BwIG8nvmw3WG0", "", ""),
    WEIBO("1354293035", "d12177d70b83517503856b9b6481027c", "https://api.weibo.com/oauth2/default.html", ""),
    WECHAT("wx8c5c6dad33466b69", "b29eecaaea91b987babb0d49b074dc26", "ifengartwxaccb11f40868eb86", "snsapi_userinfo"),
    QQ("1104817836", "THmasj8hFaABoyg0", "", "");

    public final String KEY;
    public final String SECRET;
    public final String CALLBACK;
    public final String SCOPE;

    AppKey(String key, String secret, String callback, String scope) {
        this.KEY = key;
        this.SECRET = secret;
        this.CALLBACK = callback;
        this.SCOPE = scope;
    }
}
