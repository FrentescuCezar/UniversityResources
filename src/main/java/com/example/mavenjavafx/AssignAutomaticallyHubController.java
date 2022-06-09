package com.example.mavenjavafx;

import com.database.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AssignAutomaticallyHubController implements Initializable {
    double x, y;
    private Stage stage;
    private Scene scene;
    private Parent root;

    boolean isTableAssigned = false;

    @FXML
    private TableColumn<MiscellaneousTable, Integer> Chalk;

    @FXML
    private TableColumn<MiscellaneousTable, Integer> Computer;

    @FXML
    private TableColumn<MiscellaneousTable, Integer> Sponge;

    @FXML
    private TableColumn<MiscellaneousTable, Integer> Videoprojector;

    @FXML
    private TableView<MiscellaneousTable> tableAssign;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<MiscellaneousTable> list = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Chalk.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Chalk"));
        Sponge.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Sponge"));
        Videoprojector.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Videoprojector"));
        Computer.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Computer"));

        wrapText();

        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableAssign.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Chalks total : " + tableAssign.getSelectionModel().getSelectedItem().getChalk());
                System.out.println("Sponges total : " + tableAssign.getSelectionModel().getSelectedItem().getSponge());
                System.out.println("Video-projectors total : " + tableAssign.getSelectionModel().getSelectedItem().getVideoprojector());
                System.out.println("Computers total : " + tableAssign.getSelectionModel().getSelectedItem().getChalk());
                System.out.println();
            }
        });
    }


    private void loadDate() throws SQLException {
        list.clear();

        query = "SELECT * FROM Miscellaneous";
        resultSet = DataBaseController.selectSQL(query);

        while (resultSet.next()) {
            list.add(new MiscellaneousTable(
                            resultSet.getInt("chalk"),
                            resultSet.getInt("sponge"),
                            resultSet.getInt("videoprojector"),
                            resultSet.getInt("computer")
                    )
            );
        }

        tableAssign.setItems(list);
    }


    private void wrapText() {

        Chalk.setCellFactory(param -> new TableCell<MiscellaneousTable, Integer>() {
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

        Sponge.setCellFactory(param -> new TableCell<MiscellaneousTable, Integer>() {
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

        Videoprojector.setCellFactory(param -> new TableCell<MiscellaneousTable, Integer>() {
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

        Computer.setCellFactory(param -> new TableCell<MiscellaneousTable, Integer>() {
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

    public void switchToAutomatically(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("assign_hub.fxml"));


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

    public void switchToManual(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("assign.fxml"));


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

    public void switchToAutomaticallyClasses(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("assign_automatically_classes.fxml"));

        isTableAssigned = true;


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

    public void switchToAutomaticallyMiscellaneous(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("assign_automatically_miscellaneous.fxml"));


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
