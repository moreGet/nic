package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import javafx.fxml.Initializable;

public class NicOverViewLayoutController implements Initializable {
	
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont = TableDataSetController.getInstance();
	// 윈도우 컨트롤러
	private WindowController winCont = WindowController.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
