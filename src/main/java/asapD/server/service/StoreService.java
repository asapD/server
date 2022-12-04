package asapD.server.service;

import asapD.server.controller.dto.store.StoreResponse;
import asapD.server.domain.Store;
import asapD.server.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StoreService {

  private final StoreRepository storeRepository;

  public Page<StoreResponse> getStoreAll(Pageable pageable) {

    Page<Store> result = storeRepository.findAll(pageable);

    Page<StoreResponse> storeList = result.map(store -> new StoreResponse(store));

    return storeList;
  }

}
