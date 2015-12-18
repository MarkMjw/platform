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

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.markmjw.platform.util.ImageUtil;

/**
 * Wechat helper.
 *
 * @author markmjw
 * @since 2014-09-28
 */
public class WechatHelper {
    public static final int TYPE_WECHAT_FRIEND = 0;
    public static final int TYPE_WECHAT_TIMELINE = 1;

    /** Min supported version. */
    private static final int MIN_SUPPORTED_VERSION = 0x21020001;

    private static final int MAX_IMAGE_LENGTH = 32 * 1024;
    private static final int DEFAULT_MAX_SIZE = 150;

    private static WechatHelper sInstance;

    private IWXAPI mApi;

    private WechatHelper(Context context) {
        mApi = WXAPIFactory.createWXAPI(context.getApplicationContext(),
                PlatformConfig.getInstance().getWechatId());
        mApi.registerApp(PlatformConfig.getInstance().getWechatId());
    }

    public synchronized static WechatHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WechatHelper(context);
        }
        return sInstance;
    }

    /**
     * 微信是否已安装
     *
     * @return
     */
    public boolean isInstalled() {
        return mApi.isWXAppInstalled();
    }

    /**
     * 是否支持发送朋友圈
     *
     * @return
     */
    public boolean isSupported() {
        return mApi.isWXAppSupportAPI();
    }

    /**
     * 是否支持发送朋友圈
     *
     * @return
     */
    public boolean isSupportedTimeline() {
        return mApi.getWXAppSupportAPI() >= MIN_SUPPORTED_VERSION;
    }

    /**
     * 处理分享结果
     *
     * @param intent
     * @param handler
     */
    public void handleResponse(Intent intent, IWXAPIEventHandler handler) {
        mApi.handleIntent(intent, handler);
    }

    /**
     * 获取微信API
     *
     * @return
     */
    public IWXAPI getAPI() {
        return mApi;
    }

    /**
     * 分享给微信朋友
     *
     * @param title
     * @param des
     * @param url
     * @param image
     */
    public void sendFriend(String title, String des, String url, Bitmap image) {
        WXWebpageObject page = new WXWebpageObject();
        page.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(page);
        msg.title = title;
        msg.description = des;
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = ImageUtil.bitmapToBytes(image, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 用分享的时间来标识唯一的请求
        req.transaction = TYPE_WECHAT_FRIEND + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        mApi.sendReq(req);
    }

    /**
     * 分享给微信朋友
     *
     * @param title
     * @param des
     * @param url
     * @param image
     */
    public void sendFriend(String title, String des, String url, byte[] image) {
        WXWebpageObject page = new WXWebpageObject();
        page.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(page);
        msg.title = title;
        msg.description = des;
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = image;
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 用分享的时间来标识唯一的请求
        req.transaction = TYPE_WECHAT_FRIEND + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        mApi.sendReq(req);
    }

    /**
     * 分享给微信朋友
     *
     * @param filePath
     * @param image
     */
    public void sendFriend(String filePath, byte[] image) {
        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(filePath);

        final WXMediaMessage msg = new WXMediaMessage();
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = image;
        }
        msg.mediaObject = imgObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = TYPE_WECHAT_FRIEND + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        mApi.sendReq(req);
    }

    /**
     * 分享给微信朋友
     *
     * @param filePath
     * @param image
     */
    public void sendTimeLine(String filePath, byte[] image) {
        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(filePath);

        final WXMediaMessage msg = new WXMediaMessage();
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = image;
        }
        msg.mediaObject = imgObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = TYPE_WECHAT_TIMELINE + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        mApi.sendReq(req);
    }

    /**
     * 分享到微信朋友圈
     *
     * @param title
     * @param des
     * @param url
     * @param image
     */
    public void sendTimeLine(String title, String des, String url, Bitmap image) {
        WXWebpageObject page = new WXWebpageObject();
        page.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(page);
        msg.title = title;
        msg.description = des;
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = ImageUtil.bitmapToBytes(image, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 用分享的时间来标识唯一的请求
        req.transaction = TYPE_WECHAT_TIMELINE + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        mApi.sendReq(req);
    }

    /**
     * 分享到微信朋友圈
     *
     * @param title
     * @param des
     * @param url
     * @param image
     */
    public void sendTimeLine(String title, String des, String url, byte[] image) {
        WXWebpageObject page = new WXWebpageObject();
        page.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(page);
        msg.title = title;
        msg.description = des;
        if (null != image) {
            // 缩略图的二进制数据
            msg.thumbData = image;
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 用分享的时间来标识唯一的请求
        req.transaction = TYPE_WECHAT_TIMELINE + String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        mApi.sendReq(req);
    }

    /**
     * 根据微信的要求缩放缩略图
     *
     * @param bitmap
     * @return
     */
    public Bitmap zoomOut(Bitmap bitmap) {
        Bitmap dstBitmap = null;
        if (null != bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            if (width <= 0 || height <= 0) return null;

            int w, h;
            float scale = height * 1.0f / width;
            if (width > height) {
                w = DEFAULT_MAX_SIZE;
                h = (int) (w * scale);
            } else {
                h = DEFAULT_MAX_SIZE;
                w = (int) (h / scale);
            }

            dstBitmap = ImageUtil.zoom(bitmap, w, h);
            byte[] data = ImageUtil.bitmapToBytes(dstBitmap, false);

            while (data.length > MAX_IMAGE_LENGTH) {
                dstBitmap.recycle();

                w -= 10;
                h = (int) (w * scale);

                dstBitmap = ImageUtil.zoom(bitmap, w, h);
                data = ImageUtil.bitmapToBytes(dstBitmap, false);
            }
        }

        return dstBitmap;
    }
}
