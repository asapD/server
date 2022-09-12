package asapD.server.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/test")
public class TestController {

    @ApiOperation(value="테스트", notes="부가 설명")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "클라이언트 에러"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping
    public String test(@Parameter(description = "이름") @RequestParam(required = false) String name) {
        return name;
    }

}
