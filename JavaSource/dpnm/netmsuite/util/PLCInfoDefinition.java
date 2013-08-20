package dpnm.netmsuite.util;

public interface PLCInfoDefinition {

	public static int NODETYPE_EMPTY = -1;
	public static int NODETYPE_EU = 0;
	public static int NODETYPE_MASTER_MODEM = 1;
	public static int NODETYPE_SLAVE_MODEM = 2;
	public static int NODETYPE_REPATER = 3;
	
	public static int STATE_WORKING = 100;
	public static int STATE_STOP = 101;
	public static int STATE_UPGRAING = 102;
	public static int STATE_BROKEN = 103; //?
	
}
