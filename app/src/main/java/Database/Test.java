package Database;

public class Test {
    private long _IDTest;
    private String _Description, level;
    private int _amount;

    public Test(long _IDTest, String _Description, String level, int _amount) {
        this._IDTest = _IDTest;
        this._Description = _Description;
        this.level = level;
        this._amount = _amount;
    }

    public long get_IDTest() {
        return _IDTest;
    }

    public void set_IDTest(long _IDTest) {
        this._IDTest = _IDTest;
    }

    public String get_Description() {
        return _Description;
    }

    public void set_Description(String _Description) {
        this._Description = _Description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }
}
