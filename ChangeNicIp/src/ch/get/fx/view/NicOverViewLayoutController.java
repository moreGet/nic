package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import javafx.fxml.Initializable;

public class NicOverViewLayoutController implements Initializable {
	
	public final static Logger log = Logger.getGlobal();
	// ���̺� ��Ʈ�ѷ�
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// ������ ��Ʈ�ѷ�
	private WindowController winCont = WindowController.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
