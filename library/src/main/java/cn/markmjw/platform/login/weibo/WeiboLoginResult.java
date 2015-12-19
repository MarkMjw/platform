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
 * The weibo authorize result entity.
 *
 * @author markmjw
 * @since 1.0.0
 */
class WeiboLoginResult implements Parcelable {
    String uid;
    String access_token;
    String userName;
    String expires_in;
    String remind_in;
    String refresh_token;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.access_token);
        dest.writeString(this.userName);
        dest.writeString(this.expires_in);
        dest.writeString(this.remind_in);
        dest.writeString(this.refresh_token);
    }

    public WeiboLoginResult() {}

    protected WeiboLoginResult(Parcel in) {
        this.uid = in.readString();
        this.access_token = in.readString();
        this.userName = in.readString();
        this.expires_in = in.readString();
        this.remind_in = in.readString();
        this.refresh_token = in.readString();
    }

    public static final Parcelable.Creator<WeiboLoginResult> CREATOR = new Parcelable
            .Creator<WeiboLoginResult>() {
        public WeiboLoginResult createFromParcel(Parcel source) {return new WeiboLoginResult
                (source);}

        public WeiboLoginResult[] newArray(int size) {return new WeiboLoginResult[size];}
    };

    @Override
    public String toString() {
        return "WeiboLoginResult{" +
                "uid='" + uid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", userName='" + userName + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", remind_in='" + remind_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}