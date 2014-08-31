package cn.kunsland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zone {
	
	private List<String> zones;
	private int last;
	
	public Zone(){
		init();
		last = 0;
	}
	
	private void init(){
		zones = new ArrayList<>();
		zones.add("jp");
		zones.add("kumamoto");
		zones.add("tokyo");
		zones.add("nagoya");
		zones.add("fukui");
		zones.add("tochigi");
		zones.add("okinawa");
		zones.add("gunma");
		zones.add("akita");
		zones.add("kagoshima");
		zones.add("niigata");
		zones.add("saitama");
		zones.add("fukuoka");
		zones.add("kanazawa");
		zones.add("kyoto");
	}
	
	public String getZone(){
		return zones.get(last);
	}
	
	public String getNextZone(){
		last = (last+1) % zones.size();
		return zones.get(last);
	}
	
	public String getRandomZone(){
		last = getRandom();
		return zones.get(last);
	}
	
	public int getRandom(){
		Random r = new Random();
		return r.nextInt(zones.size());
	}
	
	public String getZone(int i){
		return zones.get(i);
	}
	
	public int getSize(){
		return zones.size();
	}

}
