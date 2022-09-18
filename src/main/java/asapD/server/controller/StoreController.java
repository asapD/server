package asapD.server.controller;

import asapD.server.controller.dto.store.StoreResponseDto;
import asapD.server.response.BaseResponse;
import asapD.server.service.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/store")
public class StoreController {

  private final StoreService storeService;

  @GetMapping
  @ApiOperation(value = "가게 조회", notes = "전체 가게 조회")
  @ApiResponses({
      @ApiResponse(code = 200, message = "전체 가게 조회 성공")
  })
  public ResponseEntity<BaseResponse> getStoreAll(@RequestParam("page") int page) {

    PageRequest pageRequest = PageRequest.of(page, 10);

    List<StoreResponseDto> response = storeService.getStoreAll(pageRequest);

    return ResponseEntity.ok(
        BaseResponse.builder().message("전체 가게 조회 성공").data(response).build());
  }

}

