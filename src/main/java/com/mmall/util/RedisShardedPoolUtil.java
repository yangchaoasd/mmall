package com.mmall.util;

import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

/**
 * @author: chenkaixin
 * @createTime: 2019-01-09 19:56
 **/
@Slf4j
public class RedisShardedPoolUtil {

    public static Long expire(String key, int exTime) {

        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }

    public static String set(String key, String value) {

        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }

    // exTime的单位是秒
    public static String setEx(String key, String value, int exTime) {

        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} exTime:{} error", key, value, exTime, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }

    public static Long setNx(String key, String value) {

        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }

    public static String get(String key) {

        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }

    public static String getSet(String key, String value) {

        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getSet key:{} value:{} error", key, value, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }


    public static Long del(String key) {

        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.close(jedis);
            return result;
        }
        RedisShardedPool.close(jedis);
        return result;
    }
}
