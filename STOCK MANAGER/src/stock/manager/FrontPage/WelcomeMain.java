package stock.manager.FrontPage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import stock.manager.FrontPage.Welcome;
import java.awt.*;


public class WelcomeMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(WelcomeMain.class.getResource("/stock/manager/FrontPage/Welcome.fxml"));
        Parent root = loader.load();

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width =screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(root,width,height);
        stage.setScene(scene);
        stage.show();

        Welcome controller = loader.getController();

        double msgWidth = controller.showText.getLayoutBounds().getWidth();

        KeyValue initKeyValue = new KeyValue(controller.showText.translateXProperty(),width);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO,initKeyValue);

        KeyValue endKeyValue = new KeyValue(controller.showText.translateXProperty(),-1.0*msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(25),endKeyValue);
        Timeline timeline = new Timeline(initFrame,endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

}
