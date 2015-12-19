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

import java.util.List;

/**
 * The wechat user entity.
 *
 * @author markmjw
 * @since 1.0.0
 */
public class WechatUserInfo implements Parcelable {
    public String openid;
    public String nickname;
    public int sex;
    public String province;
    public String city;
    public String country;
    public String headimgurl;
    public List<String> privilege;
    public String unionid;
    public String language;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openid);
        dest.writeString(this.nickname);
        dest.writeInt(this.sex);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.headimgurl);
        dest.writeStringList(this.privilege);
        dest.writeString(this.unionid);
        dest.writeString(this.language);
    }

    public WechatUserInfo() {}

    protected WechatUserInfo(Parcel in) {
        this.openid = in.readString();
        this.nickname = in.readString();
        this.sex = in.readInt();
        this.province = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.headimgurl = in.readString();
        this.privilege = in.createStringArrayList();
        this.unionid = in.readString();
        this.language = in.readString();
    }

    public static final Creator<WechatUserInfo> CREATOR = new Creator<WechatUserInfo>() {
        public WechatUserInfo createFromParcel(Parcel source) {return new WechatUserInfo(source);}

        public WechatUserInfo[] newArray(int size) {return new WechatUserInfo[size];}
    };

    @Override
    public String toString() {
        return "WechatUserInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", privilege=" + privilege +
                ", unionid='" + unionid + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
