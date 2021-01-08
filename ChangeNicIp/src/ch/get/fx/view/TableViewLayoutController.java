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

public class TableViewLayoutController implements Initializable {
	// 싱글톤
	public static TableViewLayoutController instance;
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
	// MainInstance
	private ApplicationStart mainApp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
		
		// 기본값 셋팅
		nicKind.setCellValueFactory(cellData -> cellData.getValue().nicKindProperty());
		tableDatCont.commitData(nicKind);
		
		nicName.setCellValueFactory(cellData -> cellData.getValue().nicNameProperty());
		tableDatCont.commitData(nicName);
		
		nicIp.setCellValueFactory(cellData -> cellData.getValue().nicIpProperty());
		tableDatCont.commitData(nicIp);
		
		nicMask.setCellValueFactory(cellData -> cellData.getValue().nicMaskProperty());
		tableDatCont.commitData(nicMask);
		
		nicGate.setCellValueFactory(cellData -> cellData.getValue().nicGateProperty());
		tableDatCont.commitData(nicGate);
		
		nicDns01.setCellValueFactory(cellData -> cellData.getValue().nicDns01Property());
		tableDatCont.commitData(nicDns01);
		
		nicDns02.setCellValueFactory(cellData -> cellData.getValue().nicDns02Property());
		tableDatCont.commitData(nicDns02);
		
		nicInfo.setCellValueFactory(cellData -> cellData.getValue().nicInfoProperty());
		tableDatCont.commitData(nicInfo);
				
		nicTable.setItems(tableDatCont.getNicData());
	}
	
	/*
	 * Getter / Setter
	 */
	public TableView<Nic> getNicTable() {
		return nicTable;
	}
	
	public ApplicationStart getMainApp() {
		return mainApp;
	}

	public void setMainApp(ApplicationStart mainApp) {
		this.mainApp = mainApp;
	}
}
