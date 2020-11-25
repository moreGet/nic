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
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// ������ ��Ʈ�ѷ�
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
				"�뵵 : " + nicKind.getText() + "\n" +
				"��ī���̸� : " + nicName.getText() + "\n" +
				"���ּ� : " + nicIp.getText() + "\n" +
				"����� : " + nicMask.getText() + "\n" +
				"����Ʈ���� : " + nicGate.getText() + "\n" +
				"��DNS : " + nicDns01.getText() + "\n" +
				"����DNS : " + nicDns02.getText() + "\n" +
				"���� : " + nicInfo.getText() + "\n" +
				"================================\n";

		System.out.println(textMsg);
	}
}
