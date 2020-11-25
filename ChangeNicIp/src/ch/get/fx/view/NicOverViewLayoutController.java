package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NicOverViewLayoutController implements Initializable {
	
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// 윈도우 컨트롤러
	private WindowController winCont = WindowController.getInstance();
	private Stage overViewStage;
	private boolean isOkClicked;
	
	// TextField
	@FXML
	private TextField nicKind;
	@FXML
	private TextField nicName;
	@FXML
	private TextField nicIp;
	@FXML
	private TextField nicMask;
	@FXML
	private TextField nicGate;
	@FXML
	private TextField nicDns01;
	@FXML
	private TextField nicDns02;
	@FXML
	private TextField nicInfo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void exitOverView() {
		isOkClicked = false;
		winCont.close(overViewStage);
	}

	@FXML
	private void handleOkClicked() {
//		printTextField();
		isOkClicked = true;
	}
	
	public boolean isOkClicked() {
		return isOkClicked;
	}
	
	public void setOverViewStage(Stage overViewStage) {
		this.overViewStage = overViewStage;
	}
	
	public void printTextField() {
		String textMsg = 
				"================================\n" +
				"용도 : " + nicKind.getText() + "\n" +
				"랜카드이름 : " + nicName.getText() + "\n" +
				"논리주소 : " + nicIp.getText() + "\n" +
				"서브넷 : " + nicMask.getText() + "\n" +
				"게이트웨이 : " + nicGate.getText() + "\n" +
				"주DNS : " + nicDns01.getText() + "\n" +
				"보조DNS : " + nicDns02.getText() + "\n" +
				"설명 : " + nicInfo.getText() + "\n" +
				"================================\n";

		System.out.println(textMsg);
	}
}
