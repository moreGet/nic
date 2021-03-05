package ch.get.fx.view;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ToolBarLayoutController implements Initializable {
	// �̱���
	public static ToolBarLayoutController instance;
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont;
	// ������ ��Ʈ�ѷ�
	private WindowController winCont;
	// ��Ʈ ���̾ƿ� ��Ʈ�ѷ�
	private TableViewLayoutController tableViewCont;
	// toolBarStage
	private Stage toolBarStage;
	// MainInstance
	private ApplicationStart mainApp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
		tableViewCont = ApplicationStart.tableViewCont;
	}

	public void setToolBarStage(Stage toolBarStage) {
		this.toolBarStage = toolBarStage;
	}
	
	@FXML
	private void onClickedExit() {
		System.exit(0);
//		winCont.exit();
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
		int selectedItem = tableViewCont.getNicTable().getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
				.filter(idx -> idx.intValue() >= 0)
				.ifPresent(idx -> {
					tableViewCont.getNicTable().getItems().remove(idx.intValue());
				});
	}
	
	@FXML
	private void changeNicInfo() {
		int selectedItem = tableViewCont.getNicTable().getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
		.filter(idx -> idx.intValue() >= 0)
		.ifPresent(idx -> {
			Nic nicTemp = tableViewCont.getNicTable().getItems().get(idx);
			winCont.changeNicInfo(nicTemp);
		});
	}
	
	@FXML
	private void reStoreNicInfo() {
		int selectedItem = tableViewCont.getNicTable().getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
		.filter(idx -> idx.intValue() >= 0)
		.ifPresent(idx -> {
			Nic nicTemp = tableViewCont.getNicTable().getItems().get(idx);
			winCont.reStoreNicInfo(nicTemp);
		});
	}
	
	/*
	 * Clear nicInfo
	 */
	private void clearNicInfo() {
		tableDatCont.getNicData().clear(); // ���� NicInfo �ʱ�ȭ
		mainApp.setNicInfoFilePath(null); // ������Ʈ�� ������ ����
	}
	
	/*
	 * JAXB I/O
	 * FileChooser
	 */
	@FXML
	private void handleOpenNicInfoXml() {
		FileChooser fileChooser = new FileChooser();
		
		// Ȯ���� ���͸� �����Ѵ�.
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
				"XML files (*,xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extensionFilter);
		
		// Save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaStage());
		
		if (file != null) {
			mainApp.loadNicInfoDataFromFile(file);
		}
	}
	
	@FXML
	private void handleSaveNicInfoXml() {
		File nicFile = mainApp.getNicInfoFilePath(); // ���� �����ִ� ���� ���
		if (nicFile != null) { // ���ٸ�.
			mainApp.saveNicInfoDataToFile(nicFile); // ��������
		} else {
			handleSaveAsNicInfoXml(); // ���� ��ΰ� �����ϸ� �ٸ� �̸����� ����
		}
	}
	
	@FXML
	private void handleSaveAsNicInfoXml() {
		FileChooser fileChooser = new FileChooser();
		
		// Ȯ���� ���͸� �����Ѵ�.
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		// Save File Dialong�� �����ش�.
		File file = fileChooser.showSaveDialog(mainApp.getPrimaStage());
		
		if (file != null) {
			// ��Ȯ�� Ȯ���ڸ� ������ ��.
			if (!file.getPath().endsWith(",xml")) {
				file = new File(file.getPath());
			}
			mainApp.saveNicInfoDataToFile(file);
		}
	} 
	
	/*
	 * getter / setter
	 */
	public ApplicationStart getMainApp() {
		return mainApp;
	}

	public void setMainApp(ApplicationStart mainApp) {
		this.mainApp = mainApp;
	}
}
