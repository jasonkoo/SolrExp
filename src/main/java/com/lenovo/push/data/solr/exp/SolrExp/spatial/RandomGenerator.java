package com.lenovo.push.data.solr.exp.SolrExp.spatial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class RandomGenerator {
	
	private double minLatitude= 39.842472;
	private double maxLatitude = 40.004661;
	private double minLongitude = 116.267683;
	private double maxLongitude = 116.481230;
	
	Random r = new Random();
	private static String[] shopNames = {"麦当劳", "汉丽轩", "汉堡王", "必胜客", "好伦哥", "味多美", "星巴克", "肯德基", "呷哺呷哺",
		"江边城外", "云海肴", "小肥羊", "金汉斯", "嘉和一品粥", "宏状元", "东方饺子王", "哈根达斯", "DQ冰淇淋"};
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		RandomGenerator rg = new RandomGenerator();
		List<ShopEntity> shopList = new ArrayList<ShopEntity>();
		
		for (int i = 0; i < 1000; i++) {
			ShopEntity se = new ShopEntity();
			String shopName = shopNames[i % shopNames.length];
			se.setId(i + "");
			se.setName(shopName);
			se.setPosition(rg.getRandomPosPair());
			shopList.add(se);
			
		}
		ObjectWriter ow = new ObjectMapper().writer();
		String jsonShopList = ow.writeValueAsString(shopList);
		System.out.println(jsonShopList);
		
	}
	
	private String getRandomPosPair() {
		double randLat = getRandomInRange(minLatitude, maxLatitude);
		double randLong = getRandomInRange(minLongitude, maxLongitude);
		
		return randLat + "," + randLong;
	}
	
	private double getRandomInRange(double min, double max) {
		double randomValue = min + (max - min) * r.nextDouble();
		return randomValue;
	}

}
