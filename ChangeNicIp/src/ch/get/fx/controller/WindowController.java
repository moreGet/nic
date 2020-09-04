package ch.get.fx.controller;

import ch.get.fx.util.ListNets;
import javafx.application.Platform;

public class WindowController {
	
	// ��ī�� ����
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
	 * ���
	 */
	public void exit() {
		Platform.exit();
	}
	
	public void search() {
		listNets.getNicTemp();
	}
}
