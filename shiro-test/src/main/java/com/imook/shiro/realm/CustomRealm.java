package com.imook.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<String, String>( 16);
    {
        userMap.put("Leo","e10adc3949ba59abbe56e057f20f883e");
        super.setName("customRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从传过来的认证信息中,获取用户名
        String userName = (String) token.getPrincipal();

        //2.通过用户名贵到数据库获取凭证
       String password =  getPasswordByUserName(userName);
        if (password == null) {
            return  null;
        }


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("Leo",password,"customRealm");


        return simpleAuthenticationInfo;

    }




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();

        //从数据缓存中获取个人角色数据
        Set<String> roles = getRolesByUserName(userName);

        Set<String> permissions = getPermissionByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {
        HashSet<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;


    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> set = new HashSet<String>();
        set.add("admin");
        set.add("user");
        return  set;
    }

    /**
     * 模拟用户查询凭证
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {

        return userMap.get(userName);
    }
}
