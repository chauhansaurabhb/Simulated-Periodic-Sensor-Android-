package sim.deviceD9;


import iotsuite.semanticmodel.*;
import iotsuite.common.*;
import iotsuite.pubsubmiddleware.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import logic.*;
import android.content.Context;

public class Startup implements DeviceStartup {
	
	private static Context ui;
	private static String name ;
	private static String softwarePlatform;
	private static String networkAddress;
	private static Set<String> abilities = new HashSet<String>();
	private static PubSubMiddleware myPubSub;
	public static Object myContext = null;
	public static String mobileFlag;
	public static String protocol;

private static void setUpNode(Device deviceInfo)	{	

	
	
	myPubSub = (PubSubMiddleware) IoTSuiteFactory.getInstance(deviceInfo.getProtocol(), setUpDeviceInfo(), myContext);

	
	

		LogicTemperatureSensor logicTemperatureSensorImpl = new LogicTemperatureSensor(myPubSub, deviceInfo, ui, (Context) myContext); 
		new Thread(logicTemperatureSensorImpl).start();
	    
	
}

private static Device setUpDeviceInfo() {
    
	 name = "D9";
	 softwarePlatform = "Android";
	 networkAddress =  "";
	 protocol = "mqtt";
		
	abilities.add("TemperatureSensor");  
	
	List<String> regionIDs = new ArrayList<String>();
  List<String> regionLabels = new ArrayList<String>();
  
  regionIDs.add("1") ; 
  
  regionLabels.add("Room"); 
	
	Device device = new Device(name, softwarePlatform, networkAddress, regionIDs, regionLabels , abilities, mobileFlag, protocol);
	return device;    
}

@Override
public void startDevice(Object obj, Object context) {
	ui= (Context) obj;
	myContext=context;
	setUpNode(setUpDeviceInfo());
	
}

}