package com.github.madhav.SpringKafka.item_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item_detail")
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

    @Autowired
    public ItemDetailController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @GetMapping
    public List<ItemDetail> getItemDetails() {
        return itemDetailService.getItemDetails();
    }

    @GetMapping(path = "{itemDetailId}")
    public ItemDetail getItemDetailById(@PathVariable("itemDetailId") Long itemDetailId) {
        return itemDetailService.getItemDetailById(itemDetailId);
    }

    @PostMapping
    public void registerNewItemDetail(@RequestBody ItemDetail itemDetail) {
        itemDetailService.addNewItemDetail(itemDetail);
    }

    @DeleteMapping(path = "{itemDetailId}")
    public void deleteItemDetail(@PathVariable("itemDetailId") Long itemDetailId) {
        itemDetailService.deleteItemDetail(itemDetailId);
    }
}
