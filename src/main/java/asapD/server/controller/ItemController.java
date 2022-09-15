package asapD.server.controller;

import asapD.server.domain.Item;
import asapD.server.domain.Store;
import asapD.server.repository.ItemRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

    private ItemRepository itemRepository;

    @GetMapping
    @ApiOperation(value = "아이템 조회", notes = "전체 아이템 조회")
    public Page<Item> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return itemRepository.findAll(pageRequest);
    }

}
