package model;

import com.sun.istack.internal.Nullable;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class SocketResult<T> {
    public int code;
    public String msg;
    @Nullable
    public T data;

    public SocketResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SocketResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
