package asapD.server.service;

import asapD.server.domain.Store;
import asapD.server.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public List<Store> findAll() {

        return storeRepository.findAll();
    }
}
