package edu.huflit.learningenglishapp;

public class Exam {
    private long _Idquestion, _IDTest;

    public Exam(long _Idquestion, long _IDTest) {
        this._Idquestion = _Idquestion;
        this._IDTest = _IDTest;
    }

    public long get_Idquestion() {
        return _Idquestion;
    }

    public void set_Idquestion(long _Idquestion) {
        this._Idquestion = _Idquestion;
    }

    public long get_IDTest() {
        return _IDTest;
    }

    public void set_IDTest(long _IDTest) {
        this._IDTest = _IDTest;
    }
}
