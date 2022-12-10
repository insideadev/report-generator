package com.dev.reportgenerator.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class EhCacheConfig {

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration tenSecondCache = new CacheConfiguration();
        tenSecondCache.setName("listDaily");
        tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
        tenSecondCache.setMaxEntriesLocalHeap(1000);
        tenSecondCache.setTimeToLiveSeconds(100);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(tenSecondCache);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }



}
