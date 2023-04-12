package Database;

public class Question {
    private long _Idquestion;
    private String _details,_choice;
    private int _score;

    public Question(long _Idquestion, String _details, String _choice, int _score) {
        this._Idquestion = _Idquestion;
        this._details = _details;
        this._choice = _choice;
        this._score = _score;
    }

    public long get_Idquestion() {
        return _Idquestion;
    }

    public void set_Idquestion(long _Idquestion) {
        this._Idquestion = _Idquestion;
    }

    public String get_details() {
        return _details;
    }

    public void set_details(String _details) {
        this._details = _details;
    }

    public String get_choice() {
        return _choice;
    }

    public void set_choice(String _choice) {
        this._choice = _choice;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }
}
