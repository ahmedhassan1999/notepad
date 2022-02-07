/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JFX;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author amr
 */
public class Exampl1 extends Application{
    TextArea textArea = new TextArea();
    Label labelstatus = new Label("Not Started");
    
    Button startbtn = new Button("start");
    Button exitbtn = new Button("exit");

    @Override
    public void start(Stage primaryStage) {
        startbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runtask();
            }
        });
        
        
        exitbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        FlowPane ro = new FlowPane(Orientation.HORIZONTAL);
        ro.getChildren().add(startbtn);
        ro.getChildren().add(exitbtn);
        root.getChildren().add(labelstatus);
        root.getChildren().add(ro);
        root.getChildren().add(textArea);
        Scene scene = new Scene(root, 500, 300);
        
        
        primaryStage.setTitle("Hello JFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void runtask(){
        for (int i = 1; i <= 10; i++) {
            try {
                String status = "proccessing "+ i + " of "+10;
                labelstatus.setText(status);
                    System.out.println(status);
                textArea.appendText(status+"\n");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Exampl1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }  
}