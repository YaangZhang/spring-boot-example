package com.sto.springbootwebrestful.config;

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

@Configuration  //启动时加载此类
@EnableSwagger2 //表示此项目启用 Swagger API 文档
public class SwaggerConfig {
    /**
     * Swagger 常用注解
     * Swagger 通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息等，常用注解内容如下：
     *
     * 作用范围	API	使用位置
     * 协议集描述	@Api	用于 Controller 类上
     * 协议描述	@ApiOperation	用在 Controller 的方法上
     * 非对象参数集	@ApiImplicitParams	用在 Controller 的方法上
     * 非对象参数描述	@ApiImplicitParam	用在 @ApiImplicitParams 的方法里边
     * 响应集	@ApiResponses	用在 Controller 的方法上
     * 响应信息参数	@ApiResponse	用在 @ApiResponses 里边
     * 描述返回对象的意义	@ApiModel	用在返回对象类上
     * 对象属性	@ApiModelProperty	用在出入参数对象的字段上
     * @return
     */

    @Bean  //此方法使用 @Bean，在启动时初始化
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径  指定需要扫描的包路径，只有此路径下的 Controller 类才会自动生成 Swagger API 文档。
                .apis(RequestHandlerSelectors.basePackage("com.sto.springbootwebrestful.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户管理")
                .description("客户管理中心 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.ityouknow.com/")
                .version("1.0")
                .contact(new Contact("纯洁的微笑", "http://www.ityouknow.com/", "ityouknow@126.com"))
                .build();
    }

}
