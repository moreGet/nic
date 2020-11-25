package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootLayoutController implements Initializable{
	
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// 윈도우 컨트롤러
	private WindowController winCont = WindowController.getInstance();
	
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
	@FXML
	private TableColumn<Nic, Boolean> selNic;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		// CheckBox
		tableDatCont.addCheckBoxInCell(selNic);		
		// Add Listener CheckBox
		selNic.setCellValueFactory(cellData -> {
			Nic nic = cellData.getValue();
			SimpleBooleanProperty boolProp = new SimpleBooleanProperty(nic.getSelNic().get());
			
			boolProp.addListener((ob, oV, nV) -> {
				log.severe("상태 : " + nV);
				winCont.changeNicInfo(nic);
			});
			return boolProp;
		});
		
		// 컬럼 셋팅 끝
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
//		tableDatCont.getNicData().add(new Nic(
//				"KT INTRA-NET", // nicKind
//				"INTEL WIFI", // nicName
//				"127.0.0.1", // nicIp
//				"255.255.255.0", // nicMask
//				"192.168.0.1", // nicGate
//				"168.128.64.1", // nicDns01
//				"", // nicDns02
//				"Wi-Fi",// nicInfo
//				false
//		));
		
		boolean submit = winCont.addNicInfo();
		if (submit) {
			System.out.println("등록 버튼");
		} else {
			System.out.println("취소 버튼");
		}
	}
	
	@FXML
	private void searchNicInfo() {
		winCont.search();
	}
}