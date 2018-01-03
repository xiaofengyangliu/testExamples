package redis;

import java.io.IOException;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedesTestGet {
	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("192.168.159.128");
			byte[] list = jedis.get("1".getBytes());
			ListTranscoder<Data> listTranscoder = new ListTranscoder<Data>();
			List<Data> newList = listTranscoder.deserialize(list);
			System.out.println(newList.get(0).getAge());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
