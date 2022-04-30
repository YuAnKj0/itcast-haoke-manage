package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 实现的功能，将GraphQLProvider对象加载到Spring容器棕，并完成初始化GraphQLProvider对象的功能
 */
@Component
public class GraphQLProvider {
    
    private GraphQL graphQL;
    @Autowired
    private List<MyDataFetcher> myDataFetchers; //注入容器中所有的MyDataFetcher实现类
    
    @PostConstruct
    public void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:haoke.graphqls");
        this.graphQL=GraphQL.newGraphQL(buildGraphQLSchema(file)).build();
    }
    
    private GraphQLSchema buildGraphQLSchema(File file){
        TypeDefinitionRegistry typeRegistry=new SchemaParser().parse(file);
        return new SchemaGenerator().makeExecutableSchema(typeRegistry,buildWiring());
    }
    
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("HaokeQuery", builder -> {
                    for (MyDataFetcher myDataFetcher:myDataFetchers){
                        builder.dataFetcher(myDataFetcher.fieldName(),environment->myDataFetcher.dataFetcher(environment));
                        }
                    return builder;
//                        builder.dataFetcher("HouseResources",
//                                environment -> {
//                                    Long id = environment.getArgument("id");
//                                    return this.houseResourcesService.queryById(id);
//                                }
                }).build();
    
    }
    
    @Bean
    public GraphQL graphQL(){
        return this.graphQL;
    }
    
}
