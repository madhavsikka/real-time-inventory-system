package com.github.madhav.SpringKafka.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item ID does not exist"));
    }

    public void addNewItem(Item item) {
        itemRepository.save(item);
        System.out.println(item);
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalStateException("Item ID does not exist");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, Long stock, String name, Double unitPrice) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item ID does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(name, item.getName())) {
            item.setName(name);
        }
        if (stock > 0 && !Objects.equals(stock, item.getStock())) {
            item.setStock(stock);
        }
        if (unitPrice > 0 && !Objects.equals(unitPrice, item.getUnitPrice())) {
            item.setUnitPrice(unitPrice);
        }
    }
}
