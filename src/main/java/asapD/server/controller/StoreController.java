package asapD.server.controller;

import asapD.server.controller.dto.member.MemberContactRequest;
import asapD.server.domain.Store;
import asapD.server.repository.StoreRepository;
import asapD.server.service.StoreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/store")
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreService storeService;

    @GetMapping
    @ApiOperation(value = "가게 조회", notes = "전체 가게 조회")
     public Page<Store> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return storeRepository.findAll(pageRequest);
    }

}

