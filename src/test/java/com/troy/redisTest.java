package com.troy;

import redis.clients.jedis.Jedis;

/**
 * Created by 12546 on 2017/4/22.
 */
public class redisTest {

    public static void main(String args[]){
        Jedis test = new Jedis("139.196.94.27",6379);
        test.set("cpp","hello world");
//        test.del("cpp");
        String hello = test.get("cpp");
        System.out.println(hello);
    }

}
