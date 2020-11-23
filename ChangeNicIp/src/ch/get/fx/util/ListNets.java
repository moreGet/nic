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
			int buf = 0;
			Process proc = Runtime.getRuntime().exec("ipconfig /all");
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			while ((buf=br.read()) != -1) {
				System.out.print((char)buf);
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * Getter
	 */
	public boolean getNicTemp() {
		return searchNicLists();
	}
}
