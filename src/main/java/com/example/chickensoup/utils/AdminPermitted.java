package com.example.chickensoup.utils;

public enum AdminPermitted {
    MODIFY_USER("/user/modify"),
    ;



    private final String url;

    private AdminPermitted(String url) {
        this.url = url;

    }

}
