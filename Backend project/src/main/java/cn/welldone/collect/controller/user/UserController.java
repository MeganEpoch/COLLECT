package cn.welldone.collect.controller.user;

import cn.welldone.collect.service.user.PreferenceService;
import cn.welldone.collect.service.user.UserService;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.user.PreferenceVO;
import cn.welldone.collect.vo.user.UserFormVO;
import cn.welldone.collect.vo.user.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private PreferenceService preferenceService;

    @PostMapping("/register")
    public ResultVO<UserVO> register(@RequestBody UserVO user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserFormVO userForm){
        return userService.userLogin(userForm.getPhone(), userForm.getPassword());
    }


    @GetMapping("/{uid}")
    public UserVO getUser(@PathVariable Integer uid){
        return userService.getUser(uid);
    }

    @PostMapping("/prefer")
    public ResultVO<PreferenceVO> choosePreference(@RequestBody PreferenceVO preferenceVO){
        return preferenceService.choosePreference(preferenceVO);
    }
}
