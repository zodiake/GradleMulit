package com.sj.repository.service.Impl;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Test {
	public static void main(String[] args) throws InterruptedException, IOException {
		ClassPathResource c = new ClassPathResource("ehcache.xml");
		System.out.println(c.getFile().toString());
		CacheManager manager = new CacheManager(c.getFile().toString());
		Cache cache = manager.getCache("informationCache");
		while(true){
			for (int i = 0; i < 10; i++) {
				Element e = new Element(Calendar.getInstance().getTime().getTime(), i);
				System.out.println(e.toString());
				cache.put(e);
			}
			System.out.println("cache.getSize()"+cache.getSize());
			Thread.sleep(10000);
		}
	}
}
