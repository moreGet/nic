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
	// �̱���
	public static ToolBarLayoutController instance;
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont;
	// ������ ��Ʈ�ѷ�
	private WindowController winCont;
	// ��Ʈ ���̾ƿ� ��Ʈ�ѷ�
	private RootLayoutController rootCont;
	// toolBarStage
	private Stage toolBarStage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
		rootCont = RootLayoutController.instance;
	}

	public void setToolBarStage(Stage toolBarStage) {
		this.toolBarStage = toolBarStage;
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
		int selectedItem = rootCont.getNicTable().getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
				.filter(idx -> idx.intValue() >= 0)
				.ifPresent(idx -> {
					rootCont.getNicTable().getItems().remove(idx.intValue());
				});
	}
	
	@FXML
	private void changeNicInfo() {
		int selectedItem = rootCont.getNicTable().getSelectionModel().getSelectedIndex();
		
		Optional.ofNullable(selectedItem)
		.filter(idx -> idx.intValue() >= 0)
		.ifPresent(idx -> {
			Nic nicTemp = rootCont.getNicTable().getItems().get(selectedItem);
			winCont.changeNicInfo(nicTemp);
		});
	}
}
