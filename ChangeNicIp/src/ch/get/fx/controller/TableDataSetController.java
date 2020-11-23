package ch.get.fx.controller;

import java.util.logging.Logger;

import ch.get.fx.model.Nic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class TableDataSetController {

	public final static Logger log = Logger.getGlobal();
	private static ObservableList<Nic> nicData = FXCollections.observableArrayList();
	
	private TableDataSetController() {
		// 더미 데이터
		nicData.add(new Nic("KT INTRA-NET", "INTEL WIFI", "127.0.0.1", "255.255.255.0", "192.168.0.1", "168.128.64.1", "", "Wi-Fi", false));
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
		// 수정후 해당 셀의 모델 인스턴스 원본 데이터 변경 감지
//		column.setOnEditCommit((CellEditEvent<Nic, String> event) -> {
//            TablePosition<Nic, String> pos = event.getTablePosition();
// 
//            String newValue = event.getNewValue();
// 
//            int row = pos.getRow();
//            Nic nic = event.getTableView().getItems().get(row);
//
//            ApplicationStart.LOG.severe("######## CELL NewValue : " + event.getNewValue());
//            ApplicationStart.LOG.severe("######## CELL OldValue : " + event.getOldValue());
//            ApplicationStart.LOG.severe("######## CELL RowValue : " + event.getTablePosition().getRow());
//            ApplicationStart.LOG.severe("######## CELL ColumnValue : " + event.getTablePosition().getColumn());
//            ApplicationStart.LOG.severe("######## CELL Data : " + nic.getNicName());
//        });
	}
	
	// 체크박스 셀에 추가후 체크박스 리스너 달기
	public void addCheckBoxInCell(TableColumn<Nic, Boolean> column) {
		// CheckBox
		column.setCellFactory(new Callback<TableColumn<Nic,Boolean>, TableCell<Nic,Boolean>>() {
			@Override
			public TableCell<Nic, Boolean> call(TableColumn<Nic, Boolean> param) {
				CheckBoxTableCell<Nic, Boolean> cell = new CheckBoxTableCell<Nic, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
	}
	
	/*
	 * Getter / Setter
	 */
	public ObservableList<Nic> getNicData() {
		return nicData;
	}
}
