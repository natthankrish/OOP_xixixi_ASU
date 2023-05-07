module MainProgram {
    requires javafx.graphics;
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.json;
    requires json.simple;
    requires annotations;
    requires com.google.gson;
    requires jakarta.xml.bind;

    opens org.example.program;
    exports org.example.program;
    exports org.example.program.entities;
    exports org.example.program.plugin;
    exports org.example.program.topbar;
    exports org.example.program.page;
    exports org.example.program.containers;
}