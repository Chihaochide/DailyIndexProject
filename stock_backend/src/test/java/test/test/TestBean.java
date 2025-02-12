package test.test;

import com.daily.stock.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBean {

    @Autowired
    private BCrypt bCrypt;

}
