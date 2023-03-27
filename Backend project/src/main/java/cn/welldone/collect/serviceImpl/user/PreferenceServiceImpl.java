package cn.welldone.collect.serviceImpl.user;

import cn.welldone.collect.mapperservice.user.PreferenceMapper;
import cn.welldone.collect.po.user.Preference;
import cn.welldone.collect.service.user.PreferenceService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.user.PreferenceVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Resource
    PreferenceMapper preferenceMapper;

    @Override
    public ResultVO<PreferenceVO> choosePreference(PreferenceVO preferenceVO) {
        Preference preference=new Preference(preferenceVO);
        if(preferenceMapper.insert(preference)>0){
            //要用po new vo才会有id！
            return new ResultVO<PreferenceVO>(Constant.REQUEST_SUCCESS, "选择成功。", new PreferenceVO(preference));
        }

        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }
}
