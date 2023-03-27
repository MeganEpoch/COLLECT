package cn.welldone.collect.po.user;

import cn.welldone.collect.enums.UserRole;
import cn.welldone.collect.vo.user.UserVO;

import java.util.Date;

public class User {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private UserRole userRole;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User() {
    }

    public User(UserVO userVO){
        id = userVO.getId();
        uname = userVO.getUname();
        phone = userVO.getPhone();
        password = userVO.getPassword();
        userRole = userVO.getUserRole();
        createTime = userVO.getCreateTime();
    }
}