package com.example.mavenjavafx;

import com.database.DataBaseController;
import com.timeTable.classes.Miscellaneous;
import javafx.application.Platform;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static com.database.DataBaseController.dataBaseConnection;

public class AssignManualTableController implements Initializable {

    double x, y;
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    ObservableList<MiscellaneousTable> list = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Chalk.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Chalk"));
        Sponge.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Sponge"));
        Videoprojector.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Videoprojector"));
        Computer.setCellValueFactory(new PropertyValueFactory<MiscellaneousTable, Integer>("Computer"));

        wrapText();
        addTextLimiter(chalksText, 4);
        addTextLimiter(videoprojectorsText, 2);
        addTextLimiter(spongesText, 3);
        addTextLimiter(computersText, 3);

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

    @FXML
    private void update(ActionEvent event) throws SQLException, FileNotFoundException {

        String chalks = chalksText.getText();
        String sponges = spongesText.getText();
        String videoprojectors = videoprojectorsText.getText();
        String computers = computersText.getText();

        if (chalks.matches("[0-9]+")
                && sponges.matches("[0-9]+")
                && videoprojectors.matches("[0-9]+")
                && computers.matches("[0-9]+")){

            query = "UPDATE Miscellaneous SET chalk = " + chalks
                    + ",sponge = " + sponges
                    + ",videoprojector = " + videoprojectors
                    + ",computer = " + computers
                    + " WHERE id = 1";

            preparedStatement = dataBaseConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();

            Miscellaneous.getInstance().setTotalNumberOfChalk(Integer.parseInt(chalks));
            Miscellaneous.getInstance().setTotalNumberOfSponges(Integer.parseInt(sponges));
            Miscellaneous.getInstance().setTotalNumberOfVideoProjectors(Integer.parseInt(videoprojectors));
            Miscellaneous.getInstance().setTotalNumberOfComputers(Integer.parseInt(computers));


            System.out.println(Miscellaneous.getInstance().getTotalNumberOfChalk());
            //ResourcesAlgorithm resourcesAlgorithm = new ResourcesAlgorithm();
            //Graph<Event, Edge> eventsGraph;
            //eventsGraph = resourcesAlgorithm.startAssignClasses();



            loadDate();
        }
        else if (chalks.matches("[0-9]+")){
            query = "UPDATE Miscellaneous SET chalk = " + chalks
                    + " WHERE id = 1";

            preparedStatement = dataBaseConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            loadDate();
        }
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


    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
                if (Pattern.compile("[a-zA-Z]").matcher(tf.getText()).find()) {
                    Platform.runLater(() -> {
                        String s = "";
                        tf.setText(s);
                    });

                }
            }
        });
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

}
