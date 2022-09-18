package asapD.server.service;

import asapD.server.controller.dto.store.StoreResponseDto;
import asapD.server.repository.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StoreService {

  private final StoreRepository storeRepository;

  @Transactional
  public List<StoreResponseDto> getStoreAll(Pageable pageable) {

    List<StoreResponseDto> response = storeRepository.findAll().stream().map(store ->
            StoreResponseDto.builder()
                .name(store.getName())
                .address(store.getAddress())
                .owner(store.getOwner())
                .build())
        .collect(Collectors.toList());

    return response;
  }

}
