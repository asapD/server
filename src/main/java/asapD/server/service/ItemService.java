package asapD.server.service;

import asapD.server.domain.Item;
import asapD.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItemAll(Pageable pageable) {
        return itemRepository.findAll(pageable).getContent();
    }
}
