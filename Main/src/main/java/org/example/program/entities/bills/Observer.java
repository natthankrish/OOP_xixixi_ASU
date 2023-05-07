package org.example.program.entities.bills;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ObserverXMLAdapter.class)
public interface Observer {
    void update();
}
