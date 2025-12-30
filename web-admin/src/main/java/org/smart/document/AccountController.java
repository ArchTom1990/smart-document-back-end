package org.smart.document;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    // http://127.0.0.1:8080/api/login/account?token=123
    @CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
    @PostMapping("/api/login/account")
    public Object login(LoginRequest loginRequest){
        System.out.println("=====================");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus("ok");
        loginResponse.setType("1");
        loginResponse.setCurrentAuthority("admin");
        loginResponse.setToken("token-123");
        return  loginResponse;
    }


    @CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
    @GetMapping("/api/currentUser")
    public Object currentUser( ){
        UserResponse response = new UserResponse();
        response.setData(initData());
        return  response;
    }

    private UserResponse.UserData initData(){
        // 1. 初始化地理信息 (Geographic)
        UserResponse.Location province = new UserResponse.Location();
        province.setLabel("浙江省");
        province.setKey("330000");

        UserResponse.Location city = new UserResponse.Location();
        city.setLabel("杭州市");
        city.setKey("330100");

        UserResponse.Geographic geo = new UserResponse.Geographic();
        geo.setProvince(province);
        geo.setCity(city);

        // 2. 初始化标签列表 (Tags)
        List<UserResponse.Tag> tagList = new ArrayList<>();

        String[][] tagData = {
                {"0", "很有想法的"}, {"1", "专注设计"}, {"2", "辣~"},
                {"3", "大长腿"}, {"4", "川妹子"}, {"5", "海纳百川"}
        };

        for (String[] t : tagData) {
            UserResponse.Tag tag = new UserResponse.Tag();
            tag.setKey(t[0]);
            tag.setLabel(t[1]);
            tagList.add(tag);
        }

        // 3. 组装核心数据 (UserData)
        UserResponse.UserData userData = new UserResponse.UserData();
        userData.setName("Tom");
        userData.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
        userData.setUserid("00000001");
        userData.setEmail("antdesign@alipay.com");
        userData.setSignature("海纳百川，有容乃大");
        userData.setTitle("交互专家");
        userData.setGroup("蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED");
        userData.setTags(tagList);
        userData.setNotifyCount(12);
        userData.setUnreadCount(11);
        userData.setCountry("China");
        userData.setGeographic(geo);
        userData.setAddress("西湖区工专路 77 号");
        userData.setPhone("0752-268888888");

        return userData;
    }


    @CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
    @PostMapping("/api/login/outLogin")
    public Object outLogin(LoginRequest loginRequest){
        System.out.println("=====================");

        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setData(null);
        logOutResponse.setSuccess(true);
        return  logOutResponse;
    }

}
