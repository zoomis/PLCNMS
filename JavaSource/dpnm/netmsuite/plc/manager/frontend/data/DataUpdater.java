package dpnm.netmsuite.plc.manager.frontend.data;

import java.sql.Connection;

/**
 * Data Update Logic
 * 다소 복잡한 Data Structure들의 내용이 들어가 있음..
 * @author ranivris
 */

public abstract class DataUpdater extends Thread{
	
	boolean isRun = true;
	public int updatePeriod = 5000;
	protected DataModel model;
	
	
	public DataUpdater(DataModel model){
		this.model = model;
	}
	
	public void updateStop(){
		isRun = false;
	}
	
	public void updateStart(){
		start();
	}
	
	public void run(){
		while(isRun){
			try{
				updateModel();
				Thread.sleep(updatePeriod);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
			
		}
	}
	
	/**
	 * 인증 되었다면.. 이 패스워드와 비번으로 DB에 접속하는 방식으로 한다.
	 * 그다지 바람직하지 않은 방법이라고 생각됨.
	 */
	
	public abstract void updateModel();
	
	/**
	 * DB인 경우에는 DB connection을 파일인 경우에는 skip하거나 전처리 작업등을 하게 된다/
	 */
	public void makeConnection(){
		return;
	}
	
}
