package org.example.program.entities.commodities;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.ArrayList;

public class CommodityXMLAdapter extends XmlAdapter<Object, Commodity>{

    // When reading
    @Override
    public Commodity unmarshal(Object obj) throws Exception{
        Product p = (Product) obj;
        return new Product(new ArrayList<>(), p.getId(), p.getStock(), p.getName(), p.getPrice(), p.getPurchasePrice(), p.getCategory(), p.getImage(), p.getActive());
    }

    // When writing
    @Override
    public Object marshal (Commodity c) throws Exception{
        Product p = (Product) c;
        p.setObservers(null);
        return ((Product) c);
    }
}
