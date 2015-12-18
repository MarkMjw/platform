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

/**
 * The login status listener.
 *
 * @author markmjw
 * @since 2015-04-07
 */
public interface ILoginListener {
    /** Success. */
    int CODE_SUCCESS = 0x00;
    /** Failed. */
    int CODE_FAILED = 0x01;
    /** Logining. */
    int CODE_LOGIN_ING = 0x02;
    /** Authorize success. */
    int CODE_AUTH_SUCCESS = 0x03;
    /** Authorize exception. */
    int CODE_AUTH_EXCEPTION = 0x04;
    /** Cancel Authorize. */
    int CODE_CANCEL_AUTH = 0x05;
    /** Authorize failed. */
    int CODE_AUTH_FAILED = 0x06;

    /**
     * login callback
     *
     * @param code
     * @param data
     */
    void loginStatus(int code, Object data);
}
