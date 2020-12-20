package com.example.bookreader;

public interface LoaderListener {
    void onLoaded(String result);
    void onFailure (String error);
}
