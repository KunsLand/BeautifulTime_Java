package cn.kunsland;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlController{
	private Document doc;
	private Zone zone;

	public HtmlController() {
		doc = Jsoup.parse("<html><head></head><body><h1></h1></body></html>");
		zone = new Zone();
	}

	public String getContent() {
		String time = TimeHelper.getHourMinuteNumOnly();
		String time2 = TimeHelper.getHourMinute();
		doc.getElementsByTag("body").attr(
				"background",
				"http://www.bijint.com/" + zone.getZone()
						+ "/tokei_images/" + time + ".jpg");
		doc.getElementsByTag("h1").empty().append(time2);
		return doc.toString();
	}
	
	public String getNextContent(){
		String time = TimeHelper.getHourMinuteNumOnly();
		String time2 = TimeHelper.getHourMinute();
		doc.getElementsByTag("body").attr(
				"background",
				"http://www.bijint.com/" + zone.getNextZone()
						+ "/tokei_images/" + time + ".jpg");
		doc.getElementsByTag("h1").empty().append(time2);
		return doc.toString();
	}
	
	public String getRandomContent(){
		String time = TimeHelper.getHourMinuteNumOnly();
		String time2 = TimeHelper.getHourMinute();
		doc.getElementsByTag("body").attr(
				"background",
				"http://www.bijint.com/" + zone.getRandomZone()
						+ "/tokei_images/" + time + ".jpg");
		doc.getElementsByTag("h1").empty().append(time2);
		return doc.toString();
	}
}
