package cn.welldone.collect.vo.user;

import cn.welldone.collect.enums.UserRole;
import cn.welldone.collect.po.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class UserVO {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private UserRole userRole;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public UserVO() {
    }

    public UserVO(@NonNull User user) {
        id = user.getId();
        uname = user.getUname();
        phone = user.getPhone();
        userRole = user.getUserRole();
        createTime = user.getCreateTime();
    }
}
