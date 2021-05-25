package com.kaoshi.tyg.aspectj;

import com.kaoshi.tyg.aspectj.annotation.FromPermission;
import com.kaoshi.tyg.aspectj.annotation.UserPermission;

public class PermissionData {

    FromPermission from;
    UserPermission user;

    public PermissionData(FromPermission from, UserPermission user) {
        this.from = from;
        this.user = user;
    }

    public PermissionData(FromPermission from, Boolean session, Boolean token) {
        this.from = from;
        if (session) {
            this.user = UserPermission.SESSION;
        } else if (token) {
            this.user = UserPermission.TOKEN;
        } else {
            this.user = UserPermission.NOTMATCH;
        }
        this.user = user;
    }

    public FromPermission getFrom() {
        return from;
    }

    public void setFrom(FromPermission from) {
        this.from = from;
    }

    public UserPermission getUser() {
        return user;
    }

    public void setUser(UserPermission user) {
        this.user = user;
    }
}
