/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 7:50 pm
 *
 *
 ******************************************************************************/

package net.dotevolve.base.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class AccessRoleMap {

    private static final Map<String, String> roleMap = Maps.newHashMap();

    static {
        roleMap.put("ROLE_USER", "User");
        roleMap.put("ROLE_SUPER_ADMIN", "Super Admin");
        roleMap.put("ROLE_ADMIN", "Admin");

    }

    private AccessRoleMap() {
    }

    public static Set<String> allRoles() {
        return roleMap.keySet();
    }

    public static List<String> allFormattedRoles() {
        return new ArrayList<>(roleMap.values());
    }

    public static String getFormattedRole(String role) {
        return roleMap.get(role);
    }

}
