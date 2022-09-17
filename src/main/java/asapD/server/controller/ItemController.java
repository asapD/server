package asapD.server.controller;

import asapD.server.domain.Item;
import asapD.server.domain.Store;
import asapD.server.repository.ItemRepository;
import asapD.server.response.BaseResponse;
import asapD.server.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @ApiOperation(value = "아이템 조회", notes = "전체 아이템 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "전체 아이템 조회 성공")
    })
    public ResponseEntity<BaseResponse> findAll(@RequestParam("page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<Item> itemAll = itemService.getItemAll(pageRequest);
        return ResponseEntity.ok(BaseResponse.builder().message("전체 아이템 조회 성공").data(itemAll).build());
    }

}
