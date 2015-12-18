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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.utils.Utility;

import cn.markmjw.platform.util.ImageUtil;

/**
 * Weibo helper.
 *
 * @author markmjw
 * @date 2015-03-05
 */
public class WeiboHelper {
    private static WeiboHelper sInstance;

    private final IWeiboShareAPI mWeiboShareAPI;

    private WeiboHelper(Context context) {
        // 创建微博分享接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context.getApplicationContext(),
                PlatformConfig.getInstance().getWeiboKey());
        mWeiboShareAPI.registerApp();
    }

    /**
     * 获取实例
     *
     * @return
     */
    public synchronized static WeiboHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WeiboHelper(context);
        }
        return sInstance;
    }

    /**
     * 是否支持此API
     *
     * @return
     */
    public boolean isInstalled() {
        return mWeiboShareAPI.isWeiboAppInstalled();
    }

    /**
     * 处理分享结果
     *
     * @param intent
     * @param response
     */
    public void handleResponse(Intent intent, IWeiboHandler.Response response) {
        mWeiboShareAPI.handleWeiboResponse(intent, response);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 注意：当 {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351
     * 时，支持同时分享多条消息， 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     *
     * @param activity
     * @param text      文本
     * @param imagePath 图片
     */
    public void sendMessage(Activity activity, String text, String imagePath) {
        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (!TextUtils.isEmpty(text)) {
            weiboMessage.textObject = getTextObject(text);
        }
        if (!TextUtils.isEmpty(imagePath)) {
            weiboMessage.imageObject = getImageObject(imagePath);
        }

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest(activity, request);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 注意：当 {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351
     * 时，支持同时分享多条消息， 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     *
     * @param activity
     * @param title
     * @param des
     * @param url
     * @param thumbnail
     */
    private void sendWebMessage(Activity activity, String title, String des, String url, Bitmap
            thumbnail) {
        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.mediaObject = getWebPageObject(title, des, url, thumbnail);

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest(activity, request);
    }

    /**
     * 创建文本消息对象
     *
     * @param content
     * @return 文本消息对象
     */
    private TextObject getTextObject(String content) {
        TextObject text = new TextObject();
        text.text = content;
        return text;
    }

    /**
     * 创建图片消息对象
     *
     * @param imagePath
     * @return 图片消息对象
     */
    private ImageObject getImageObject(String imagePath) {
        ImageObject image = new ImageObject();
        Bitmap bitmap = ImageUtil.getBitmapFromFile(imagePath);
        if (bitmap != null) {
            image.setImageObject(bitmap);
        }
        return image;
    }

    /**
     * 创建多媒体（网页）消息对象
     *
     * @param title
     * @param des
     * @param url
     * @param thumbnail
     * @return
     */
    private WebpageObject getWebPageObject(String title, String des, String url, Bitmap thumbnail) {
        WebpageObject web = new WebpageObject();
        web.identify = Utility.generateGUID();
        web.title = title;
        web.description = des;

        web.setThumbImage(thumbnail);
        web.actionUrl = url;
        web.defaultText = des;
        return web;
    }
}
