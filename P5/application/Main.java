package application;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Label greetingLabel = new Label("CS400 MyFirstJavaFX");
			// combo box
			ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
			final ComboBox comboBox = new ComboBox(options);
			// Image view
			Image pic=new Image("myFace.jpg");
			ImageView imageView=new ImageView(pic);
			imageView.setFitHeight(200);
			imageView.setFitWidth(170);
			//button
			Button b=new Button("Cancel");
			//Text Field
			Label label1 = new Label("Name:");
			TextField textField = new TextField ();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 500, 500);
			root.setLeft(comboBox);
			root.setTop(greetingLabel);
			root.setCenter(imageView);
			root.setBottom(b);
			root.setRight(textField);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Ojas' first JavaFX program");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// use command-line here, before calling launch
		launch(args);
	}
}
