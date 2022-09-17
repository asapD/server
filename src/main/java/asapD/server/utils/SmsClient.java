package asapD.server.utils;

import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

@Component
public class SmsClient {


    public String createRandomNum() {
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        return numStr;
    }

    public void sendMessage(String phoneNumber,String code){
        // code redis 에 저장

        String api_key = "NCSK0TRMGX0QQC86";
        String api_secret = "RVDMYMYTYNFI0FKDDVKBF3P3XIMLCGX7";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", "01086094105");
        params.put("type", "SMS");
        params.put("text", "asapD : 인증번호는" + "["+code+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            throw new ApiException(ApiExceptionEnum.SMS_EXCEPTION);
        }
    }
}
