package cn.welldone.collect.po.user;


import cn.welldone.collect.vo.user.PreferenceVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preference {
    private Integer id;
    private int employeeId;

    private String device;

    private String taskType;



    public Preference(PreferenceVO preferenceVO){
        this.id=preferenceVO.getId();
        this.employeeId=preferenceVO.getEmployeeId();
        this.device=preferenceVO.getDevice();
        this.taskType=preferenceVO.getTaskType();
    }
}

