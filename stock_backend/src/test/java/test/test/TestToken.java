package test.test;

import com.daily.stock.utils.BCrypt;
import com.daily.stock.utils.JwtUtils;
import com.daily.stock.utils.Payload;
import com.daily.stock.utils.RsaUtils;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;

public class TestToken {

    public static  final String publicKeyPath = "E:\\DailyIndexProject\\rsa-key\\rsa-key.pub";

    @Test
    public void TestT() throws Exception{
        String token = "eyJ11hbGciOiJSUzI1NiJ9.eyJ1c2VyIjoie1wiaWRcIjpcIjEyMzczNjE5MTUxNjUwMjAxNjFcIixcInBob25lXCI6XCIxMzg4ODg4ODg4OFwiLFwidXNlcm5hbWVcIjpcImFkbWluXCIsXCJuaWNrTmFtZVwiOlwi6LaF57qn566h55CG5ZGYXCJ9IiwianRpIjoiWkRVek5qZzNaR1l0Tm1Fek9DMDBNR1psTFRnMFpERXROamc1WldFelpHWXhaR1EzIiwiZXhwIjoxNzM5MzY4MjM5fQ.dUzwkZjus2d-UrE6-XvGQVehpybtp86mCz8xa6hVjZBCLiySpZfw7-dyBB4EOZs5EeH7BoPkoc5_KRViSZ0gK0TYJ_OSakS1Jyu8aHjc61aD1M0A66oM7THR2IUh0PWLjOD99gckwg7XtbtbHOnroeS3p1DFWjMvPATeQ80O8pjQFBu-aZKYAajOHr6up76-6r19pGOYVKVbsbZZQUSw67F3dQlKphOQopWBqS8fa_Q1rdX7ReE9meJS1FRJJR0epimloT09Dow-QRvhRPxe5ew6UwoRRE8LdApfBPHzgy9oL3JkyleEtzvQQVGMBoGl8aJM9HEseypuUbxCZGmQ7w";
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        Payload<LoginRespVo> info = JwtUtils.getInfoFromToken(token, publicKey,LoginRespVo.class);
        System.out.println("info = " + info);


    }
}
