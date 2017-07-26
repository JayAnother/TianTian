package jay.love.tiantian.ui.b.model;

import java.io.Serializable;
import java.util.List;

public class TLMessageEntity implements Serializable {

    private int type;
    private long time;
    private int code;
    private String text;
    private String url;
    private List<TLNewsEntity> list;

    public TLMessageEntity() {
    }

    public TLMessageEntity(int type, long time) {
        this.type = type;
        this.time = time;
        this.text = text;
    }

    public TLMessageEntity(int type, long time, String text) {
        this.type = type;
        this.time = time;
        this.text = text;
    }

    public List<TLNewsEntity> getList() {
        return list;
    }

    public void setList(List<TLNewsEntity> list) {
        this.list = list;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "type=" + type +
                ", time=" + time +
                ", code=" + code +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", list=" + list +
                '}';
    }
}
