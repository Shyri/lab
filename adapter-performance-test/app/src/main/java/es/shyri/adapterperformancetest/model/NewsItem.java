package es.shyri.adapterperformancetest.model;

/**
 * Created by Shyri on 07/10/2015.
 */
public class NewsItem {
    private long id;
    private String title;
    private String subitle;
    private String body;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubitle() {
        return subitle;
    }

    public void setSubitle(String subitle) {
        this.subitle = subitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
