package ch.get.fx.view;

import java.net.URL;
import java.util.Optional;
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
	// �̱���
	public static RootLayoutController instance;
	public final static Logger log = Logger.getGlobal();
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont;
	// ������ ��Ʈ�ѷ�
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
	@FXML
	private TableColumn<Nic, Boolean> selNic;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
		
		// �⺻�� ����
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
//				log.severe("���� : " + nV);
				winCont.changeNicInfo(nic, nV);
			});
			return boolProp;
		});
		
		// �÷� ���� ��
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
		boolean isOkay = winCont.addNicInfo();
		
		if (isOkay) { // ��ī�� �߰� ��ư
			tableDatCont.getNicData().add(NicOverViewLayoutController.instance.getNic());
		}
	}
	
	@FXML
	private void onClickedDelete() {
		int selectedItem = nicTable.getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
				.filter(idx -> idx.intValue() >= 0)
				.ifPresent(idx -> {
					nicTable.getItems().remove(idx.intValue());
				});
	}
	
	@FXML
	private void searchNicInfo() {
		winCont.search();
	}
}