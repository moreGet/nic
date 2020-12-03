package ch.get.fx.controller;

import java.util.logging.Logger;

import ch.get.fx.model.Nic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableDataSetController {

	public final static Logger log = Logger.getGlobal();
	private static ObservableList<Nic> nicData = FXCollections.observableArrayList();
	
	private TableDataSetController() {
		// ���� ������
		nicData.addAll(new Nic("KT INTRA-NET1", "Wi-Fi", "192.168.0.5", "255.255.255.0", "192.168.0.1", "168.128.64.1", "168.128.64.2", "Wi-Fi"),
					   new Nic("KT INTRA-NET1", "�̴���", "192.168.0.5", "255.255.255.0", "192.168.0.1", "168.128.64.1", "168.128.64.2", "Wi-Fi"));
	}
	
	private static class LazyHolder {
		public static final TableDataSetController INSTANCE = new TableDataSetController();
	}
	
	public static TableDataSetController getInstance() {
		return LazyHolder.INSTANCE;
	}

	/*
	 * ���
	 */
	public void commitData(TableColumn<Nic, String> column) {
		// ����Ŭ�� �� ���� ��� �߰�
		column.setCellFactory(TextFieldTableCell.<Nic>forTableColumn());
	}
	
	
	/*
	 * Getter / Setter
	 */
	public ObservableList<Nic> getNicData() {
		return nicData;
	}

	public static void setNicData(ObservableList<Nic> nicData) {
		TableDataSetController.nicData = nicData;
	}
}
