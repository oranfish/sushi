package com.oranfish.sushiutil.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisUtil {

    private volatile static JedisPool pool;

    public static final String REDIS_MAX = "redis.max";
    public static final String REDIS_IDLE = "redis.idle";
    public static final String REDIS_MAX_WAIT = "redis.maxWait";
    public static final String REDIS_SERVERS = "redis.ip";
    public static final String REDIS_SOCKET_TIME_OUT = "redis.socket.timeout";
    public static final String REDIS_PASSWORD = "redis.password";

    private RedisUtil() {
    }

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(ConfigUtil.getProperty(REDIS_MAX,"100")));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(ConfigUtil.getProperty(REDIS_IDLE, "30")));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(ConfigUtil.getProperty(REDIS_MAX_WAIT, "200")));
        jedisPoolConfig.setTestOnBorrow(true);

        String host = ConfigUtil.getProperty(REDIS_SERVERS);
        String password = ConfigUtil.getProperty(REDIS_PASSWORD, "");
        if(StringUtils.isEmpty(password)){
            password = null;
        }
        pool = new JedisPool(jedisPoolConfig, host, Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, password, Protocol.DEFAULT_DATABASE);
    }



    public static void put(String k, Object v, long timeout){
        Jedis jedis = pool.getResource();
        try{
            String code = jedis.psetex(k, timeout, JSON.toJSONString(v));
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public static <T> T get(String k, Class<T> clazz){
        Jedis jedis = pool.getResource();
        T t = null;
        try{
            String value = jedis.get(k);
            if(value!=null){
                t = JSON.parseObject(value, clazz);
            }
            return t;
        }finally{
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    public static void main(String[] args){
        System.out.println(RedisUtil.get("1", String.class));
    }
}
