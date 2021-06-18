package com.paladin.currencyconverter.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class APIConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(){
        CacheConfiguration config = new CacheConfiguration();
        config.setName("currency-converter");
        config.setMaxEntriesLocalHeap(500);
        config.setTimeToIdleSeconds(60*60*24);

        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.addCache(config);
        return net.sf.ehcache.CacheManager.newInstance(configuration);
     }


}
