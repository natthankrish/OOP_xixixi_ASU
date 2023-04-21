module program {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires json;
    requires json.simple;

    opens program to javafx.fxml;
    exports program;
}