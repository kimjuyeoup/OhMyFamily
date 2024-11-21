package com.example.demo.global.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RedisUtil {

  private final StringRedisTemplate stringRedisTemplate;
}
