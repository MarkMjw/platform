platform
================================
An Android library login and share with third party such as Weibo,Wechat and QQ.

> Author weibo：<a href="http://weibo.com/markmjw" target="_blank">MarkMjw</a>

Features
------
* support Weibo, Wechat, QQ login and share
* request by [okhttp](https://github.com/square/okhttp)
* easy integration
* open source

Gradle
------
```groovy
compile('cn.markmjw:platform:1.0.0')
```

Usage
-------
####Config
* Required
```java
PlatformConfig.getInstance()
                .initWeibo(WEIBO.KEY, WEIBO.SECRET, WEIBO.CALLBACK, "")
                .initWechat(WECHAT.KEY, WECHAT.SECRET, WECHAT.SCOPE, WECHAT.CALLBACK)
                .initQQ(QQ.KEY, QQ.SECRET);
```

####Login

* login with Weibo
```java
WeiboLoginHandler handler = new WeiboLoginHandler();
handler.setLogEnable(true);
handler.setRequestUserInfo(true);
activity.setLifecycleListener(new ILifecycleListener() {
     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
           handler.onActivityResult(requestCode, resultCode, data);
           activity.setLifecycleListener(null);
     }
});
handler.login(activity, new LoginListener());
```

* login with Wechat
```java
WechatLoginHandler handler = new WechatLoginHandler();
handler.setLogEnable(true);
handler.setRequestUserInfo(true);
handler.login(new LoginListener());
```

* login with QQ
```java
QQLoginHandler handler = new QQLoginHandler();
handler.setLogEnable(true);
handler.setRequestUserInfo(true);
activity.setLifecycleListener(new ILifecycleListener() {
     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
           handler.onActivityResult(requestCode, resultCode, data);
           activity.setLifecycleListener(null);
     }
});
handler.login(activity, new LoginListener());
```

####Share

* share with Weibo
* share with Wechat
* share with QQ

See more details on the [Wiki](https://github.com/openproject/LessCode/wiki)

License
-------
```
Copyright (C) 2015 MarkMjw

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```