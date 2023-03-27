package cn.welldone.collect.service.user;

import cn.welldone.collect.vo.user.PreferenceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferenceServiceTest {
    @Resource
    PreferenceService preferenceService;
   @Transactional
    @Test
    public void testChoosePreference(){
        PreferenceVO preferenceVO=new PreferenceVO(null,10,"linux","性能测试");
        System.out.println(preferenceService.choosePreference(preferenceVO));
    }
}
