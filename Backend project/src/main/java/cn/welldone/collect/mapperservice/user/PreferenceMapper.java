package cn.welldone.collect.mapperservice.user;

import cn.welldone.collect.po.user.Preference;

public interface PreferenceMapper {
    int insert(Preference preference);

    Preference selectByEmployeeId(int eid);
}
