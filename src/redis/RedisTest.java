package redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("192.168.159.128");
		jedis.auth("redis0524");
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
		jedis.set("name", "x");
		System.out.println(jedis.get("name"));
		try {
			List<Data> noticeList = new ArrayList<Data>();
			Data data = new Data();
			data.setAge(0);
			data.setName("name");
			noticeList.add(data);
			
			if (noticeList.size() > 0 && noticeList != null) {
				ListTranscoder<Data> listTranscoder = new ListTranscoder<Data>();
				jedis.set("1".getBytes(),listTranscoder.serialize(noticeList));
			}
		} catch (Exception e) {
			// 如果缓存连不上，则不处理
			System.out.println("登录无法更新该用户缓存");
		}
	}
}
