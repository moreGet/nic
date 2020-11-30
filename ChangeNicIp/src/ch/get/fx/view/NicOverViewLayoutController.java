package ch.get.fx.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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
	// �̱���
	public static NicOverViewLayoutController instance;
	// IP���� ǥ����
	private String ipRegx = "((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}";
	public final static Logger log = Logger.getGlobal();
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// ������ ��Ʈ�ѷ�
	private WindowController winCont = WindowController.getInstance();
	private Stage overViewStage;
	private boolean isOkClicked;
	// Nic������
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
		instance = this;
	}
	
	@FXML
	private void exitOverView() {
		isOkClicked = false;
		winCont.close(overViewStage);
	}

	@FXML
	private void handleOkClicked() {
//		printTextField();		
		// �ʼ� ��
		ArrayList<TextField> mandatoryList = new ArrayList<TextField>(
				Arrays.asList(nicIp, nicMask, nicGate, nicDns01));
		
		// ���� ��
		ArrayList<TextField> optinalList = new ArrayList<TextField>(
				Arrays.asList(nicName, nicKind, nicDns02, nicInfo));
		
		// IP ��ȿ�� �˻�
		for (TextField textField : mandatoryList) {
			boolean vaildRegx = Pattern.matches(ipRegx, textField.getText());
			if (!vaildRegx) {
				winCont.showAlert("IP������ ���", textField.getText(), "���ּ� �ʵ带 Ȯ���� �ּ���.");
				textField.requestFocus();
				return;
			}
		}
		
		String text = null;
		for (TextField textField : optinalList) {
			text = Optional.ofNullable(textField.getText())
								.filter(value -> value.length() > 0)
								.orElse("Default Value");
			
			textField.setText(text);
		}
		
		nic = new Nic();
		isOkClicked = true;
		nic.setNicKind(nicKind.getText());
		nic.setNicName(nicName.getText());
		nic.setNicIp(nicIp.getText());
		nic.setNicMask(nicMask.getText());
		nic.setNicGate(nicGate.getText());
		nic.setNicDns01(nicDns01.getText());
		nic.setNicDns02(nicDns02.getText());
		nic.setNicInfo(nicInfo.getText());
		nic.setSelNic(false);
		winCont.close(overViewStage);
	}
	
	public boolean isOkClicked() {
		return isOkClicked;
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

	public Nic getNic() {
		return nic;
	}

	public void setOverViewStage(Stage overViewStage) {
		this.overViewStage = overViewStage;
	}
}
