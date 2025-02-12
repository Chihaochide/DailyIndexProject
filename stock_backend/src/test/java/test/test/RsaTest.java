package test.test;

import com.daily.stock.pojo.entity.SysUser;
import com.daily.stock.utils.JwtUtils;
import com.daily.stock.utils.Payload;
import com.daily.stock.utils.RsaUtils;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * RasUtil用来生成公钥和私钥
 * Jwt工具类用来生成token
 */
public class RsaTest {

    public static  final String publicKeyPath = "E:\\DailyIndexProject\\rsa-key\\rsa-key.pub";
    public static  final String privateKeyPath = "E:\\DailyIndexProject\\rsa-key\\rsa-key";

    @Test
    public void testGenericRsa() throws Exception{

        RsaUtils.generateKey(publicKeyPath,privateKeyPath,"coffe",1024);
    }

    @Test
    public void getKey() throws Exception {
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        System.out.println("publicKey = " + publicKey);

        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        System.out.println("privateKey = " + privateKey);
    }

    /**
     * 生成token
     */
    @Test
    public void creatToken() throws Exception{
        LoginRespVo loginRespVo = LoginRespVo.builder().username("Ax").id(1113L).phone("177").nickName("Ax").build();
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        String token = JwtUtils.generateTokenExpireInMinutes(loginRespVo, privateKey, 10);
        System.out.println("token = " + token);
    }
    /**
     * 验证token
     */
    @Test
    public void checkToken() throws Exception{
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoie1wiaWRcIjpcIjExMTNcIixcInBob25lXCI6XCIxNzdcIixcInVzZXJuYW1lXCI6XCJBeFwiLFwibmlja05hbWVcIjpcIkF4XCJ9IiwianRpIjoiWlRjd05XSm1NV1l0TnpkbE55MDBOVEl6TFRoa01XUXRabVJoWmpVNU1USTVNREl6IiwiZXhwIjoxNzM5MzM0MDAwfQ.KuhGIrKf_hQkt6Z3N-e02IzWRCWAW_VuTvKk3qrfSU3KGQ7NLBpfhTpGrXc83hQBv844OfVBgLCANIcWHvoH8z2CURtJWcrFW7yoQVH_r366VMJoQOgUf5eMCbZxHAHjw2BdPN-LytKouBGcMYclOr_zeHHQMd4RgQ4snbQ9iUYFVW4JQ0B7d1_sx6Wk2SxM3dRr5G7RgXmNadn8XwHFK9inOUijqxb8jkgLal1GJjGXobea4i083CcrXlkzzO3W9GAWv0jw3CwNbycUUc1ODOWAsKZhQdqjXG6uQyLC1_W1WpyesNflJuDZv-Dp99mN8cMKtno4Dkepw4MLwfmqVw";
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        Payload<LoginRespVo> payload = JwtUtils.getInfoFromToken(token, publicKey, LoginRespVo.class);
        LoginRespVo info = payload.getInfo();
        System.out.println("info = " + info);
    }


}
