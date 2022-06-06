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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {

    double x,y;
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


    ObservableList<Timetable> list = FXCollections.observableArrayList(
            new Timetable("Orar Informatica, anul 1","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel","C2",0),
            new Timetable("Orar Master ingineria sistemelor soft, anul 2","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 3","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos, Prof.dr. Lucanu Dorel","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0),
            new Timetable("Orar Informatica, anul 4","Luni","08:00","10:00","Programare orientata-obiect","C","Conf. dr. Gavrilut Dragos","C2",0)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timetable.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Timetable"));
        Day.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Day"));
        Start.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<Timetable,String>("End"));
        Discipline.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Discipline"));
        Type.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Type"));
        Teacher.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Teacher"));
        Room.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Room"));
        Capacity.setCellValueFactory(new PropertyValueFactory<Timetable,Integer>("Capacity"));

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

        table.setItems(list);
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
}
