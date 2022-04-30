package cn.itcast.haoke.dubbo.api.controller;

import cn.itcast.haoke.dubbo.api.config.MockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ykj
 */
@RequestMapping("mock")
@RestController
public class MockController {
    
    @Autowired
    private MockConfig mockConfig;
    /**
     * 菜单
     *
     */
    @GetMapping("index/menu")
    public String indexMenu() {
        return this.mockConfig.getIndexMenu();
    }
    /**
     * 首页资讯
     */
    @GetMapping("index/info")
    public String indexInfo() {
        return this.mockConfig.getIndexInfo();
    }
    /**
     * 首页问答
     */
    @GetMapping("index/faq")
    public String indexFaq() {
        return this.mockConfig.getIndexFaq();
    }
    /**
     * 首页房源信息
     */
    @GetMapping("index/house")
    public String indexHouse() {
        return this.mockConfig.getIndexHouse();
    }
    
    /**
     * 查询资讯
     */
    @GetMapping("infos/list")
    public String infosList(@RequestParam("type") Integer type){
        switch (type){
            case 1:
                return this.mockConfig.getInfosList1();
            case 2:
                return this.mockConfig.getInfosList2();
            case 3:
                return this.mockConfig.getInfosList3();
            }
        return this.mockConfig.getInfosList1();
    }
    
    @GetMapping("info/my")
    public String myInfo(){
        return this.mockConfig.getMy();
    }
    
}
