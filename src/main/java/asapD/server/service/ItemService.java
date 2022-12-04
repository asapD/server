package asapD.server.service;

import asapD.server.controller.dto.item.ItemResponse;
import asapD.server.domain.Item;
import asapD.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public Page<ItemResponse> getItemAll(Pageable pageable) {

    Page<Item> result = itemRepository.findAll(pageable);
    return result.map(item -> new ItemResponse(item));

  }
}
