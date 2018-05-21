package parallelcomputing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import parallelcomputing.animUtils.Walker;

/**
 * @Author: Tom Scholten & Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Function: ParallelComputing
 * @Date: May 17, 2018 2:55:39 PM
 * @version: 1.0
 */
public class ParallelComputing extends Application{
    final double x = 600.0;
    final double y = 300.0;

    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {
        Walker walker = new Walker(x, y);
    }

    @Override
    public void start(Stage pStage) throws Exception {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                300.0, 135.0, //front point
                320.0, 145.0, //Right point
                310.0, 155.0  //Left point
        });

        Group root = new Group(polygon);
        Scene scene = new Scene(root, 600, 300);

        pStage.setTitle("Test Application");
        pStage.setScene(scene);
        pStage.show();
    }
}
