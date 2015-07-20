package org.hhhhhh.house.spider;

import java.util.ArrayList;
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

public class DiYiShiJianDownloader implements FangYuanWangDownloader {

	String url = "http://bj.01fy.cn/sale/";
//	String url1 = "http://bj.01fy.cn/sale/list_2_0_0_0-0_0_0-0_0_2_0_1_.html";
//	String url2 = "http://bj.01fy.cn/sale/list_2_0_0_0-0_0_0-0_0_2_0_2_.html";

	@Override
	public List<HouseDto> download() {
		String content1 = WebContentFethcer.get("utf-8", url);
//		String content1 = WebContentFethcer.get("utf-8", url1);
//		String content2 = WebContentFethcer.get("utf-8", url2);
		List<String> houseUrls1 = getUrls(content1);
//		List<String> houseUrls2 = getUrls(content2);
		
		List<String> urls = Lists.newArrayList();
		urls.addAll(houseUrls1);
//		urls.addAll(houseUrls2);
		
		ArrayList<HouseDto> ls = Lists.newArrayList();
		for (String url : urls) {
			HouseDto dto;
			try {
				dto = getHouseData(url);
				ls.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ls;
	}

	private HouseDto getHouseData(String url) {

		Log.d("获取房源信息" , url);
		String houseInfo = WebContentFethcer.get("utf-8", url);
		HouseDto dto = new HouseDto();
		Document doc = Jsoup.parse(houseInfo);
		Element e = doc.getElementById("content");

		String name = getName(e);
		String commitDate = getCommitDate(e);
		dto.setCommit_date(commitDate);
		dto.setName(name);
		dto.setUpdate_date(Util.Time.format(System.currentTimeMillis()));
		dto.setHref(url);
		Elements dls = e.getElementsByTag("dl");
		setAttributes(dto, dls);
		Log.d("获取成功" , url);
		return dto;
	}

	private void setAttributes(HouseDto dto, Elements dls) {
		for (Element e : dls) {
			Element dt = getElementsByTag(e, "dt");
			Element dd = getElementsByTag(e, "dd");
			String key = dt.text().trim();
			String value = dd.text().trim();

			if (key.contains("出售价格")) {
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
			} else if (key.contains("户型面积")) {
				dto.setHuxing(value);
			} else if (key.contains("小区名称")) {
				dto.setZoneName(value);
			} else if (key.contains("小区地址")) {
				dto.setPlate(value);
			} else if (key.contains("房屋概况")) {
				dto.setDsc(value);
			} else if (key.contains("所处楼层")) {
				dto.setFloor(value);
			} else if (key.contains("联 系 人")) {
				dto.setOwner(value);
			} else if (key.contains("联系电话")) {
				Element ee = getElementsByTag(dd, "div");
				Element img = getElementsByTag(ee, "img");
				if(img != null) {
					dto.setTel(img.attr("src"));
				} else {
					String trim = ee.text().trim();
					dto.setTel(trim);
				}
			}
		}
	}

	static Pattern compile = Pattern
			.compile("[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}\\　[0-9]{2}:[0-9]{2}");

	private String getCommitDate(Element e) {
		Elements divs = e.getElementsByTag("div");
		Element div = divs.get(2);
		Element time = getElementsByTag(div, "p");
		String text = time.text();
		Matcher m = compile.matcher(text);
		if (m.find()) {
			String g = m.group();
			String r = g.replace("　", " ");
			return r + ":00";
		}
		return "1970-01-01 00:00:00";
	}

	private String getName(Element e) {
		Element el = getElementsByTag(e, "h1");
		String text = el.text();
		return text.trim();
	}

	private Element getElementsByTag(Element e, String name) {
		Elements es = e.getElementsByTag(name);
		if (es.isEmpty())
			return null;
		return es.get(0);
	}

	private List<String> getUrls(String content) {
		Document doc = Jsoup.parse(content);
		Element e = doc.getElementById("list");
		Elements divs = e.getElementsByTag("div");
		ArrayList<String> ls = Lists.newArrayList();
		for (Element div : divs) {
			Elements as = div.getElementsByTag("a");
			if (as.size() != 0) {
				Element a = as.get(0);
				String href = url + a.attr("href");
				ls.add(href);
			}
		}
		return ls;
	}

	public static void main(String[] args) {
		DiYiShiJianDownloader d = new DiYiShiJianDownloader();
		List<HouseDto> download = d.download();
		for (HouseDto dto : download) {
			Daos.getHouseDao().save(dto);
		}
	}
}
