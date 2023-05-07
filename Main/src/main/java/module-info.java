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
    requires kernel;
    requires layout;
    requires io;

    opens org.example.program;
    opens org.example.program.containers to jakarta.xml.bind;
    opens org.example.program.entities.commodities to jakarta.xml.bind, com.sun.xml.bind;
    opens org.example.program.entities.clients to jakarta.xml.bind, com.sun.xml.bind, com.sun.xml.bind.core;
    exports org.example.program;
    exports org.example.program.plugin;
    exports org.example.program.topbar;
    exports org.example.program.page;
    exports org.example.program.containers;
    exports org.example.program.entities.clients;
    exports org.example.program.entities.commodities;
    exports org.example.program.entities.bills;
    opens org.example.program.entities.bills to com.sun.xml.bind, com.sun.xml.bind.core, jakarta.xml.bind;
}