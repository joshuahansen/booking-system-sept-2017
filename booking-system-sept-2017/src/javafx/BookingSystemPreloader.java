package javafx;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Preloader for the booking system. Has loading bar and message so user knows system is loading 
 * @author SEPT Team 6
 *
 */
public class BookingSystemPreloader extends Preloader {
	private Stage preloaderStage;
	
	/**
	 * Start method to start the pre loader
	 */
	@Override
	    public void start(Stage primaryStage) throws Exception {
	       this.preloaderStage = primaryStage;
	   
	       VBox loading = new VBox(20);
	       loading.setMaxWidth(Region.USE_PREF_SIZE);
	       loading.setMaxHeight(Region.USE_PREF_SIZE);
	       loading.getChildren().add(new ProgressBar());
	       loading.getChildren().add(new Label("Loading Databases \nPlease wait..."));
	 
	       BorderPane root = new BorderPane(loading);
	       Scene scene = new Scene(root);
	 
	       scene.getStylesheets().add(getClass().getResource("bookingSystem.css").toExternalForm());
	       
	       primaryStage.setWidth(800);
	       primaryStage.setHeight(600);
	       primaryStage.setScene(scene);
	       primaryStage.initStyle(StageStyle.UNDECORATED);
	       primaryStage.show();
	   }
	 
	/**
	 * hides pre loader once main program has loaded.
	 */
	   @Override
	   public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
	      if (stateChangeNotification.getType() == Type.BEFORE_START) {
	         preloaderStage.hide();
	      }
	   }
}
