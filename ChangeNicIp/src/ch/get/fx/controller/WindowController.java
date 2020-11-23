package ch.get.fx.controller;

import ch.get.fx.model.Nic;
import ch.get.fx.util.ListNets;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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
	
	public void changeNicInfo(Nic nicTemp) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("��Ʈ��ũ ���� ��ü");
		alert.setHeaderText(nicTemp.toString());
		alert.setContentText("��Ʈ��ũ ������ �ٲٽðڽ��ϱ�?");
		alert.showAndWait();
		
		if (alert.getResult().equals(ButtonType.OK)) {
			System.out.println("Ȯ��â ��� : " + alert.getResult().getText());
		}	
	}
	
	public void search() {
		listNets.getNicTemp();
	}
}
