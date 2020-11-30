package ch.get.fx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.view.NicOverViewLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApplicationStart extends Application {
	// INIT INSTANCE
	private static WindowController windowCont = WindowController.getInstance();
	private static TableDataSetController tableCont = TableDataSetController.getInstance();
	
	// Logger
	public final static Logger LOG = Logger.getGlobal();
	
	private Stage primaStage;
	// view.RootLayout.fxml
	private BorderPane rootLayout;
	// view.NicOverViewLayout.fxml
	private VBox overViewLayout;
	
	@Override
	public void start(Stage primaryStage) {
		windowCont.setMainApp(this); // init Main App
		this.primaStage = primaryStage;
		this.primaStage.setTitle("NIC IP ��ȯ��");
		this.primaStage.setResizable(true);
		
		LOG.setLevel(Level.INFO);
		LOG.info("MAIN START STEP 1 [ " + Thread.currentThread().getName() + " ] " + " [ " + "INIT LAY_OUT ROOT" + " ] ");
		initRootLayout();
		LOG.info("MAIN START STEP 5 [ " + Thread.currentThread().getName() + " ] " + " [ " + "INIT LAY_OUT FINISH" + " ] ");
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initRootLayout() {
		
		try {
			// fxml ���� ���̾ƿ� ������
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ApplicationStart.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// scene ���
			Scene scene = new Scene(rootLayout);
			primaStage.setScene(scene);
			primaStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// NIC �������
	public boolean initNicOverViewLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ApplicationStart.class.getResource("view/NicOverViewLayout.fxml"));
			overViewLayout = (VBox) loader.load();
			NicOverViewLayoutController cont = loader.getController();
			
			// scene ���
			Stage stage = new Stage();
			cont.setOverViewStage(stage);
			Scene scene = new Scene(overViewLayout);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("��ī�� ���� ���");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaStage);
			stage.showAndWait();
			
			return cont.isOkClicked(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
}