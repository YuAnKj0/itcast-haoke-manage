package cn.itcast.haoke.dubbo.apicn.itcast.haoke.dubbo.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

public class TestRedis {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Test
    public void testSave(){
//        for (int i = 0; i < 100; i++) {
//            this.redisTemplate.opsForValue().set("key_" + i, "value_"+i);
//        }
//        Set<String> keys = this.redisTemplate.keys("key_*");
//        for (String key : keys) {
//            String value = this.redisTemplate.opsForValue().get(key);
//            System.out.println(value);
//            //this.redisTemplate.delete(key);
//        }
    }
}
