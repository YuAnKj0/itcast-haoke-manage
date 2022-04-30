package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.lang.model.type.IntersectionType;

public class HouseResourcesListDataFetcher implements MyDataFetcher{
    
    @Autowired
    private HouseResourcesService houseResourcesService;
    /**
     * 查询名称
     *
     * @return
     */
    @Override
    public String fieldName() {
        return "HouseResourcesList";
    }
    
    /**
     * 具体实现数据查询的逻辑
     *
     * @param environment
     * @return
     */
    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Integer page = environment.getArgument("page");
        if (page==null) {
            page=1;
        }
        Integer pageSize = environment.getArgument("pageSize");
        if (pageSize==null) {
            pageSize=5;
        }
        return this.houseResourcesService.queryList(null,page,pageSize);
    }
}
