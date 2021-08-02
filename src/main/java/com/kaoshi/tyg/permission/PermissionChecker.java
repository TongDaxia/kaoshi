package com.kaoshi.tyg.permission;

import com.kaoshi.tyg.permission.annotation.FromPermission;
import com.kaoshi.tyg.permission.annotation.Permission;

public class PermissionChecker {


    /**
     * @param now            当前请求携带的权限
     * @param permissionData 接口要求保证的权限
     * @return
     */
    public static Boolean checkPermission(PermissionData now, Permission permissionData) {

        if (now == null) {
            return true;
        }

        Permission.Permit[] value = permissionData.value();
        for (Permission.Permit permit : value) {
            if (permit.USER_PERMISSION().getValue() < now.user.getValue()) {
                return false;
            }
            if (permit.FROM_PERMISSION() == FromPermission.SAVE
                    && now.from == FromPermission.NOT_SAVE) {
                return false;
            }
        }

        return true;
    }
}
