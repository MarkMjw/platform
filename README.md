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

* Login with Weibo
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

* Login with Wechat
```java
WechatLoginHandler handler = new WechatLoginHandler();
handler.setLogEnable(true);
handler.setRequestUserInfo(true);
handler.login(new LoginListener());
```

* Login with QQ
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

* Share with Weibo
```java
WeiboHelper.getInstance(context).sendMessage(context, "text", "imagePath");
```

* Share with Wechat
```java
// share to friend
WechatHelper.getInstance(context).sendFriend("title", "description", "shareUrl", thumbnail);

// share to timeline
WechatHelper.getInstance(context).sendTimeLine("title", "description", "shareUrl", thumbnail);
```
* Share with QQ
```java
// share to qq friend
QQHelper.getInstance(context).shareToQQWithNetworkImage(activity, "title", "description", "shareUrl", "imageUrl");

// share to qzone
QQHelper.getInstance(context).shareToQzoneWithNetWorkImages(activity, "title", "description", "shareUrl", images);
```


See more details on the [sample](https://github.com/MarkMjw/platform/blob/master/sample%2Fsrc%2Fmain%2Fjava%2Fcn%2Fmarkmjw%2Fplatform%2Fdemo%2FMainActivity.java)

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