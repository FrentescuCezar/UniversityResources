package com.example.mavenjavafx;

import com.database.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.database.DataBaseController.dataBaseConnection;

public class DisciplineTableController implements Initializable {

    double x, y;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<DisciplineTable, String> Discipline;

    @FXML
    private TableColumn<DisciplineTable, String> Teacher;

    @FXML
    private TableView<DisciplineTable> tableDiscipline;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    ObservableList<DisciplineTable> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Discipline.setCellValueFactory(new PropertyValueFactory<DisciplineTable, String>("Discipline"));
        Teacher.setCellValueFactory(new PropertyValueFactory<DisciplineTable, String>("Teacher"));

        wrapText();

        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadDate() throws SQLException {
        list.clear();

        query = "SELECT * FROM Disciplines";
        resultSet = DataBaseController.selectSQL(query);

        while (resultSet.next()) {
            list.add(new DisciplineTable(
                            resultSet.getString("Discipline"),
                            resultSet.getString("Teacher")
                    )
            );
        }

        tableDiscipline.setItems(list);
    }

    private void wrapText() {
        Discipline.setCellFactory(param -> new TableCell<DisciplineTable, String>() {
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

        Teacher.setCellFactory(param -> new TableCell<DisciplineTable, String>() {
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
    }

    public void switchToResources(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("resources.fxml"));


        root.setOnMousePressed(evt -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    public void switchToClasses(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("miscellaneous-classes.fxml"));


        root.setOnMousePressed(evt -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    public void switchToMiscellaneous(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("miscellaneous-miscellaneous.fxml"));


        root.setOnMousePressed(evt -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }
}
