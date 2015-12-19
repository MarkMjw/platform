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

package cn.markmjw.platform.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Gson util.
 *
 * @author markmjw
 * @since 1.0.0
 */
public final class GsonUtil {
    private static Gson gson;

    public GsonUtil() {
    }

    /**
     * parse json string with {@link Type}
     *
     * @param json json string
     * @param type the class type
     * @param <T>  the type
     * @return the type
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * parse json string with {@link Class}
     *
     * @param json  json string
     * @param clazz the class type
     * @param <T>   the class type
     * @return the class object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * parse from json {@link Reader}
     *
     * @param json  the Reader
     * @param clazz the class type
     * @param <T>   the class type
     * @return the class object
     */
    public static <T> T fromJson(Reader json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * parse from json byte[]
     *
     * @param bytes the Reader
     * @param clazz the class type
     * @param <T>   the class type
     * @return the class object
     */
    public static <T> T fromJson(byte[] bytes, Class<T> clazz) {
        return gson.fromJson(new String(bytes), clazz);
    }

    /**
     * convert object to json string
     *
     * @param src the object
     * @return json string
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        builder.registerTypeAdapter(Date.class, new JsonDeserializer() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext
                    context) {
                return json == null ? null : new Date(json.getAsLong() * 1000L);
            }
        });
        gson = builder.create();
    }
}