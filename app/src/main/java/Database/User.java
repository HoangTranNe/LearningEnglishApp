package Database;

public class User {
    private long _IDUser;
    private String _username, _password,_gender, _roleuser,_level;
    private int _age;

    public User() {
    }

    public User(long _IDUser, String _username, String _password, String _gender, String _roleuser, int _age) {
        this._IDUser = _IDUser;
        this._username = _username;
        this._password = _password;
        this._gender = _gender;
        this._roleuser = _roleuser;
        this._age = _age;
    }

    public long get_IDUser() {
        return _IDUser;
    }

    public void set_IDUser(long _IDUser) {
        this._IDUser = _IDUser;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_roleuser() {
        return _roleuser;
    }

    public void set_roleuser(String _roleuser) {
        this._roleuser = _roleuser;
    }

    public String get_level() {
        return _level;
    }

    public void set_level(String _level) {
        this._level = _level;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }
}
