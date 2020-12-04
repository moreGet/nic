package ch.get.fx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.util.ListNets;
import ch.get.fx.view.NicOverViewLayoutController;
import ch.get.fx.view.RootLayoutController;
import ch.get.fx.view.ToolBarLayoutController;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationStart extends Application {
	// INIT INSTANCE
	public static WindowController windowCont; 
	public static TableDataSetController tableCont;
	public static ListNets listNet;
	public static NicOverViewLayoutController nicCont;
	public static RootLayoutController rootCont;
	public static ToolBarLayoutController toolBarCont;
	
	// Logger
	public final static Logger LOG = Logger.getGlobal();
	
	private Stage primaStage;
	// view.RootLayout.fxml
	private AnchorPane rootLayout;
	// view.NicOverViewLayout.fxml
	private VBox overViewLayout;
	// view.ToolBarLayout.fxml
	private HBox toolBarLayout;
	private TabPane tabPaneLayout;
	
	// Layout Properties
	public static final int TOOL_BAR_WIDTH = 80;
	public static final int TOOL_BAR_TRANS_DISTANCE = 70;
	public static final int TOOL_BAR_HEIGHT = 40;
	public static final int TOOL_BAR_HEIGHT_DISTANCE = 30;
	
	@Override
	public void start(Stage primaryStage) {
		// INIT INSTANCE
		windowCont = WindowController.getInstance();
		tableCont = TableDataSetController.getInstance();
		listNet = ListNets.getInstance();
		
		// INPUT DATA TO INSTANCE
		windowCont.setMainApp(this); // init Main App
		windowCont.setListNets(listNet);
		
		this.primaStage = primaryStage;
		this.primaStage.setTitle("NIC IP ��ȯ��");
		this.primaStage.setResizable(true);
		
		LOG.setLevel(Level.INFO);
		LOG.info("MAIN START STEP 1 [ " + Thread.currentThread().getName() + " ] " + " [ " + "INIT LAY_OUT ROOT" + " ] ");
		initRootLayout();
		initToolBarLayout();
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
			tabPaneLayout = (TabPane) loader.load();
			
			// scene ���
			Scene scene = new Scene(tabPaneLayout);
			primaStage.setScene(scene);
			primaStage.show();
			
			// ��Ʈ�ѷ� ����
			rootCont = (RootLayoutController) loader.getController();
			// TabPane ���� rootLayout�� id ������ ����
			rootLayout = rootCont.getRootLayout();
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
			nicCont = loader.getController();
			
			// scene ���
			Stage stage = new Stage();
			nicCont.setOverViewStage(stage);
			Scene scene = new Scene(overViewLayout);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("��ī�� ���� ���");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaStage);
			stage.showAndWait();
			
			return nicCont.isOkClicked(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	// toolBar
	public void initToolBarLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ApplicationStart.class.getResource("view/ToolBarLayout.fxml"));
			toolBarLayout = (HBox) loader.load();
			toolBarCont = loader.getController();
			
			toolBarLayout.prefWidthProperty().bind(rootLayout.widthProperty());
			AnchorPane.setBottomAnchor(toolBarLayout, 0.0);
			toolBarLayout.setPrefHeight(TOOL_BAR_HEIGHT);
			
			// Animation ����
			toolBarLayout.setTranslateY(TOOL_BAR_HEIGHT_DISTANCE);
			TranslateTransition transition = new TranslateTransition(Duration.millis(500), toolBarLayout);
			transition.setFromY(TOOL_BAR_HEIGHT_DISTANCE);
			transition.setToY(0);
			
			// ���� -> ������ �̵� �ִϸ��̼�
			toolBarLayout.setOnMouseEntered(e -> {
				transition.setRate(1);
				transition.play();
			});
			
			// ������ -> �������� �̵� �ִϸ��̼�
			toolBarLayout.setOnMouseExited(e -> {
				transition.setRate(-1);
				transition.play();
			});
			
			rootLayout.getChildren().add(toolBarLayout);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}