package ch.get.fx;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jfoenix.controls.JFXTabPane;

import ch.get.fx.controller.TableDataSetController;
import ch.get.fx.controller.WindowController;
import ch.get.fx.model.NicInfoListWrapper;
import ch.get.fx.util.ListNets;
import ch.get.fx.view.NicOverViewLayoutController;
import ch.get.fx.view.RootLayoutController;
import ch.get.fx.view.TableViewLayoutController;
import ch.get.fx.view.ToolBarLayoutController;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
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
	public static TableViewLayoutController tableViewCont;
	
	// Logger
	public final static Logger LOG = Logger.getGlobal();
	
	private Stage primaStage;
	// view.RootLayout.fxml
	private JFXTabPane rootLayout;
	private double tabWidth = 40.0;
	// view.NicOverViewLayout.fxml
	private VBox overViewLayout;
	// view.ToolBarLayout.fxml
	private HBox toolBarLayout;
	// view.TableViewLayout.fxml
	private AnchorPane tableViewLayout;
	
	// Layout Properties
	public static final int TOOL_BAR_WIDTH = 80;
	public static final int TOOL_BAR_WIDTH_DISTANCE = 70;
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
		initTableViewLayout();
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
			rootLayout = (JFXTabPane) loader.load();
			rootLayout.setPrefSize(800, 500);
			
			// tooltip
			rootLayout.getTabs().get(0).setTooltip(new Tooltip("��ī�� ����"));
			
			// scene ���
			Scene scene = new Scene(rootLayout);
			primaStage.setScene(scene);
			primaStage.show();
			
			// ��Ʈ�ѷ� ����
			rootCont = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initTableViewLayout() {
		try {
			// fxml ���� ���̾ƿ� ������
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ApplicationStart.class.getResource("view/TableViewLayout.fxml"));
			tableViewLayout = (AnchorPane) loader.load();
			
			rootLayout.setTabMinWidth(tabWidth);
			rootLayout.setTabMaxWidth(tabWidth);
			rootLayout.setTabMinHeight(tabWidth);
			rootLayout.setTabMaxHeight(tabWidth);
			rootLayout.setRotateGraphic(true);
			rootLayout.getTabs().get(0).setContent(tableViewLayout);
			
			// ��Ʈ�ѷ� ����
			tableViewCont = loader.getController();
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
			toolBarCont.setMainApp(this);
			
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
			
			tableViewLayout.getChildren().add(toolBarLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// window prop
	public File getNicInfoFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(ApplicationStart.class);
//		prefs.remove("filePath"); // ���� ��� ����
		
		String filePath = prefs.get("filePath", null);	
		
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}
	
	public void setNicInfoFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(ApplicationStart.class);
		
		if (file != null) {
			prefs.put("filePath", file.getPath());
		} else {
			prefs.remove("filePath");
		}
	}

	/*
	 * JAXB I/O
	 */
	public void loadNicInfoDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(NicInfoListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			// File -> XML -> Instance
			NicInfoListWrapper infoListWrapper = (NicInfoListWrapper) um.unmarshal(file);
			tableCont.getNicData().clear();
			tableCont.getNicData().addAll(infoListWrapper.getNicInfo());
			
			// ���ϰ�� -> ������Ʈ��
			setNicInfoFilePath(file);
		} catch (Exception e) {
			windowCont.showAlert(
					"�б� ����", 
					"XML ������ �ҷ����� ���߽��ϴ�.", 
					"XML������ Ȯ���ϼ���.\n��� : " + file.getPath(), 
					AlertType.ERROR);
		}
	}
	
	public void saveNicInfoDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(NicInfoListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // ����ó �����͸� ���Ѵ�.
	        NicInfoListWrapper wrapper = new NicInfoListWrapper();
	        wrapper.setNicInfo(tableCont.getNicData());

	        // ������ �� XML�� ���Ͽ� �����Ѵ�.
	        m.marshal(wrapper, file);

	        // ���� ��θ� ������Ʈ���� �����Ѵ�.
	        setNicInfoFilePath(file);
	    } catch (Exception e) { // ���ܸ� ��´�.
			windowCont.showAlert(
					"���� ����", 
					"XML ������ �������� ���߽��ϴ�.", 
					"��θ� Ȯ�� �ϼ���.\n��� : " + file.getPath(), 
					AlertType.ERROR);
			
			e.printStackTrace();
	    }
	}
	
	/*
	 * Getter / Setter
	 */
	public Stage getPrimaStage() {
		return primaStage;
	}
}