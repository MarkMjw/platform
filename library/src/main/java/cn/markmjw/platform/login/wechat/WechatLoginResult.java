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

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The wechat authorize result entity.
 *
 * @author markmjw
 * @since 1.0.0
 */
class WechatLoginResult implements Parcelable {
    String accessToken;
    long expiresIn;
    String refreshToken;
    String openid;
    String scope;
    String unionid;
    int errcode;
    String errmsg;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accessToken);
        dest.writeLong(this.expiresIn);
        dest.writeString(this.refreshToken);
        dest.writeString(this.openid);
        dest.writeString(this.scope);
        dest.writeString(this.unionid);
        dest.writeInt(this.errcode);
        dest.writeString(this.errmsg);
    }

    public WechatLoginResult() {}

    protected WechatLoginResult(Parcel in) {
        this.accessToken = in.readString();
        this.expiresIn = in.readLong();
        this.refreshToken = in.readString();
        this.openid = in.readString();
        this.scope = in.readString();
        this.unionid = in.readString();
        this.errcode = in.readInt();
        this.errmsg = in.readString();
    }

    public static final Parcelable.Creator<WechatLoginResult> CREATOR = new Parcelable
            .Creator<WechatLoginResult>() {
        public WechatLoginResult createFromParcel(Parcel source) {return new WechatLoginResult
                (source);}

        public WechatLoginResult[] newArray(int size) {return new WechatLoginResult[size];}
    };

    @Override
    public String toString() {
        return "WechatLoginResult{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
