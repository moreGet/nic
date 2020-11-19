package ch.get.fx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.get.fx.model.Nic;

/**
 * @author Get
 * GET NIC INFO
 */
public class ListNets {
	private List<Nic> nicTemp;
	
	private ListNets() {
		
	}
	
	private static class LazyHolder {
		public static final ListNets INSTANCE = new ListNets();
	}
	
	public static ListNets getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	/*
	 * ���
	 */
	private synchronized List<Nic> searchNicLists() {
		try {
			// Ÿ��Ʋ
//			Map<String, List<String>> info = new HashMap<>();
			// IP �ּ�
//			List<String> content = new ArrayList<>();
			
			
			// ���Խ� ����
//			String reg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
//			Pattern pattern = Pattern.compile(reg);
//			Matcher matcher;
			
			// CMD �����ϱ�
			Process proc = Runtime.getRuntime().exec("ipconfig /all");
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			String line = null;
//			String prefix = null;
//			String postfix = null;
			String lineTemp = null;
			
			// ���ڿ� �Ÿ���
			while ((line = br.readLine()) != null) {
				
				if (!line.isEmpty()) {
					lineTemp = line.trim();
					
					if (lineTemp.indexOf('.') == 0) {
						
					}
				}
				
//				matcher = pattern.matcher(line);
//				if (matcher.find()) {
//					postfix = matcher.group();
//					postfix = postfix.trim();
//					
//					prefix = line.trim();
//					prefix = prefix.substring(0, prefix.indexOf('.'));
//
//					System.out.print("[ " + prefix + " ] ");
//					System.out.println("[ " + postfix + " ]");
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// �ӽ�
		this.nicTemp = new ArrayList<Nic>();
		this.nicTemp.add(new Nic(
				"KT INTRA-NET", // nicKind
				"INTEL WIFI", // nicName
				"127.0.0.1", // nicIp
				"255.255.255.0", // nicMask
				"192.168.0.1", // nicGate
				"168.128.64.1", // nicDns01
				"", // nicDns02
				"Wi-Fi",// nicInfo
				true
		));
		
		return this.nicTemp;
	}
	
	/*
	 * Getter
	 */
	public List<Nic> getNicTemp() {
		return searchNicLists();
	}
}
