package ch.get.fx.controller;

import ch.get.fx.ApplicationStart;
import ch.get.fx.model.Nic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableDataSetController {

	private static ObservableList<Nic> nicData = FXCollections.observableArrayList();
	
	private TableDataSetController() {
		// 더미 데이터
		nicData.add(new Nic("KT INTRA-NET", "INTEL WIFI", "127.0.0.1", "255.255.255.0", "192.168.0.1", "168.128.64.1", "", "Wi-Fi", true));
	}
	
	private static class LazyHolder {
		public static final TableDataSetController INSTANCE = new TableDataSetController();
	}
	
	public static TableDataSetController getInstance() {
		return LazyHolder.INSTANCE;
	}

	/*
	 * 기능
	 */
	public void commitData(TableColumn<Nic, String> column) {
		// 더블클릭 셀 에딧 기능 추가
		column.setCellFactory(TextFieldTableCell.<Nic>forTableColumn());
		// 셀 아웃 포커싱
//		column.setCellFactory();
		// 수정후 해당 셀의 모델 인스턴스 원본 데이터 변경
		column.setOnEditCommit((CellEditEvent<Nic, String> event) -> {
            TablePosition<Nic, String> pos = event.getTablePosition();
 
            String newValue = event.getNewValue();
 
            int row = pos.getRow();
            Nic nic = event.getTableView().getItems().get(row);

            ApplicationStart.LOG.severe("######## CELL NewValue : " + event.getNewValue());
            ApplicationStart.LOG.severe("######## CELL OldValue : " + event.getOldValue());
            ApplicationStart.LOG.severe("######## CELL RowValue : " + event.getTablePosition().getRow());
            ApplicationStart.LOG.severe("######## CELL ColumnValue : " + event.getTablePosition().getColumn());
//            ApplicationStart.LOG.severe("######## CELL Data : " + nic.getNicName());
        });
	}
	
//	public void commitCheckBoxData(TableColumn<Nic, Boolean> column) {
//		// 더블클릭 셀 에딧 기능 추가
//		column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
//		// 수정후 해당 셀의 모델 인스턴스 원본 데이터 변경
//		System.out.println("심각");
//		column.setOnEditCommit((CellEditEvent<Nic, Boolean> event) -> {
//            TablePosition<Nic, Boolean> pos = event.getTablePosition();
// 
//            boolean newValue = event.getNewValue();
// 
//            int row = pos.getRow();
//            Nic nic = event.getTableView().getItems().get(row);
//
//            ApplicationStart.LOG.severe("######## CELL NewValue : " + event.getNewValue());
//            ApplicationStart.LOG.severe("######## CELL OldValue : " + event.getOldValue());
//            ApplicationStart.LOG.severe("######## CELL RowValue : " + event.getTablePosition().getRow());
//            ApplicationStart.LOG.severe("######## CELL ColumnValue : " + event.getTablePosition().getColumn());
////            ApplicationStart.LOG.severe("######## CELL Data : " + nic.getNicName());
//        });
//	}
	
	/*
	 * Getter / Setter
	 */
	public ObservableList<Nic> getNicData() {
		return nicData;
	}
}
