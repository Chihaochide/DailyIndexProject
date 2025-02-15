package test.test;

import com.dailyindex.stock.BackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = BackendApplication.class)
public class RedisJacksonTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test1(){
        // 存入值
        redisTemplate.opsForValue().set("myname","Ax");
        // 获取值
        String myname = redisTemplate.opsForValue().get("myname");
        System.out.println("myname = " + myname);
    }
}
