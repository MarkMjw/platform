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

package cn.markmjw.platform.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The common authorize result entity.
 *
 * @author markmjw
 * @date 2015-05-05
 */
public class AuthResult implements Parcelable {
    /** unlogin */
    public static final int TYPE_NONE = 0;
    /** login with weibo */
    public static final int TYPE_WEIBO = 1;
    /** login with wechat */
    public static final int TYPE_WECHAT = 2;
    /** login with qq */
    public static final int TYPE_QQ = 3;

    public int from = TYPE_NONE;
    public String id;
    public String accessToken;
    public long expiresIn;
    public String refreshToken = "";

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.from);
        dest.writeString(this.id);
        dest.writeString(this.accessToken);
        dest.writeLong(this.expiresIn);
        dest.writeString(this.refreshToken);
    }

    public AuthResult() {}

    protected AuthResult(Parcel in) {
        this.from = in.readInt();
        this.id = in.readString();
        this.accessToken = in.readString();
        this.expiresIn = in.readLong();
        this.refreshToken = in.readString();
    }

    public static final Creator<AuthResult> CREATOR = new Creator<AuthResult>() {
        public AuthResult createFromParcel(Parcel source) {return new AuthResult(source);}

        public AuthResult[] newArray(int size) {return new AuthResult[size];}
    };

    @Override
    public String toString() {
        return "AuthResult{" +
                "from=" + from +
                ", id='" + id + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
