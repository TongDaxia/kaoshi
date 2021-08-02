package com.kaoshi.tyg.permission.annotation;

public enum FromPermission {

    SAVE,
    NOT_SAVE;

    private FromPermission() {
    }



    private static final String save = "in";

    public static FromPermission trans(String from) {
        if (save.equals(from)) {
            return SAVE;
        }
        return NOT_SAVE;
    }
}
