package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor_dm implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		String url = page.getUrl().toString();
		String content = page.getHtml().toString();
		System.out.println(content);
		

	}

	public static void main(String[] args) {

		Spider.create(new GithubRepoPageProcessor_dm())
		//http://www.biquke.com/bq/3/3714
				.addUrl("http://www.quanmin.tv/23900134")
				// 开启5个线程抓取
				.thread(5)
				// 启动爬虫
				.run();
	}
}
