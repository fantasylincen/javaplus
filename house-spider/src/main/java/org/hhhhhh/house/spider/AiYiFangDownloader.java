package org.hhhhhh.house.spider;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class AiYiFangDownloader implements FangYuanWangDownloader {

	String url = "http://www.iefang.com/SecondHandHouse/UsedIndividualResource/";
	String baseUrl = "http://www.iefang.com/";

	@Override
	public List<HouseDto> download() {
		String content1 = WebContentFethcer.get("utf-8", url);
		List<String> houseUrls1 = getUrls(content1);

		List<String> urls = Lists.newArrayList();
		urls.addAll(houseUrls1);

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
		return ls;
	}

	private boolean isAreadyHave(String url2) {
		return Daos.getHouseDao().get(url2) != null;
	}

	private HouseDto getHouseData(String url) {

		// Log.d("fetch house data", url);
		String houseInfo = WebContentFethcer.get("gb2312", url);
//		try {
//			houseInfo = new String(houseInfo.getBytes(), "utf8");
//		} catch (UnsupportedEncodingException e2) {
//			e2.printStackTrace();
//		}
//		try {
//			System.setOut(new PrintStream("d:/temp.xml"));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		Log.d(houseInfo);
		
		HouseDto dto = new HouseDto();
		Document doc = Jsoup.parse(houseInfo);
		Element ee = doc.getElementsByAttributeValue("class", "hua_bj").get(0);
		Elements sss = ee.getElementsByAttributeValue("class", "yun_right");
		Element yun_right = sss.get(0);

		String name = yun_right.getElementsByAttributeValue("class", "hua_ya").get(0).text().trim();
		String commitDate = getCommitDate(yun_right);
		dto.setCommit_date(commitDate);
		dto.setName(name);
		dto.setUpdate_date(Util.Time.format(System.currentTimeMillis()));
		dto.setHref(url);
		Element dd = yun_right.getElementsByAttributeValue("class", "yi_jia_left").get(0);
		Elements dls = dd.getElementsByTag("p");
		
		Elements lou_ming = ee.getElementsByTag("lou_ming");
		
		String tel = yun_right.getElementsByAttributeValue("class", "hao_ma").get(0).text();
		String who = yun_right.getElementsByAttributeValue("style", " color:#42c1ee; font-weight:bold; font-size:15px;").get(0).text();
		
		
		
		dto.setOwner(who);
		dto.setTel(tel);
		
		setAttributes(dto, dls, lou_ming);
		Log.d("fetch ayf house data successful", url);
		return dto;
	}

	private void setAttributes(HouseDto dto, Elements dls, Elements lou_ming) {
		
		for (Element e : dls) {
			Element dt = getElementsByTag(e, "b");
			Element dd = getElementsByTag(e, "a");
			String key = dt.text().trim();
			String value = dd.text().trim();

			if (key.contains("单 价")) {
				value = value.replaceAll(" 元.+", "");
				value = value.trim();
				dto.setPrice(value);
			} else if (key.contains("户 型")) {
				dto.setHuxing(value);
			}
		}
		
		
		for (Element e : lou_ming) {
			Element dt = getElementsByTag(e, "b");
			Element dd = getElementsByTag(e, "a");
			String key = dt.text().trim();
			String value = dd.text().trim();

			if (key.contains("地&nbsp;&nbsp;&nbsp;&nbsp;址")) {
				dto.setPlate(value);
				dto.setZoneName(value);
			} else if (key.contains("配套设施")) {
				dto.setDsc(value);
			} else if (key.contains("楼&nbsp;&nbsp;&nbsp;&nbsp;层")) {
				dto.setFloor(value);
			}
		}
	}

	static Pattern compile = Pattern
			.compile("[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}\\　[0-9]{2}:[0-9]{2}");

	private String getCommitDate(Element e) {
		Elements divs = e.getElementsByTag("p");
		Element div = divs.get(1);
		// Element time = getElementsByTag(div, "p");
		// String text = time.text();
		// Matcher m = compile.matcher(text);
		// if (m.find()) {
		// String g = m.group();
		// String r = g.replace("　", " ");
		// return r + ":00";
		// }
		// return "1970-01-01 00:00:00";

		Pattern p = Pattern
				.compile("[0-9]{4}\\/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
		Matcher m = p.matcher(div.text());
		if (m.find()) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/M/d HH:mm:ss");
			SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = sf.parse(m.group());
				return sf2.format(date);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
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
		Element e = doc.getElementById("PageSearch");
		Elements es = e.getElementsByAttributeValue("class", "jin_jiao");
		ArrayList<String> ls = Lists.newArrayList();

		for (Element el : es) {
			Element b = el.getElementsByAttributeValue("class", "jin_jiao_B")
					.get(0);
			Element c = el.getElementsByAttributeValue("class", "jin_jiao_C")
					.get(0);
			Element d = el.getElementsByAttributeValue("class", "jin_jiao_D")
					.get(0);

			Element wan_jiao = b.getElementsByAttributeValue("class",
					"wan_jiao").get(0);

			// <a target="_blank" href="/zushou/20150710061007_shou_1.html"
			// onclick="AddRentHouseCookIe('bc1203ca-2d07-4546-ba8e-7705908a028f')">不限购！【慧忠北里】253㎡690万速进</a>
			// <a href="javascript:void(0)"></a>

			Element a = wan_jiao.getElementsByTag("a").get(0);
			String url = baseUrl + a.attr("href");

			ls.add(url);
		}

		// Elements divs = e.getElementsByTag("div");
		// for (Element div : divs) {
		// Elements as = div.getElementsByTag("a");
		// if (as.size() != 0) {
		// Element a = as.get(0);
		// String href = url + a.attr("href");
		// ls.add(href);
		// }
		// }
		return ls;
	}

	public static void main(String[] args) {
		AiYiFangDownloader d = new AiYiFangDownloader();
		List<HouseDto> download = d.download();
		for (HouseDto dto : download) {
			Daos.getHouseDao().save(dto);
		}
	}
}
