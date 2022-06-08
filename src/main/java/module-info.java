module com.example.mavenjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.jsoup;
    requires org.jgrapht.core;

    opens com.example.mavenjavafx to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example.mavenjavafx;
    exports com.timeTable;
    exports com.timeTable.classes;

}