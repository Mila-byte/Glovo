package com.example.glovo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private Date date;
    private float cost;
    private List<Product> products;
}
