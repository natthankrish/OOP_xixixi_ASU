package org.example.program.entities.commodities;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.example.program.entities.bills.Observer;

@XmlJavaTypeAdapter(CommodityXMLAdapter.class)
public interface Commodity {

    public Integer getId();

    public Integer getStock();
    public String getName();

    public Double getPurchasePrice();
    public Double getPrice();
    public String getCategory();

    public String getImage();

    public Boolean getActive();

    public void setID(Integer i);

    public void setStock(Integer newStock);

    public void setName(String newName);

    public void setPurchasePrice(Double newPurchasePrice);

    public void setPrice(Double newPrice);

    public void setCategory(String newCategory);

    public void setImage(String newImage);

    public void setActive(Boolean active);

    public void display();

    public String getCurrency();

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();

}
