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

package cn.markmjw.platform.login.qq;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The qq user entity.
 *
 * @author markmjw
 * @since 2015-08-16
 */
public class QQUserInfo implements Parcelable {
    public String is_yellow_year_vip;
    public int ret;
    public String figureurl_qq_1;
    public String figureurl_qq_2;
    public String nickname;
    public String yellow_vip_level;
    public int is_lost;
    public String msg;
    public String city;
    public String figureurl_1;
    public String vip;
    public String level;
    public String figureurl_2;
    public String province;
    public String gender;
    public String is_yellow_vip;
    public String figureurl;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.is_yellow_year_vip);
        dest.writeInt(this.ret);
        dest.writeString(this.figureurl_qq_1);
        dest.writeString(this.figureurl_qq_2);
        dest.writeString(this.nickname);
        dest.writeString(this.yellow_vip_level);
        dest.writeInt(this.is_lost);
        dest.writeString(this.msg);
        dest.writeString(this.city);
        dest.writeString(this.figureurl_1);
        dest.writeString(this.vip);
        dest.writeString(this.level);
        dest.writeString(this.figureurl_2);
        dest.writeString(this.province);
        dest.writeString(this.gender);
        dest.writeString(this.is_yellow_vip);
        dest.writeString(this.figureurl);
    }

    public QQUserInfo() {}

    protected QQUserInfo(Parcel in) {
        this.is_yellow_year_vip = in.readString();
        this.ret = in.readInt();
        this.figureurl_qq_1 = in.readString();
        this.figureurl_qq_2 = in.readString();
        this.nickname = in.readString();
        this.yellow_vip_level = in.readString();
        this.is_lost = in.readInt();
        this.msg = in.readString();
        this.city = in.readString();
        this.figureurl_1 = in.readString();
        this.vip = in.readString();
        this.level = in.readString();
        this.figureurl_2 = in.readString();
        this.province = in.readString();
        this.gender = in.readString();
        this.is_yellow_vip = in.readString();
        this.figureurl = in.readString();
    }

    public static final Parcelable.Creator<QQUserInfo> CREATOR = new Parcelable.Creator<QQUserInfo>() {
        public QQUserInfo createFromParcel(Parcel source) {return new QQUserInfo(source);}

        public QQUserInfo[] newArray(int size) {return new QQUserInfo[size];}
    };

    @Override
    public String toString() {
        return "QQUserInfo{" +
                "is_yellow_year_vip='" + is_yellow_year_vip + '\'' +
                ", ret=" + ret +
                ", figureurl_qq_1='" + figureurl_qq_1 + '\'' +
                ", figureurl_qq_2='" + figureurl_qq_2 + '\'' +
                ", nickname='" + nickname + '\'' +
                ", yellow_vip_level='" + yellow_vip_level + '\'' +
                ", is_lost=" + is_lost +
                ", msg='" + msg + '\'' +
                ", city='" + city + '\'' +
                ", figureurl_1='" + figureurl_1 + '\'' +
                ", vip='" + vip + '\'' +
                ", level='" + level + '\'' +
                ", figureurl_2='" + figureurl_2 + '\'' +
                ", province='" + province + '\'' +
                ", gender='" + gender + '\'' +
                ", is_yellow_vip='" + is_yellow_vip + '\'' +
                ", figureurl='" + figureurl + '\'' +
                '}';
    }
}
