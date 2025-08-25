package com.example.config;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;
import java.util.concurrent.TimeUnit;
@Configuration
@EnableCaching
public class CacheConfig {
  @Bean
  public Caffeine<Object,Object> caffeineSpec(){
    return Caffeine.newBuilder().maximumSize(10_000).expireAfterWrite(30, TimeUnit.SECONDS);
  }
  @Bean
  public CacheManager cacheManager(Caffeine<Object,Object> caffeine){
    CaffeineCacheManager cm = new CaffeineCacheManager("orders");
    cm.setCaffeine(caffeine);
    return cm;
  }
}