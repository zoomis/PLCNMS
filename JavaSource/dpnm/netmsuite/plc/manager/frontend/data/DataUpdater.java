package dpnm.netmsuite.plc.manager.frontend.data;

import java.sql.Connection;

/**
 * Data Update Logic
 * �ټ� ������ Data Structure���� ������ �� ����..
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
	 * ���� �Ǿ��ٸ�.. �� �н������ ������� DB�� �����ϴ� ������� �Ѵ�.
	 * �״��� �ٶ������� ���� ����̶�� ������.
	 */
	
	public abstract void updateModel();
	
	/**
	 * DB�� ��쿡�� DB connection�� ������ ��쿡�� skip�ϰų� ��ó�� �۾����� �ϰ� �ȴ�/
	 */
	public void makeConnection(){
		return;
	}
	
}
