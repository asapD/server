package asapD.server.service;

import asapD.server.controller.dto.item.ItemResponseDto;
import asapD.server.domain.Item;
import asapD.server.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public List<ItemResponseDto> getItemAll(Pageable pageable) {
//        List<Item> items = itemRepository.findAll(pageable).getContent();
    List<Item> items = itemRepository.findAll();

    List<ItemResponseDto> response = items.stream().map(item ->
            ItemResponseDto.builder()
                .description(item.getDescription())
                .name(item.getName())
                .price(item.getPrice())
                .storeId(item.getStore().getId())
                .build())
        .collect(Collectors.toList());

    return response;
  }
}
