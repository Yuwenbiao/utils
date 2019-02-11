package com.example.util.jedis操作;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Set;

/**
 * jedis操作方法
 */
@Component
public class JedisListCache {

    private final JedisPool jedisPool;

    @Autowired
    public JedisListCache(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 缓存至有序集合
     *
     * @param key    键
     * @param score  分数值
     * @param member 成员
     * @return 缓存状态
     */
    public Long zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zadd(key, score, member);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 对有序集合进行排序
     *
     * @param key   键
     * @param start 起始
     * @param end   截至
     * @return 有序集合
     */
    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取成员对应的排名
     *
     * @param key    键
     * @param member 成员
     * @return 排名
     */
    public Long zrevrank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrank(key, member);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取成员对应的分数
     *
     * @param key    键
     * @param member 成员
     * @return 分数
     */
    public Double zscore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zscore(key, member);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取有序集合的成员数
     *
     * @param key 键
     * @return 成员数
     */
    public Long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            throw new JedisException(e.getMessage());
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
}
