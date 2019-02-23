/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompoints;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author mb
 */
public final class RandomPointsFXMLController implements Initializable {

    private static final int    CIRCLE_DIAMETER = 200;
    private static final int    CIRCLE_RADIUS = CIRCLE_DIAMETER / 2;
    private static final double TAU = java.lang.Math.PI * 2.0;

    private static final java.util.Random Rand = new java.util.Random();


    @FXML
    private Canvas canvas;

    /**
     * @param event Ignored
     */
    @FXML
    private void clear(ActionEvent event) {
        System.out.println("Now I will clear the canvas...");

        final GraphicsContext graphCon = canvas.getGraphicsContext2D();

        // First clear whole area, not just the circle.
        // This is important as points can draw outside the circle area.
        // https://stackoverflow.com/a/27204962/
        graphCon.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        graphCon.setFill(Color.BLACK);
        graphCon.setStroke(Color.BLACK);
        graphCon.setLineWidth(10.0);
        graphCon.fillOval(0.0, 0.0, CIRCLE_DIAMETER, CIRCLE_DIAMETER); // x y width height
    }


    @FXML
    private void goNaive(ActionEvent event) {
        System.out.println("Now I will draw to the canvas...");

        final GraphicsContext graphCon = canvas.getGraphicsContext2D();
        graphCon.setFill(Color.WHITE);
        graphCon.setStroke(Color.WHITE);

        for (int i = 0 ; i != 200; ++i)
        {
            // ignore that it should be [0.0, 1.0) not [0.0 to 1.0] from nextDouble
            final double theta_radians
                    = RandomPointsFXMLController.Rand.nextDouble()
                    * RandomPointsFXMLController.TAU;

            final double r = RandomPointsFXMLController.Rand.nextDouble() * CIRCLE_RADIUS;

            final double offset_x = r * java.lang.Math.sin(theta_radians);
            final double offset_y = r * java.lang.Math.cos(theta_radians);

            final double coord_x = offset_x + CIRCLE_RADIUS;
            final double coord_y = offset_y + CIRCLE_RADIUS;

            // Take the chosen point and draw it using a small circle,
            // of diameter 3.0. Careful to offset by 1.0 (*not* 1.5).
            graphCon.fillRect(coord_x - 1.0, coord_y - 1.0, 3.0, 3.0); // x, y, w, h
        }

    }


    @FXML
    private void goSqrt(ActionEvent event) {
        System.out.println("Now I will draw to the canvas...");

        final GraphicsContext graphCon = canvas.getGraphicsContext2D();
        graphCon.setFill(Color.RED);
        graphCon.setStroke(Color.RED);

        for (int i = 0 ; i != 200; ++i)
        {
            // ignore that it should be [0.0, 1.0) not [0.0 to 1.0] from nextDouble
            final double theta_radians
                    = RandomPointsFXMLController.Rand.nextDouble()
                    * RandomPointsFXMLController.TAU;

            final double seed = RandomPointsFXMLController.Rand.nextDouble();
            final double r = CIRCLE_RADIUS * java.lang.Math.sqrt(seed);

            final double offset_x = r * java.lang.Math.sin(theta_radians);
            final double offset_y = r * java.lang.Math.cos(theta_radians);

            final double coord_x = offset_x + CIRCLE_RADIUS;
            final double coord_y = offset_y + CIRCLE_RADIUS;

            // Take the chosen point and draw it using a small circle,
            // of diameter 3.0. Careful to offset by 1.0 (*not* 1.5).
            graphCon.fillRect(coord_x - 1.0, coord_y - 1.0, 3.0, 3.0); // x, y, w, h
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RandomPointsFXMLController.Rand.nextDouble(); // churn up the RNG
        RandomPointsFXMLController.Rand.nextDouble();
        RandomPointsFXMLController.Rand.nextDouble();
        RandomPointsFXMLController.Rand.nextDouble();

        this.clear(null);
    }

}
