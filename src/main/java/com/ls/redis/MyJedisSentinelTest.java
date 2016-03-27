package com.ls.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class MyJedisSentinelTest {
	
	 public static void main(String[] args) {
	        Set<String> sentinels = new HashSet<String>();
	        sentinels.add(new HostAndPort("localhost", 26379).toString());
	        sentinels.add(new HostAndPort("localhost", 26380).toString());
//	        sentinels.add(new HostAndPort("localhost", 26381).toString());
	        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
	        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
	        Jedis master = sentinelPool.getResource();
	        master.set("username","lishuai");
	        sentinelPool.returnResource(master);
	        Jedis master2 = sentinelPool.getResource();
	        String value = master2.get("username");
	        System.out.println("username: " + value);
	        master2.close();
	        sentinelPool.destroy();
	    }

}
