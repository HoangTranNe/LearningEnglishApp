package Database;

public class Blog {
    private long _IDblog;
    private String _headline,_blogwriting;

    public Blog() {
    }

    public Blog(long _IDblog, String _headline, String _blogwriting) {
        this._IDblog = _IDblog;
        this._headline = _headline;
        this._blogwriting = _blogwriting;
    }

    public long get_IDblog() {
        return _IDblog;
    }

    public void set_IDblog(long _IDblog) {
        this._IDblog = _IDblog;
    }

    public String get_headline() {
        return _headline;
    }

    public void set_headline(String _headline) {
        this._headline = _headline;
    }

    public String get_blogwriting() {
        return _blogwriting;
    }

    public void set_blogwriting(String _blogwriting) {
        this._blogwriting = _blogwriting;
    }

}
