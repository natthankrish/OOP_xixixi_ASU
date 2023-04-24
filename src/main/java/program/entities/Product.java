package program.entities;


import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {
    private Integer id;
    private Integer stock;
    private String name;
    private Double price;
    private Double purchasePrice;
    private String category;
    private String image;
}
