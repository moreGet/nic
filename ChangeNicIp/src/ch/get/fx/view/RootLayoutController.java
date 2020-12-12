package ch.get.fx.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ch.get.fx.ApplicationStart;
import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import javafx.fxml.Initializable;

public class RootLayoutController implements Initializable{
	// 싱글톤
	public static RootLayoutController instance;
	public final static Logger log = Logger.getGlobal();
	// 테이블 컨트롤러
	private TableDataSetController tableDatCont;
	// 윈도우 컨트롤러
	private WindowController winCont;
	// MainInstance
	private ApplicationStart mainApp;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		tableDatCont = ApplicationStart.tableCont;
		winCont = ApplicationStart.windowCont;
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