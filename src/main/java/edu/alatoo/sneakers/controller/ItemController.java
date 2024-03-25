package edu.alatoo.sneakers.controller;

import edu.alatoo.sneakers.model.User;
import edu.alatoo.sneakers.payload.ItemRequestDTO;
import edu.alatoo.sneakers.payload.ItemResponseDTO;
import edu.alatoo.sneakers.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@AuthenticationPrincipal User user,
                                                      @RequestBody ItemRequestDTO itemRequestDTO) {
        ItemResponseDTO createdItem = itemService.create(user, itemRequestDTO);
        return ResponseEntity.ok(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@AuthenticationPrincipal User user,
                                                      @PathVariable Long id,
                                                      @RequestBody ItemRequestDTO itemRequestDTO) {
        ItemResponseDTO updatedItem = itemService.update(user, id, itemRequestDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItem(@PathVariable Long id) {
        ItemResponseDTO item = itemService.get(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(@AuthenticationPrincipal User user) {
        List<ItemResponseDTO> items = itemService.getAll(user.getId());
        return ResponseEntity.ok(items);
    }
}
