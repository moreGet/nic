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
	// 싱글톤
	public static ToolBarLayoutController instance;
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont;
	// 윈도우 컨트롤러
	private WindowController winCont;
	// 루트 레이아웃 컨트롤러
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
		
		if (isOkay) { // 랜카드 추가 버튼
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
		tableDatCont.getNicData().clear(); // 현재 NicInfo 초기화
		mainApp.setNicInfoFilePath(null); // 레지스트리 데이터 삭제
	}
	
	/*
	 * JAXB I/O
	 * FileChooser
	 */
	@FXML
	private void handleOpenNicInfoXml() {
		FileChooser fileChooser = new FileChooser();
		
		// 확장자 필터를 설정한다.
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
		File nicFile = mainApp.getNicInfoFilePath(); // 현재 열려있는 파일 경로
		if (nicFile != null) { // 없다면.
			mainApp.saveNicInfoDataToFile(nicFile); // 새로저장
		} else {
			handleSaveAsNicInfoXml(); // 기존 경로가 존재하면 다름 이름으로 저장
		}
	}
	
	@FXML
	private void handleSaveAsNicInfoXml() {
		FileChooser fileChooser = new FileChooser();
		
		// 확장자 필터를 설정한다.
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		// Save File Dialong를 보여준다.
		File file = fileChooser.showSaveDialog(mainApp.getPrimaStage());
		
		if (file != null) {
			// 정확한 확장자를 가져야 함.
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
