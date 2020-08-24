package ch.get.fx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationStart extends Application {
	// Logger
	public final static Logger LOG = Logger.getGlobal();
	
	private Stage primaStage;
	// view.RootLayout.fxml
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaStage = primaryStage;
		this.primaStage.setTitle("NIC IP 변환기");
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
			// fxml 에서 레이아웃 가져옴
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ApplicationStart.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// scene 출력
			Scene scene = new Scene(rootLayout);
			primaStage.setScene(scene);
			primaStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}