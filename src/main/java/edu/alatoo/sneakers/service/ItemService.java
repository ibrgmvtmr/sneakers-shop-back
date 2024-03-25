package edu.alatoo.sneakers.service;

import edu.alatoo.sneakers.model.User;
import edu.alatoo.sneakers.payload.ItemRequestDTO;
import edu.alatoo.sneakers.payload.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    ItemResponseDTO create(User user, ItemRequestDTO itemRequestDTO);
    ItemResponseDTO update(User user, Long id, ItemRequestDTO itemRequestDTO);
    ItemResponseDTO get(Long id);
    List<ItemResponseDTO> getAll(Long userId);

}
