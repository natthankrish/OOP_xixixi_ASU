module program {
    requires javafx.controls;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.json;
    requires json.simple;
    requires annotations;
    requires com.google.gson;
    requires java.xml.bind;
    exports program;
    opens program.container to java.xml.bind;
    opens program.entities to java.xml.bind, com.sun.xml.bind;
}