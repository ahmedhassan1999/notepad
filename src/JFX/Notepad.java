
package JFX;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package mynotepad;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author amr
 */
public class Notepad extends Application {
    MenuBar mb;
    Menu file,edit,help;
    BorderPane root;
    TextArea ta;
    MenuItem neew,open,save,exit,undo,cut,copy,paste,del,selAll,about;
    Scene scene;
    Dialog<String> dialog;
    
   @Override
    public void init(){
        neew = new MenuItem("New");
        open = new MenuItem("Open");
        save = new MenuItem("Save");
        exit = new MenuItem("Exit");
        undo = new MenuItem("Undo");
        cut = new MenuItem("Cut");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        del = new MenuItem("Delete");
        selAll = new MenuItem("Select All");
        about = new MenuItem("Apout Notepad");
        
        file = new Menu("File");
        edit = new Menu("Edit");
        help = new Menu("Help");
        
        mb = new MenuBar();
        
        root = new BorderPane();
        
        ta = new TextArea();
        
        file.getItems().addAll(neew,open,save,new SeparatorMenuItem(),exit);
        edit.getItems().addAll(undo,new SeparatorMenuItem(),cut,copy,paste,del,new SeparatorMenuItem(),selAll);
        help.getItems().addAll(about);
        
        mb.getMenus().addAll(file,edit,help);
        
        root.setTop(mb);
        root.setCenter(ta);
        root.setBottom(new Label("hi"));
        
        scene = new Scene(root, 500, 500);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
 exit.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        
undo.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.undo();
            }
        });
        
        cut.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.cut();
            }
        });
        
        copy.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.copy();
            }
        });
        
        paste.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.paste();
            }
        });
        
        del.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.deleteText(ta.getSelection());
            }
        });
        
        selAll.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                ta.selectAll();
            }
        });

 about.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
         dialog = new Dialog<String>();
          ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
         dialog.getDialogPane().getButtonTypes().add(type);
                dialog.setTitle("About");
                dialog.setContentText("This is my notepad");
                dialog.show();
    
            }
        });
open.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                FileChooser fil_chooser = new FileChooser();
                File file = fil_chooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        FileInputStream in = new FileInputStream(file);
                        int count = in.available();  
                        byte[] ary = new byte[count];  
                        in.read(ary);
                        ta.setText(new String(ary));
                        in.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
  
        save.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                dialog.setTitle("save");
                DirectoryChooser dir_chooser = new DirectoryChooser();
                File file = dir_chooser.showDialog(primaryStage); 
                if (file != null) {
                    dialog.setContentText(file.getAbsolutePath());
                    TextInputDialog td = new TextInputDialog();
                    td.setHeaderText("enter file name");
                    td.showAndWait();
                    try {
                        file = new File(file.getAbsolutePath()+"/"+td.getEditor().getText());
                        if(file.createNewFile()){
                            FileOutputStream ou = new FileOutputStream(file);
                            byte[] strToBytes = ta.getText().getBytes();
                            ou.write(strToBytes);
                            ou.close();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });     
        primaryStage.setTitle("FX Notepad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
   
    
}
