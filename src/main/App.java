package main;
	
import javafx.application.Application;
import scenes.Menu;
import javafx.stage.Stage;


public class App extends Application {
	@Override
	public void start(Stage start) throws Exception{
		@SuppressWarnings("unused")
		Menu gameStart = new Menu();
		gameStart.setMenu(start);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
