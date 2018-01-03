package webmagic;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

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
		if (url.indexOf(".html") != -1) {
			String title = page.getHtml().$("title").replace("<title>", "").replace("</title>", "").toString();
			String countent = page.getHtml().$("#content").toString();
			countent = countent.replaceAll("&nbsp;", "");
			countent = countent.replaceAll("<br>", "");
			countent = countent.replaceAll("</div>", "");
			countent = countent.replaceAll("<div id=\"content\">", "");
			System.out.println(title);
			System.out.println(countent);
		} else {
			String ml = page.getHtml().$("dl").toString();
			String[] ss = ml.split("a");
			
			for (int i = 0; i < ss.length; i++) {//ss.length
				List<String> results = new ArrayList<String>();
				String one = ss[i];
				int startIndex = one.indexOf("\"");
				int endIndex = one.indexOf(".");
				if (startIndex > 0) {
					String result = one.substring(startIndex + 1, endIndex);
					System.out.println(result);
					results.add("http://www.biquke.com/bq/3/3714/" + result
							+ ".html");
				}
				page.addTargetRequests(results);
			}
			
		}

	}

	public static void main(String[] args) {

		Spider.create(new GithubRepoPageProcessor())
		//http://www.biquke.com/bq/3/3714
				.addUrl("http://www.biquke.com/bq/3/3714/3831251.html")
				// 开启5个线程抓取
				.thread(5)
				// 启动爬虫
				.run();
	}
}
