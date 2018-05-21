package com.imook.test;

import com.imook.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import sun.security.provider.MD5;

public class CustomRealmTest {


    @Test
    public void testAuthenticaton() {

        CustomRealm customRealm = new CustomRealm();
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        //2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Leo", "123456");
        subject.login(token);

        System.out.println("isAuthenticated" + subject.isAuthenticated());

  /*      subject.checkRoles("admin");

        subject.checkPermission("user:delete");

        subject.checkPermission("user:update");*/

    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString());
    }

}
