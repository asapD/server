package asapD.server.service;

import asapD.server.controller.dto.item.ItemResponseDto;
import asapD.server.domain.Item;
import asapD.server.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
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

  public Page<ItemResponseDto> getItemAll(Pageable pageable) {

    Page<Item> result = itemRepository.findAll(pageable);

    Page<ItemResponseDto> itemList = result.map(item -> new ItemResponseDto(item));

    return itemList;
  }
}
