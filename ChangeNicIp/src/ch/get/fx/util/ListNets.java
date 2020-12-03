package ch.get.fx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.get.fx.common.CommandError;
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
	 * ���
	 */
	public boolean changeNicInterface(Nic nicTemp) {
		boolean changeOkay = false; // NIC���� �ٲ������?
		// üũ �ڽ� ������ ���� VPN���� �系�� IP���� ������ �����;� ��
		List<String[]> nicInterfaceInfo = new ArrayList<String[]>();
		
//		nicInterfaceInfo = switchIntraNet(nicTemp); // üũ�� �Ǹ� ��Ʈ���
		nicInterfaceInfo = switchInterNet(nicTemp); // �ƴ϶�� ���ͳ�
		
		try {
			// ������ ���� ����
			for (String[] command : nicInterfaceInfo) {
				if (!vaildAdminPermit(command)) {
					throw new Exception();
				}
			}
			changeOkay = true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return changeOkay;
	}
	
	// ���ͳ� ����ġ
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
	
	// ��Ʈ��� ����ġ
	// �ٲܶ� IP �������� �Ű����� Ʋ���ٰ� ����
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
		StringBuffer sb = new StringBuffer("");
		String msg = "";
		Process proc = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		
		while ((msg = br.readLine()) != null) {
			if (msg.contains(CommandError.ERROR_PERMIT.getErrorCode())) {
				sb.append("������ ������ �ʿ� �մϴ�.");
			} else if(msg.contains(CommandError.ERROR_NIC_NAME.getErrorCode())) {
				sb.append("��Ʈ��ũ ����� �̸��� ��Ȯ���� Ȯ�� �ϼ���.");
			} else if (msg.contains(CommandError.ERROR_ALREADY_DHCP.getErrorCode())) {
				sb.append("�̹� ���ͳ� ������ �Ǿ� �ֽ��ϴ�.");
			} else {
				System.out.println(msg);
			}
			
			if (sb.length() > 0) {
				WindowController.getInstance().showAlert(
						"���� �Ұ�", 
						"�Ʒ� ������ ������ �Ұ��� �մϴ�.", 
						sb.toString(), 
						AlertType.ERROR);
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Getter
	 */
}
