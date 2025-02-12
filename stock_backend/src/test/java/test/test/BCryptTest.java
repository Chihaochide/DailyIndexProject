package test.test;

import com.daily.stock.utils.BCrypt;
import org.junit.jupiter.api.Test;

/**
 * 对用户密码进行加密方式 使用了Bcrypt工具类
 */
public class BCryptTest {

    /**
     * 加密
     */
    @Test
    public void testEncode(){
        String password = "admin";

        // 生成随机盐
        String salt = BCrypt.gensalt();
        // 加密
        String pwd = BCrypt.hashpw(password, salt);
        System.out.println("pwd = " + pwd);
    }

    /**
     * 校验
     */
    @Test
    public void verifyPwd(){
        String pwd = "$2a$10$3mW6m/wlNUQFe4/mstG9HeDaj.oV9NPMpjEiW2d7pDwDpZmcQhH82";
        String password = "admin";
        boolean checkpw = BCrypt.checkpw(password, pwd);
        System.out.println("checkpw = " + checkpw);
    }
}
