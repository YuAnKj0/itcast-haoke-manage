package cn.itcast.haoke.dubbo.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Ykj.
 */
@SpringBootApplication
public class AdDubboProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdDubboProvider.class)
                // 非 Web 应用
                .web(WebApplicationType.NONE)
                .run(args);
    }
}

