package com.example.chickensoup.utils;

public enum TeacherPermitted {
    MODIFY_USER("/user/modify"),
    ;

    private final String url;

    TeacherPermitted(String url) {
        this.url = url;
    }
}
