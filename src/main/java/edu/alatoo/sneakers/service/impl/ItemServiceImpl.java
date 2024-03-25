package edu.alatoo.sneakers.service.impl;

import edu.alatoo.sneakers.exception.NotFoundException;
import edu.alatoo.sneakers.mapper.ItemMapper;
import edu.alatoo.sneakers.model.Item;
import edu.alatoo.sneakers.model.User;
import edu.alatoo.sneakers.payload.ItemRequestDTO;
import edu.alatoo.sneakers.payload.ItemResponseDTO;
import edu.alatoo.sneakers.repository.ItemRepository;
import edu.alatoo.sneakers.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }


    @Override
    public ItemResponseDTO create(User user,  ItemRequestDTO itemRequestDTO) {
        Item item = itemMapper.toItem(itemRequestDTO);
        item.setUser(user);
        return itemMapper.toDTO(item);
    }

    @Override
    public ItemResponseDTO update(User user, Long id, ItemRequestDTO itemRequestDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Проект не найден"));
        item.setLabel(itemRequestDTO.getLabel());
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        return itemMapper.toDTO(itemRepository.save(item));
    }

    @Override
    public ItemResponseDTO get(Long id) {
        return itemMapper.toDTO(itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Проект не найден")));

    }

    @Override
    public List<ItemResponseDTO> getAll(Long userId) {
        return itemRepository.findAllByUserId(userId)
                .stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
