package dpnm.netmsuite.plc.manager.frontend.data;

import dpnm.netmsuite.util.*;
import java.util.*;

public interface DataManager{
	public PLCTreeNode getTreeMap();
	public String getData(String nodeID, String field);
	public void updateDataModelStart();
	public void updateDataModelStop();
	public int getNodeType(String macAddr);
	public Vector getFaultList(String macAddr);
	public Vector getStatics(String macAddr);
	public Vector getNewestFaultList( int size);
}
