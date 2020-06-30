package com.peng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
//	//访问路径/swagger-ui.html
//	@Bean
//	public Docket createRestApi() {
//		return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select()
//				.apis(RequestHandlerSelectors.basePackage("com.peng.controller")).paths(PathSelectors.any()).build()
//				.apiInfo(new ApiInfoBuilder().title("SpringBoot整合Swagger").description("SpringBoot整合Swagger，详细信息......")
//						.version("1.0.0").contact(new Contact("曾鹏", "blog.csdn.net", "zengpeng5281314@163.com"))
//						.license("The Apache License").licenseUrl("http://www.baidu.com").build());
//	}
//	
	
	 // 定义分隔符
    private static final String splitor = ";";

    /**
     * 创建API应用
     * api() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.peng.controller"
                        + splitor
                        + "com.peng.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("XXX项目接口")
                //创建人
                .contact(new Contact("userName", "", "zengpeng5281314@163.com"))
                //版本号
                .version("1.0.0-SNAPSHOT")
                //描述
                .description("")
                .build();
    }


    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }


    /**
     * swagger2原始ui
     * http://localhost:8080/swagger-ui.html
     *
     * swagger-ui-layer访问ui
     * http://localhost:8080/docs.html
     */
	
}
