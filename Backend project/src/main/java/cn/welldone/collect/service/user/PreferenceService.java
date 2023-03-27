package cn.welldone.collect.service.user;

import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.user.PreferenceVO;

public interface PreferenceService {
    ResultVO<PreferenceVO> choosePreference(PreferenceVO preferenceVO);

}
