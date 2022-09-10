package asapD.server.controller;

import asapD.server.domain.Store;
import asapD.server.repository.StoreRepository;
import asapD.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/store")
public class StoreController {
    private final StoreRepository storeRepository;

    @GetMapping
     public Page<Store> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return storeRepository.findAll(pageRequest);
    }
}

