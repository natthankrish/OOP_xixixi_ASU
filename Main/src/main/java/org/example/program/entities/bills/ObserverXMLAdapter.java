package org.example.program.entities.bills;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;


public class ObserverXMLAdapter extends XmlAdapter<Object, Observer>{

    // When reading
    @Override
    public Observer unmarshal(Object obj) throws Exception{
        return null;
    }

    // When writing
    @Override
    public Object marshal (Observer c) throws Exception{
        return null;
    }
}
