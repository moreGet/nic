package ch.get.fx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Get
 * GET NIC INFO
 */
public class ListNets {
	private ListNets() {}
	
	private static class LazyHolder {
		public static final ListNets INSTANCE = new ListNets();
	}
	
	public static ListNets getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	/*
	 * 기능
	 */
	private boolean changeNicInterface(Nic nicTemp, boolean selNicStatus) {
		boolean changeOkay = false; // NIC정보 바뀌었는지?
		
		// 체크 박스 유무에 따라 VPN인지 사내망 IP인지 정보를 가져와야 함
		List<String[]> nicInterfaceInfo = new ArrayList<String[]>();
		
		if (selNicStatus) {
//			System.out.println("인트라넷");
			nicInterfaceInfo = switchIntraNet(nicTemp); // 체크가 되면 인트라넷
		} else {
//			System.out.println("인터넷");
			nicInterfaceInfo = switchInterNet(nicTemp); // 아니라면 인터넷
		}
		
		try {
			// 관리자 권한 보기
			for (String[] command : nicInterfaceInfo) {
				for (String strings : command) {
					System.out.print(strings + " ");
				}
				System.out.println();
				
				if (!vaildAdminPermit(command)) {
					throw new Exception();
				}
			}
			changeOkay = true;
		} catch (Exception e) {
			WindowController.getInstance().showAlert(
					"관리자 권한이 필요합니다.", 
					"권한이 없습니다.", 
					"관리자 권한으로 프로그램을 실행시켜 주세요.", 
					AlertType.INFORMATION);
		}
		
		return changeOkay;
	}
	
	// 인터넷 스위치
	private List<String[]> switchInterNet(Nic nic) {
		String nicName = nic.getNicName().get();
		
        String[] ip = { "netsh", "interface", "ip", "set", "address",
        "name=", nicName ,"source=dhcp"};
        
        String[] dns = { "netsh", "interface", "ip", "set", "dns",
        "name=", nicName ,"source=dhcp", "register=PRIMARY"};
        
        String[] wins = { "netsh", "interface", "ip", "set", "wins",
        "name=", nicName ,"source=dhcp"};
        
        return Arrays.asList(ip, dns, wins);
	}
	
	// 인트라넷 스위치
	// 바꿀때 IP 만맞으면 매개변수 틀리다고 나옴
	private List<String[]> switchIntraNet(Nic nicTemp) {
		String nicName = nicTemp.getNicName().get();
		String nicIp = nicTemp.getNicIp().get();
		String nicMask = nicTemp.getNicMask().get();
		String nicGate = nicTemp.getNicGate().get();
		String nicDns01 = nicTemp.getNicDns01().get();
		String nicDns02 = nicTemp.getNicDns02().get();

        String[] ip = { "netsh", "interface", "ip", "set", "address",
        "name=",nicName ,"source=static", "addr=",nicIp,
        "gateway=",nicGate,
        "mask=",nicMask};
		
        String[] dns = { "netsh", "interface", "ip", "set", "dns",
        "name=",nicName ,"source=static", "addr=",nicDns01};
        
        String[] dns2 = { "netsh", "interface", "ip", "add", "dns",
        "name=",nicName ,nicDns02};
        
        return Arrays.asList(ip, dns, dns2);
	}
	
	public synchronized boolean vaildAdminPermit(String[] command) throws Exception {
		String msg = "";
		Process proc = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		
		while ((msg = br.readLine()) != null) {
			System.out.println(msg);
			
			if (msg.contains("관리자")) {
				System.out.println(msg);
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Getter
	 */
	public boolean getNicTemp(Nic nicTemp, boolean selNicStatus) {
		return changeNicInterface(nicTemp, selNicStatus);
	}
}
