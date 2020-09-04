package ch.get.fx.controller;

import ch.get.fx.util.ListNets;
import javafx.application.Platform;

public class WindowController {
	
	// 랜카드 정보
	private ListNets listNets = ListNets.getInstance();
	
	private WindowController() {
		
	}
	
	private static class LazyHolder {
		public static final WindowController INSTANCE = new WindowController();
	}
	
	public static WindowController getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	/*
	 * 기능
	 */
	public void exit() {
		Platform.exit();
	}
	
	public void search() {
		listNets.getNicTemp();
	}
}
