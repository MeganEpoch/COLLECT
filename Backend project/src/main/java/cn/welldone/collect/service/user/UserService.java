package cn.welldone.collect.service.user;

import cn.welldone.collect.po.user.User;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.user.UserVO;

import java.util.List;


public interface UserService {
    // 用户注册
    ResultVO<UserVO> userRegister(UserVO user);
    // 用户登录验证
    ResultVO<UserVO> userLogin(String phone, String password);
    // 根据id查找用户
    UserVO getUser(Integer uid);

    List<User> getAll();


}
