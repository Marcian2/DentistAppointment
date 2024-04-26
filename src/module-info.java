module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    opens GUI to javafx.fxml;
    opens Main to javafx.fxml;
    exports GUI;
    exports Main;
    opens to
    exports;
}