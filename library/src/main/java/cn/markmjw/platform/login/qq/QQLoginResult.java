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
 * The qq authorize result entity.
 *
 * @author markmjw
 * @since 1.0.0
 */
class QQLoginResult implements Parcelable {
    int ret;
    String openid;
    String access_token;
    String pay_token;
    long expires_in;
    String pf;
    String pfkey;
    String msg;
    long login_cost;
    long query_authority_cost;
    long authority_cost;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ret);
        dest.writeString(this.openid);
        dest.writeString(this.access_token);
        dest.writeString(this.pay_token);
        dest.writeLong(this.expires_in);
        dest.writeString(this.pf);
        dest.writeString(this.pfkey);
        dest.writeString(this.msg);
        dest.writeLong(this.login_cost);
        dest.writeLong(this.query_authority_cost);
        dest.writeLong(this.authority_cost);
    }

    public QQLoginResult() {}

    protected QQLoginResult(Parcel in) {
        this.ret = in.readInt();
        this.openid = in.readString();
        this.access_token = in.readString();
        this.pay_token = in.readString();
        this.expires_in = in.readLong();
        this.pf = in.readString();
        this.pfkey = in.readString();
        this.msg = in.readString();
        this.login_cost = in.readLong();
        this.query_authority_cost = in.readLong();
        this.authority_cost = in.readLong();
    }

    public static final Parcelable.Creator<QQLoginResult> CREATOR = new Parcelable
            .Creator<QQLoginResult>() {
        public QQLoginResult createFromParcel(Parcel source) {return new QQLoginResult(source);}

        public QQLoginResult[] newArray(int size) {return new QQLoginResult[size];}
    };

    @Override
    public String toString() {
        return "QQLoginResult{" +
                "ret=" + ret +
                ", openid='" + openid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", pay_token='" + pay_token + '\'' +
                ", expires_in=" + expires_in +
                ", pf='" + pf + '\'' +
                ", pfkey='" + pfkey + '\'' +
                ", msg='" + msg + '\'' +
                ", login_cost=" + login_cost +
                ", query_authority_cost=" + query_authority_cost +
                ", authority_cost=" + authority_cost +
                '}';
    }
}