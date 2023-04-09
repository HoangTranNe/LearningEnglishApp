package edu.huflit.learningenglishapp;

public class Result {
    private long _IDUser,_IDExam;
    private int _tries,_score;

    public Result(long _IDUser, long _IDExam, int _tries, int _score) {
        this._IDUser = _IDUser;
        this._IDExam = _IDExam;
        this._tries = _tries;
        this._score = _score;
    }

    public long get_IDExam() {
        return _IDExam;
    }

    public void set_IDExam(long _IDExam) {
        this._IDExam = _IDExam;
    }

    public int get_tries() {
        return _tries;
    }

    public void set_tries(int _tries) {
        this._tries = _tries;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public long get_IDUser() {
        return _IDUser;
    }

    public void set_IDUser(long _IDUser) {
        this._IDUser = _IDUser;
    }
}
