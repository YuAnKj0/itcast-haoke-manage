package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.api.vo.WebResult;
import cn.itcast.haoke.dubbo.server.api.ApiAdService;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import cn.itcast.haoke.dubbo.server.vo.PageInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author Ykj
 */
@Service
public class AdService {
    @Reference(version = "1.0.0")
    private ApiAdService apiAdService;
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        PageInfo<Ad> adPageInfo = this.apiAdService.queryAdList(type, page,
                pageSize);
        return this.apiAdService.queryAdList(type, page, pageSize);
    }
}



