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
	 * ���
	 */
	public Alert showAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
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
		Alert alert = showAlert("��Ʈ��ũ ���� ��ü", nicTemp.toString(), "��Ʈ��ũ ������ �ٲٽðڽ��ϱ�?");
		
		if (alert.getResult().equals(ButtonType.OK)) {
			System.out.println("Ȯ��â ��� : " + alert.getResult().getText());
			changeResult = listNets.getNicTemp();
			
			if (changeResult) {
				System.out.println("�ٲ�");
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
