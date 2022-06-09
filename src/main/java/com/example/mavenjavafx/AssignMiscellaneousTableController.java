package com.example.mavenjavafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
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
import java.util.regex.Pattern;

import static com.database.DataBaseController.dataBaseConnection;

public class AssignMiscellaneousTableController implements Initializable {

    double x, y;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<RoomTable, Integer> capacity;

    @FXML
    private TableColumn<RoomTable, Integer> chalks;

    @FXML
    private TableColumn<RoomTable, Integer> computers;

    @FXML
    private TableColumn<RoomTable, String> room;

    @FXML
    private TableColumn<RoomTable, Integer> sponges;

    @FXML
    private TableView<RoomTable> tableAssign;

    @FXML
    private TableColumn<RoomTable, String> type;

    @FXML
    private TableColumn<RoomTable, Integer> videoprojectors;

    @FXML
    private TextField videoprojectorsText;

    @FXML
    private TextField computersText;

    @FXML
    private TextField chalksText;

    @FXML
    private TextField spongesText;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<RoomTable> list = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        room.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("room"));
        type.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("type"));
        capacity.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("capacity"));
        chalks.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("chalks"));
        sponges.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("sponges"));
        videoprojectors.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("videoprojectors"));
        computers.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("computers"));

        wrapText();
        addTextLimiter(chalksText,3);
        addTextLimiter(videoprojectorsText,1);
        addTextLimiter(spongesText,2);
        addTextLimiter(computersText,2);

        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableAssign.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(tableAssign.getSelectionModel().getSelectedItem().getRoom());
            }
        });
    }



    private void loadDate() throws SQLException {
        list.clear();

        connection = dataBaseConnection.getConnection();
        query = "SELECT * FROM RoomsInitial";
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new RoomTable(
                            resultSet.getString("room"),
                            resultSet.getString("type"),
                            resultSet.getInt("capacity"),
                            resultSet.getInt("chalk"),
                            resultSet.getInt("sponge"),
                            resultSet.getInt("videoprojector"),
                            resultSet.getInt("computer")
                    )
            );
        }

        tableAssign.setItems(list);
    }


    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
                if(!(Pattern.compile("[0-9]+").matcher(tf.getText())).matches()){
                    String s = "";
                    tf.setText(s);
                }
            }
        });
    }

    private void wrapText() {

        room.setCellFactory(param -> new TableCell<RoomTable, String>() {
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

        type.setCellFactory(param -> new TableCell<RoomTable, String>() {
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

        capacity.setCellFactory(param -> new TableCell<RoomTable, Integer>() {
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

        chalks.setCellFactory(param -> new TableCell<RoomTable, Integer>() {
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

        sponges.setCellFactory(param -> new TableCell<RoomTable, Integer>() {
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

        videoprojectors.setCellFactory(param -> new TableCell<RoomTable, Integer>() {
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

        computers.setCellFactory(param -> new TableCell<RoomTable, Integer>() {
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

    public void switchToDisciplines(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("miscellaneous.fxml"));


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
