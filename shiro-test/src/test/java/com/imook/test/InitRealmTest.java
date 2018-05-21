package com.imook.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class InitRealmTest {


    @Test
    public void testAuthenticaton() {
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Leo", "123456");
        subject.login(token);

        System.out.println("isAuthenticated" + subject.isAuthenticated());

        subject.checkRoles("admin");

        subject.checkPermission("user:delete");

        subject.checkPermission("user:update");


    }
}
