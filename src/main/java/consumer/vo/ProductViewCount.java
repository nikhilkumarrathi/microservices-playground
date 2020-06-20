package consumer.vo;

import io.micronaut.core.annotation.Introspected;

import java.util.Date;

@Introspected
public class ProductViewCount {
    private String name;
    private Date time;
    private long views;

    public ProductViewCount(String name, Date time, long views) {
        this.name = name;
        this.time = time;
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "ProductViewCount{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", count=" + views +
                '}';
    }
}
