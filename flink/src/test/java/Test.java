import com.alibaba.fastjson.JSON;
import com.longhchen.KafkaUtils;
import com.longhchen.bean.User;

import java.util.Random;

/**
 * Create by longhchen on  2021-03-13 22:48
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void sendData(){
        int cnt = 0;
        while(cnt < 200){
            User user = new User();
            user.setId(cnt);
            user.setUsername("username"+new Random().nextInt((cnt%5)+2));
            user.setPassword("password"+cnt);
            user.setTimestamp(System.currentTimeMillis());

            KafkaUtils.send("topn",String.valueOf(cnt), JSON.toJSONString(user));

            cnt = cnt +1;

        }
    }
}
