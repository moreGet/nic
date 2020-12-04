package ch.get.fx.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
			Nic nicTemp = tableViewCont.getNicTable().getItems().get(selectedItem);
			winCont.changeNicInfo(nicTemp);
		});
	}
}
