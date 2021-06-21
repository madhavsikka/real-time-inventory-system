package com.github.madhav.SpringKafka.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping(path = "{itemId}")
    public Item getItemById(@PathVariable("itemId") Long itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping
    public void registerNewItem(@RequestBody Item item) {
        itemService.addNewItem(item);
    }

    @PostMapping(path = "/{itemId}/add_stock")
    public void addStock(
            @PathVariable("itemId") Long itemId,
            @RequestParam(name = "addedStock") Long addedStock) {
        itemService.updateItemStock(itemId, addedStock);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping(path = "{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long stock,
            @RequestParam(required = false) Double unitPrice) {
        itemService.updateItem(itemId, stock, name, unitPrice);
    }
}
