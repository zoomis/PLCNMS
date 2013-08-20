package dpnm.netmsuite.util;

public class StatisticInfo {
	public long _timeStamp;
	
	public long _inPkts;
	public long _outPkts;
	
	public long _inSpeed = -1;
	public long _outSpeed = -1;
	
	public long _inBytes;
	public long _outBytes;
	
	public StatisticInfo(long timeStamp, int inPkts, int outPkts, int inBytes, int outBytes){
		_timeStamp = timeStamp;
		
		_inPkts = inPkts;
		_outPkts = outPkts;
		
		_inBytes = inBytes;
		_outBytes = outBytes;

	}
	
	public String toString(){
		return "TimeStamp : " + _timeStamp + " inPkts : " + _inPkts  + " outPkts : " + _outPkts  + " _inBytes : " + _inBytes  + " _outBytes : " + _outBytes  + " _inSpeed : " + _inSpeed  + " _outSpeed : " + _outSpeed; 
	}

	public void set_inSpeed(long _inSpeed) {
		this._inSpeed = _inSpeed;
	}

	public long get_inSpeed() {
		return _inSpeed;
	}

	public void set_outSpeed(long _outSpeed) {
		this._outSpeed = _outSpeed;
	}

	public long get_outSpeed() {
		return _outSpeed;
	}
}
