package org.hhhhhh.house.spider;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hhhhhh.house.hibernate.dao.Daos;
import org.hhhhhh.house.hibernate.dto.HouseDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Com58Downloader implements FangYuanWangDownloader {

	String url = "http://bj.58.com/ershoufang/0/?utm_source=d_ztsfc&utm_campaign=ztsfc-101&spm=zufang-1010&PGTID=14374020277440.3103229554835707&ClickID=3";

	@Override
	public List<HouseDto> download() {
		String content1 = WebContentFethcer.get("utf-8", url);
		List<String> urls = getUrls(content1);

		ArrayList<HouseDto> ls = Lists.newArrayList();
		for (String url : urls) {
			if (isAreadyHave(url)) {
				break;
			}
			HouseDto dto;
			try {
				dto = getHouseData(url);
				ls.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		HashMap<String, HouseDto> map = Maps.newHashMap();
		
		for (HouseDto dto : ls) {
			map.put(dto.getHref(), dto);
		}
		
		ls.clear();
		ls.addAll(map.values());

		return ls;
	}

	private boolean isAreadyHave(String url2) {
		return Daos.getHouseDao().get(url2) != null;
	}

	private HouseDto getHouseData(String url) {

		// Log.d("fetch house data", url);
		String houseInfo = WebContentFethcer.get("utf-8", url);
		HouseDto dto = new HouseDto();
		Document doc = Jsoup.parse(houseInfo);

//		try {
//			System.setOut(new PrintStream("d:/temp.xml"));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}

		Elements divs = doc.getElementsByAttributeValue("class",
				"col_sub sumary ");
		Element div = divs.get(0);

		String update_date = Util.Time.format(System.currentTimeMillis());
		String name = getName(doc);
		dto.setName(name);
		dto.setCommit_date(update_date);
		dto.setUpdate_date(update_date);
		dto.setHref(url);
		setTel(dto, div);
		Elements dls = div.getElementsByTag("li");
		setAttributes(dto, dls);

		Log.d("fetch 58 house data successful", url);
		return dto;
	}

	private void setTel(HouseDto dto, Element div) {
		Element ee = div.getElementById("t_phone");
		String text = ee.html();

		Pattern c = Pattern.compile("img src='http:.+'");
		Matcher m = c.matcher(text);
		boolean find = m.find();
		if (find) {
			String group = m.group();

			group = group.replaceAll("img src=", "");
			group = group.replaceAll("'", "");
			group = group.trim();
			dto.setTel(group);
		} else
			dto.setTel("");
	}

	private String getName(Document doc) {
		Elements e = doc.getElementsByAttributeValue("class", "bigtit_span");
		String text = e.text();
		text = text.replaceAll("&nbsp;", "");
		text = text.trim();
		return text;
	}

	private void setAttributes(HouseDto dto, Elements dls) {
		for (Element e : dls) {
			Elements kv = e.getElementsByTag("div");
			if (kv.size() != 2) {
				continue;
			}
			Element dt = kv.get(0);
			Element dd = kv.get(1);

			String key = dt.text().trim();
			String value = dd.text().trim();

			if (key.contains("售价")) {
				Pattern c = Pattern.compile("\\([0-9\\.]+元/㎡\\)");
				Matcher m = c.matcher(value);
				boolean find = m.find();
				if (find) {
					String group = m.group();

					group = group.replaceAll("元/㎡", "");
					group = group.replaceAll("\\(", "");
					group = group.replaceAll("\\)", "");
					group = group.trim();
					dto.setPrice(group);
				} else
					dto.setPrice("0");
			} else if (key.contains("户型")) {
				dto.setHuxing(value);
			} else if (key.contains("位置")) {
				dto.setZoneName(value);
			} else if (key.contains("地址")) {
				dto.setPlate(value);
			} else if (key.contains("联系人")) {
				dto.setOwner(value);
			}
		}
	}

	static Pattern compile = Pattern
			.compile("[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}\\　[0-9]{2}:[0-9]{2}");

	private Element getElementsByTag(Element e, String name) {
		Elements es = e.getElementsByTag(name);
		if (es.isEmpty())
			return null;
		return es.get(0);
	}

	private List<String> getUrls(String content) {
		Document doc = Jsoup.parse(content);
		// try {
		// System.setOut(new PrintStream("d:/temp.xml"));
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// }
		// Log.d(doc.html());
		Element table = getTable(doc);
		Elements tds = table.getElementsByTag("td");
		ArrayList<String> ls = Lists.newArrayList();
		for (Element td : tds) {
			Elements as = td.getElementsByTag("a");
			if (as.size() != 0) {
				Element a = as.get(0);
				String href = a.attr("href");
				ls.add(href);
			}
		}
		return ls;
	}

	private Element getTable(Document doc) {

		Elements es = doc.getElementsByTag("table");
		for (Element element : es) {
			String attr = element.attr("class");
			if ("tbimg".equals(attr)) {
				return element;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Com58Downloader d = new Com58Downloader();
		List<HouseDto> download = d.download();
		Daos.getHouseDao().save(download);
	}
}
