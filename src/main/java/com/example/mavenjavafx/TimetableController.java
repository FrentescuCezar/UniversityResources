package com.example.mavenjavafx;


import com.database.DataBaseConnection;
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

public class TimetableController implements Initializable {

    double x, y;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<Timetable, Integer> Capacity;

    @FXML
    private TableColumn<Timetable, String> Day;

    @FXML
    private TableColumn<Timetable, String> Discipline;

    @FXML
    private TableColumn<Timetable, String> End;

    @FXML
    private TableColumn<Timetable, String> Room;

    @FXML
    private TableColumn<Timetable, String> Start;

    @FXML
    private TableColumn<Timetable, String> Teacher;

    @FXML
    private TableColumn<Timetable, String> Timetable;

    @FXML
    private TableColumn<Timetable, String> Type;

    @FXML
    private TableView<Timetable> table;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Timetable timetable = null;


    ObservableList<Timetable> list = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timetable.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Timetable"));
        Day.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Day"));
        Start.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<Timetable, String>("End"));
        Discipline.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Discipline"));
        Type.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Type"));
        Teacher.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Teacher"));
        Room.setCellValueFactory(new PropertyValueFactory<Timetable, String>("Room"));
        Capacity.setCellValueFactory(new PropertyValueFactory<Timetable, Integer>("Capacity"));

        wrapText();

        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    private void loadDate() throws SQLException {
        list.clear();

        connection = dataBaseConnection.getConnection();
        query = "SELECT * FROM TimeTableInitial";
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new Timetable(
                            resultSet.getString("TimeTable"),
                            resultSet.getString("day"),
                            resultSet.getString("start"),
                            resultSet.getString("end"),
                            resultSet.getString("discipline"),
                            resultSet.getString("type"),
                            resultSet.getString("teacher"),
                            resultSet.getString("room"),
                            resultSet.getInt("capacity")
                    )
            );
        }

        table.setItems(list);
    }

    private void wrapText() {
        Timetable.setCellFactory(param -> new TableCell<Timetable, String>() {
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

        Teacher.setCellFactory(param -> new TableCell<Timetable, String>() {
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

        Discipline.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Day.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Start.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        End.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Type.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Room.setCellFactory(param -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });

        Capacity.setCellFactory(param -> new TableCell<Timetable, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item.toString());
                    text.setStyle("-fx-text-alignment:justify;");
                    text.setStyle("-fx-text-fill: white");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                    setGraphic(text);
                }
            }
        });
    }
}
