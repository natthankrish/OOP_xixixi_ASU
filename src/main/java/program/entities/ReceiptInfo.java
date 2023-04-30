package program.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "ReceiptInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReceiptInfo {
    private Integer productID;
    private Integer quantity;
    private Double subtotal;

}
