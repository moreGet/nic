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
	
	// ��ī�� ����
	private ListNets listNets;
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
	 * ���
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
	
	// ��Ʈ��ũ �������̽� ���� �ڵ鷯
	public void changeNicInfo(Nic nicTemp) {
		boolean isChanged = false;
		Alert alert = showAlert("��Ʈ��ũ ���� ��ü", nicTemp.toString(), "��Ʈ��ũ ������ �ٲٽðڽ��ϱ�?", AlertType.CONFIRMATION);
		
		if (alert.getResult().equals(ButtonType.OK)) {
			// �ٲ���°�?
			isChanged = listNets.changeNicInterface(nicTemp);
			
			if (isChanged) {
				WindowController.getInstance().showAlert(
						"���� ����", 
						"���濡 ���� �Ͽ����ϴ�.", 
						"",
						AlertType.INFORMATION);	
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
	
	public void setListNets(ListNets listNets) {
		this.listNets = listNets;
	}
}
