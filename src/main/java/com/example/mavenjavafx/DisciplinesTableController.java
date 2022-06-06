package com.example.mavenjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisciplinesTableController implements Initializable {

    double x,y;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<DisciplinesTable> tableDisciplines;

    @FXML
    private TableColumn<DisciplinesTable, String> Discipline2;

    @FXML
    private TableColumn<DisciplinesTable, String> Teacher2;

    ObservableList<DisciplinesTable> list = FXCollections.observableArrayList(
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel"),
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel"),
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel"),
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel"),
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel"),
            new DisciplinesTable("Programare orientata-obiect","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Discipline2.setCellValueFactory(new PropertyValueFactory<DisciplinesTable,String>("Discipline2"));
        Teacher2.setCellValueFactory(new PropertyValueFactory<DisciplinesTable,String>("Teacher2"));

        Discipline2.setCellFactory(param -> new TableCell<DisciplinesTable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Teacher2.setCellFactory(param -> new TableCell<DisciplinesTable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        tableDisciplines.setItems(list);
    }

    public void switchToResources(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("resources.fxml"));


        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    public void switchToClasses(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("miscellaneous_classes.fxml"));


        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    public void switchToMiscellaneous(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("miscellaneous_miscellaneous.fxml"));


        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }


}
