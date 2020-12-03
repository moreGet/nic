package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootLayoutController implements Initializable{
	// 싱글톤
	public static RootLayoutController instance;
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont;
	// 윈도우 컨트롤러
	private WindowController winCont;
	
	@FXML
	private TableView<Nic> nicTable;
	@FXML
	private TableColumn<Nic, String> nicKind;
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
	@FXML
	private TableColumn<Nic, String> nicInfo;
//	@FXML
//	private TableColumn<Nic, Boolean> selNic;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
		
		// 기본값 셋팅
		nicKind.setCellValueFactory(cellData -> cellData.getValue().getNicKind());
		tableDatCont.commitData(nicKind);
		
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
		
		nicInfo.setCellValueFactory(cellData -> cellData.getValue().getNicInfo());
		tableDatCont.commitData(nicInfo);
		
		// 컬럼 셋팅 끝
		ApplicationStart.LOG.info("### INIT_ROOT_CONTROLLER [ " + Thread.currentThread().getName() + " ]");
				
		nicTable.setItems(tableDatCont.getNicData());
		ApplicationStart.LOG.info("### INIT_TABLE_DATA [ " + Thread.currentThread().getName() + " ]");
	}
	
	public TableView<Nic> getNicTable() {
		return nicTable;
	}
}