package ch.get.fx.controller;

import javafx.application.Platform;

public class WindowController {

	private WindowController() {

	}
	
	private static class LazyHolder {
		public static final WindowController INSTANCE = new WindowController();
	}
	
	public static WindowController getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	/*
	 * ±â´É
	 */
	public void exit() {
		Platform.exit();
	}
}
