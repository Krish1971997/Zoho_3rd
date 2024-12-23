package carRentalSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleLog {
	
	Map<String, List<Log>> log = new HashMap<>();
	private static VehicleLog instance = null;

	private VehicleLog() {}

	public static VehicleLog getInstance() {
		if (instance == null) {
			instance = new VehicleLog();
		}
		return instance;
	}

	public void addLog(String licenceNum, Log logInfo) {
//		List<Log> list = log.get(licenceNum);
//		if (list == null) {
//			log.put(licenceNum, new ArrayList<>());
//			log.get(licenceNum).add(logInfo);
//		} else {
//			list.add(logInfo);
//		}
		
		log.computeIfAbsent(licenceNum, k -> new ArrayList<>()).add(logInfo);
	}

	public void printLog(String licenceNum) {
		List<Log> list = log.get(licenceNum);
		for (Log val : list) {
			System.out.println(val);
		}
	}

	@Override
	public String toString() {
		return "VehicleLog [log=" + log + "]";
	}
	

}
