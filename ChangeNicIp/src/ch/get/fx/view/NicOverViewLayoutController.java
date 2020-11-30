package ch.get.fx.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.Nic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NicOverViewLayoutController implements Initializable {
	
	// IP정규 표현식
	private String ipRegx = "((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}";
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// 윈도우 컨트롤러
	private WindowController winCont = WindowController.getInstance();
	private Stage overViewStage;
	private boolean isOkClicked;
	// Nic데이터
	private Nic nic;
	
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
		ArrayList<TextField> list = new ArrayList<TextField>(
				Arrays.asList(nicName, nicIp, nicMask, nicGate, nicDns01, nicDns02));
		
		// IP 유효성 검사
		for (TextField textField : list) {
			boolean vaildRegx = Pattern.matches(ipRegx, textField.getText());
			if (vaildRegx) {
				isOkClicked = true;
				nic.setNicKind(nicKind.getText());
				nic.setNicName(nicName.getText());
				nic.setNicIp(nicIp.getText());
				nic.setNicMask(nicMask.getText());
				nic.setNicGate(nicGate.getText());
				nic.setNicDns01(nicDns01.getText());
				nic.setNicDns02(nicDns02.getText());
				nic.setNicInfo(nicInfo.getText());
				winCont.close(overViewStage);
			} else {
				winCont.showAlert("IP범위를 벗어남", textField.getText(), "논리주소 필드를 확인해 주세요.");
				nicIp.requestFocus();
				return;
			}
		}
	}
	
	public boolean isOkClicked() {
		return isOkClicked;
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

	public Nic getNic() {
		return nic;
	}

	public void setOverViewStage(Stage overViewStage) {
		this.overViewStage = overViewStage;
	}
}
