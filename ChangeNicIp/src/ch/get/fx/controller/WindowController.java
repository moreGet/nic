package ch.get.fx.controller;

import ch.get.fx.ApplicationStart;
import ch.get.fx.model.Nic;
import ch.get.fx.util.ListNets;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class WindowController {
	
	// 랜카드 정보
	private ListNets listNets = ListNets.getInstance();
	// Main Instance
	private ApplicationStart mainApp;
	
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
	public Alert showAlert(String title, String header, String content, AlertType alertType) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setAlertType(alertType);
		alert.showAndWait();
		
		return alert;
	}
	
	public void exit() {
		Platform.exit();
	}
	
	public void close(Stage stage) {		
		stage.close();
	}
	
	public void changeNicInfo(Nic nicTemp) {
		boolean changeResult = false;
		Alert alert = showAlert("네트워크 정보 교체", nicTemp.toString(), "네트워크 정보를 바꾸시겠습니까?", AlertType.CONFIRMATION);
		
		if (alert.getResult().equals(ButtonType.OK)) {
			// 바뀌었는가?
			changeResult = listNets.getNicTemp();
			
			// 바뀌었다면..
			if (changeResult) {
				System.out.println("바뀜");
			} else {
				System.out.println("에러");
			}
		}	
	}
	
	public boolean addNicInfo() {
		return mainApp.initNicOverViewLayout();
	}

	public void search() {
//		listNets.getNicTemp();
	}
	
	// Setter
	public void setMainApp(ApplicationStart mainApp) {
		this.mainApp = mainApp;
	}
}
