package edu.alatoo.sneakers.payload;

import lombok.Data;

@Data
public class ItemRequestDTO {
    private Long id;
    private String name;
    private String label;
    private Long price;
}
