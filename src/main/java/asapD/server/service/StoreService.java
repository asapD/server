package asapD.server.service;

import asapD.server.controller.dto.store.StoreResponseDto;
import asapD.server.domain.Store;
import asapD.server.repository.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StoreService {

  private final StoreRepository storeRepository;

  public Page<StoreResponseDto> getStoreAll(Pageable pageable) {

    Page<Store> result = storeRepository.findAll(pageable);

    Page<StoreResponseDto> storeList = result.map(store -> new StoreResponseDto(store));

    return storeList;
  }

}
