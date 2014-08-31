package cn.kunsland;

public class TimeListener extends Thread {
	
	private String time="";
	private TimeProcessor tp;
	
	public TimeListener(TimeProcessor tp){
		this.tp = tp;
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1*1000);
				String t = TimeHelper.getHourMinute();
				if(!t.equals(time)){
					time = t;
					tp.processTimeChanged(time);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
