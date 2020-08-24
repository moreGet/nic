package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootLayoutController implements Initializable{
	
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// 윈도우 컨트롤러
	private WindowController winCont = WindowController.getInstance();
	
	@FXML
	private TableView<Nic> nicTable;
	@FXML
	private TableColumn<Nic, String> nicName;
	@FXML
	private TableColumn<Nic, String> nicIp;
	@FXML
	private TableColumn<Nic, String> nicMask;
	@FXML
	private TableColumn<Nic, String> nicGate;
	@FXML
	private TableColumn<Nic, String> nicDns01;
	@FXML
	private TableColumn<Nic, String> nicDns02;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 기본값 셋팅
		nicName.setCellValueFactory(cellData -> cellData.getValue().getNicName());
		tableDatCont.commitData(nicName);
		
		nicIp.setCellValueFactory(cellData -> cellData.getValue().getNicIp());
		tableDatCont.commitData(nicIp);
		
		nicMask.setCellValueFactory(cellData -> cellData.getValue().getNicMask());
		tableDatCont.commitData(nicMask);
		
		nicGate.setCellValueFactory(cellData -> cellData.getValue().getNicGate());
		tableDatCont.commitData(nicGate);
		
		nicDns01.setCellValueFactory(cellData -> cellData.getValue().getNicDns01());
		tableDatCont.commitData(nicDns01);
		
		nicDns02.setCellValueFactory(cellData -> cellData.getValue().getNicDns02());
		tableDatCont.commitData(nicDns02);
		ApplicationStart.LOG.info("### INIT_ROOT_CONTROLLER [ " + Thread.currentThread().getName() + " ]");
				
		nicTable.setItems(tableDatCont.getNicData());
		ApplicationStart.LOG.info("### INIT_TABLE_DATA [ " + Thread.currentThread().getName() + " ]");
	}
	
	@FXML
	private void onClickedExit() {
		winCont.exit();
	}
	
	@FXML
	private void onClickedAdd() {
		
	}
}