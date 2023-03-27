package cn.welldone.collect.po.task;

import cn.welldone.collect.vo.task.TaskVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer id;

    private String name;

    private String type;

    private String intro;

    private Date createTime;

    private Date endTime;

    private Date deleteTime;

    private Integer requiredNumber;

    private Integer employerId;

    private String employerName;

    private Integer status;
    // 标记当前用户（工人）是否已接受任务
    private boolean accepted;

    // 标记当前用户（发包方）是否有权限管理任务
    private boolean manageable;

    private String fileName1;

    private String fileName2;

    private double similarity;       //相似度(只在推荐时调用)

    private String device;

    private Integer difficulty;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(Integer requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName == null ? null : employerName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isManageable() {
        return manageable;
    }

    public void setManageable(boolean manageable) {
        this.manageable = manageable;
    }



    public Task(@NonNull TaskVO taskVO) {
        this.id = taskVO.getId();
        this.name = taskVO.getName();
        this.type = taskVO.getType();
        this.intro = taskVO.getIntro();
        this.createTime = taskVO.getCreateTime();
        this.endTime = taskVO.getEndTime();
        this.requiredNumber = taskVO.getRequiredNumber();
        this.employerId = taskVO.getEmployerId();
        this.employerName = taskVO.getEmployerName();
        this.status = taskVO.getStatus();
        this.accepted=taskVO.isAccepted();
        this.manageable=taskVO.isManageable();
        this.fileName1=taskVO.getFileName1();
        this.fileName2=taskVO.getFileName2();
        device=taskVO.getDevice();
        difficulty=taskVO.getDifficulty();
    }
}