package ch.get.fx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;

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
	private synchronized boolean searchNicLists() {
		try {
			// CMD 실행하기
			String msg = "";
			
			for (String[] command : changeNetInterface()) {
				Process proc = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				
				while ((msg = br.readLine()) != null) {
					System.out.println(msg);
				}
			}
			
			return true;
		} catch (Exception e) {
			WindowController.getInstance()
							.showAlert("관리자 권한이 필요합니다.", "권한이 없습니다.", "관리자 권한으로 프로그램을 실행시켜 주세요.");
			e.printStackTrace();
			return false;
		}
	}
	
	private List<String[]> changeNetInterface() {
		String nicName = "이더넷";
		String nicIp = "192.168.0.5";
		String nicMask = "255.255.255.0";
		String nicGate = "192.168.0.1";
		String nicDns01 = "8.8.8.8";
		String nicDns02 = "";
		
		String[] permit = {"CMD.EXE", "/C", ""};
		
        String[] ip = { "netsh", "interface", "ip", "set", "address",
        "name=", nicName ,"source=static", "addr=",nicIp,
        "gateway=", nicGate,
        "mask=", nicMask};
        
//        String[] gate = { "netsh", "interface", "ip", "set", "address",
//        "name=", nicName ,"gateway=",nicGate,
//        "gwmetric=","0"};
		
        String[] dns = { "netsh", "interface", "ip", "set", "dns",
        "name=", nicName ,"source=static", "addr=",nicDns01};
        
//		return Arrays.asList(ip, gate, dns);
        return Arrays.asList(ip, dns);
	}
	
	/*
	 * Getter
	 */
	public boolean getNicTemp() {
		return searchNicLists();
	}
}
