package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesDataFetcher implements MyDataFetcher {
    
    @Autowired
    private HouseResourcesService houseResourcesService;
    /**
     * 查询名称
     *
     * @return
     */
    @Override
    public String fieldName() {
        return "HouseResources";
    }
    
    /**
     * 具体实现数据查询的逻辑
     *
     * @param environment
     * @return
     */
    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id = environment.getArgument("id");
        return this.houseResourcesService.queryById(id);
    }
}
