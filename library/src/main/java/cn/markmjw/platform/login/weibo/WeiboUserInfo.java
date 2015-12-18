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

package cn.markmjw.platform.login.weibo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The weibo user entity.
 *
 * @author markmjw
 * @since 2014-04-07
 */
public class WeiboUserInfo implements Parcelable {
    public long id;
    public String name;
    public String screen_name;
    public String profile_image_url;
    public String avatar_large;
    public String description;
    public String location;
    public String url;
    public String verified_source_url;
    public int block_app;
    public String remark;
    public int verified_type;
    public String verified_reason;
    public int statuses_count;
    public String lang;
    public String verified_source;
    public int credit_score;
    public String city;
    public String verified_trade;
    public boolean following;
    public int favourites_count;
    public String idstr;
    public boolean verified;
    public String domain;
    public String province;
    public String gender;
    public String created_at;
    public int user_ability;
    public String weihao;
    public int followers_count;
    public int online_status;
    public String profile_url;
    public int bi_followers_count;
    public boolean geo_enabled;
    public int star;
    public int urank;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.screen_name);
        dest.writeString(this.profile_image_url);
        dest.writeString(this.avatar_large);
        dest.writeString(this.description);
        dest.writeString(this.location);
        dest.writeString(this.url);
        dest.writeString(this.verified_source_url);
        dest.writeInt(this.block_app);
        dest.writeString(this.remark);
        dest.writeInt(this.verified_type);
        dest.writeString(this.verified_reason);
        dest.writeInt(this.statuses_count);
        dest.writeString(this.lang);
        dest.writeString(this.verified_source);
        dest.writeInt(this.credit_score);
        dest.writeString(this.city);
        dest.writeString(this.verified_trade);
        dest.writeByte(following ? (byte) 1 : (byte) 0);
        dest.writeInt(this.favourites_count);
        dest.writeString(this.idstr);
        dest.writeByte(verified ? (byte) 1 : (byte) 0);
        dest.writeString(this.domain);
        dest.writeString(this.province);
        dest.writeString(this.gender);
        dest.writeString(this.created_at);
        dest.writeInt(this.user_ability);
        dest.writeString(this.weihao);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.online_status);
        dest.writeString(this.profile_url);
        dest.writeInt(this.bi_followers_count);
        dest.writeByte(geo_enabled ? (byte) 1 : (byte) 0);
        dest.writeInt(this.star);
        dest.writeInt(this.urank);
    }

    public WeiboUserInfo() {}

    protected WeiboUserInfo(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.screen_name = in.readString();
        this.profile_image_url = in.readString();
        this.avatar_large = in.readString();
        this.description = in.readString();
        this.location = in.readString();
        this.url = in.readString();
        this.verified_source_url = in.readString();
        this.block_app = in.readInt();
        this.remark = in.readString();
        this.verified_type = in.readInt();
        this.verified_reason = in.readString();
        this.statuses_count = in.readInt();
        this.lang = in.readString();
        this.verified_source = in.readString();
        this.credit_score = in.readInt();
        this.city = in.readString();
        this.verified_trade = in.readString();
        this.following = in.readByte() != 0;
        this.favourites_count = in.readInt();
        this.idstr = in.readString();
        this.verified = in.readByte() != 0;
        this.domain = in.readString();
        this.province = in.readString();
        this.gender = in.readString();
        this.created_at = in.readString();
        this.user_ability = in.readInt();
        this.weihao = in.readString();
        this.followers_count = in.readInt();
        this.online_status = in.readInt();
        this.profile_url = in.readString();
        this.bi_followers_count = in.readInt();
        this.geo_enabled = in.readByte() != 0;
        this.star = in.readInt();
        this.urank = in.readInt();
    }

    public static final Creator<WeiboUserInfo> CREATOR = new Creator<WeiboUserInfo>() {
        public WeiboUserInfo createFromParcel(Parcel source) {return new WeiboUserInfo(source);}

        public WeiboUserInfo[] newArray(int size) {return new WeiboUserInfo[size];}
    };

    @Override
    public String toString() {
        return "WeiboUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", avatar_large='" + avatar_large + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", url='" + url + '\'' +
                ", verified_source_url='" + verified_source_url + '\'' +
                ", block_app=" + block_app +
                ", remark='" + remark + '\'' +
                ", verified_type=" + verified_type +
                ", verified_reason='" + verified_reason + '\'' +
                ", statuses_count=" + statuses_count +
                ", lang='" + lang + '\'' +
                ", verified_source='" + verified_source + '\'' +
                ", credit_score=" + credit_score +
                ", city='" + city + '\'' +
                ", verified_trade='" + verified_trade + '\'' +
                ", following=" + following +
                ", favourites_count=" + favourites_count +
                ", idstr='" + idstr + '\'' +
                ", verified=" + verified +
                ", domain='" + domain + '\'' +
                ", province='" + province + '\'' +
                ", gender='" + gender + '\'' +
                ", created_at='" + created_at + '\'' +
                ", user_ability=" + user_ability +
                ", weihao='" + weihao + '\'' +
                ", followers_count=" + followers_count +
                ", online_status=" + online_status +
                ", profile_url='" + profile_url + '\'' +
                ", bi_followers_count=" + bi_followers_count +
                ", geo_enabled=" + geo_enabled +
                ", star=" + star +
                ", urank=" + urank +
                '}';
    }
}