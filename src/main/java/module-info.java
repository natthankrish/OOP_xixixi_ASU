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
    requires jakarta.xml.bind;
    exports program;
    opens program.containers to jakarta.xml.bind;
    opens program.entities to jakarta.xml.bind, com.sun.xml.bind;
    opens program.entities.clients to jakarta.xml.bind, com.sun.xml.bind, com.sun.xml.bind.core;
}