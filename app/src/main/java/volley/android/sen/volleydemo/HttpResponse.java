package volley.android.sen.volleydemo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shouwang on 16/4/7.
 */
public class HttpResponse<T> implements Serializable {
    public static final int CODE_SUCCESS = 0;

    public int code;
    public String message;

    @SerializedName("timestamp")
    public int timeStamp;

    @SerializedName("metadata")
    public Object metaData;

    public T data;
    public T results;
    public String error;

    public static class MetaData {
        @SerializedName("total_count")
        public int totalCount;
        @SerializedName("last_offset")
        public long lastOffset;
        @SerializedName("current_offset")
        public long currentOffset;
    }

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }
}
