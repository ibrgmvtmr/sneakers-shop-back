package edu.alatoo.sneakers.mapper;

import edu.alatoo.sneakers.model.Item;
import edu.alatoo.sneakers.payload.ItemRequestDTO;
import edu.alatoo.sneakers.payload.ItemResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemRequestDTO itemRequestDTO);
    ItemResponseDTO toDTO(Item item);
}
