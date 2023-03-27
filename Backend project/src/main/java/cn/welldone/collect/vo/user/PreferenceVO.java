package cn.welldone.collect.vo.user;

import cn.welldone.collect.po.user.Preference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceVO {
    private Integer id;
    private int employeeId;
    private String device;
    private String taskType;

    public PreferenceVO(Preference preference){
        this.id=preference.getId();
        this.employeeId=preference.getEmployeeId();
        this.device=preference.getDevice();
        this.taskType=preference.getTaskType();
    }
}
