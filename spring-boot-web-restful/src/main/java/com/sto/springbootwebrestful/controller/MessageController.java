package com.sto.springbootwebrestful.controller;

import com.sto.springbootwebrestful.entity.BaseResult;
import com.sto.springbootwebrestful.entity.Message;
import com.sto.springbootwebrestful.repository.MessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Api 的使用 :
 * Api 作用在 Controller 类上，做为 Swagger 文档资源，该注解将一个 Controller（Class）标注为一个 Swagger 资源（API）。
 * 在默认情况下，Swagger-Core 只会扫描解析具有 @Api 注解的类，
 * 而会自动忽略其他类别资源（JAX-RS endpoints、Servlets 等）的注解。
 *
 * 属性名称	备注
 * value	url 的路径值
 * tags	如果设置这个值，value 的值会被覆盖
 * description	对 API 资源的描述
 * produces	For example, "application/json, application/xml"
 * consumes	For example, "application/json, application/xml"
 * protocols	Possible values: http, https, ws, wss
 * authorizations	高级特性认证时配置
 * hidden	配置为 true 将在文档中隐藏
 */
@Api(value = "消息", description = "消息操作 API", position = 100, protocols = "http")
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * @ApiOperation 定义在方法上，描述方法名、方法解释、返回信息、标记等信息。
     *属性名称	备注
     * value	url 的路径值
     * tags	如果设置这个值、value的 值会被覆盖
     * produces	For example, "application/json, application/xml"
     * consumes	For example, "application/json, application/xml"
     * protocols	Possible values: http, https, ws, wss
     * authorizations	高级特性认证时配置
     * hidden	配置为 true 将在文档中隐藏
     * response	返回的对象
     * responseContainer	这些对象是有效的 "List", "Set" or "Map"，其他无效
     * httpMethod	"GET"、"HEAD"、"POST"、"PUT"、"DELETE"、"OPTIONS" and "PATCH"
     * code	http 的状态码 默认 200
     * extensions	扩展属性
     * 获取所有消息体
     * @return
     */
    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息内容列表",
            produces="application/json, application/xml",
            consumes="application/json, application/xml",
            response = List.class)
    @GetMapping("/messages")
    public List<Message> list(){
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }

    /**
     *@ApiImplicitParams 用于描述方法的返回信息，和 @ApiImplicitParam 注解配合使用；
     * @ApiImplicitParam 用来描述具体某一个参数的信息，包括参数的名称、类型、限制等信息。
     *属性名称	备注
     * name	接收参数名
     * value	接收参数的意义描述
     * required	参数是否必填值为 true 或者 false
     * dataType	参数的数据类型只作为标志说明，并没有实际验证
     * paramType	查询参数类型，其值：
     * path 以地址的形式提交数据
     * query 直接跟参数完成自动映射赋
     * body 以流的形式提交，仅支持 POST
     * header 参数在 request headers 里边提交
     * form 以 form 表单的形式提交 仅支持 POST
     *
     * 创建一个消息体
     *
     * @param message
     * @return
     */
    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息 ID", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/message")
    public Message create(Message message) {
        message = this.messageRepository.save(message);
        return message;
    }

    /**
     * @ApiResponses 主要封装方法的返回信息和 @ApiResponse 配置起来使用，
     * @ApiResponse 定义返回的具体信息包括返回码、返回信息等。
     *
     * 使用 put 请求进行修改
     *
     * @param message
     * @return
     */
    @ApiOperation(value = "修改消息", notes = "根据参数修改消息")
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    @PutMapping(value = "/message")
    public Message modify(Message message) {
        Message messageResult=this.messageRepository.update(message);
        return messageResult;
    }

    // 更新消息的 text 字段
    @PatchMapping(value="/message/text")
    public BaseResult<Message> patch(Message message) {
        Message messageResult=this.messageRepository.updateText(message);
        return BaseResult.successWithData(messageResult);
    }

    @GetMapping(value = "/message/{id}")
    public Message get(@PathVariable Long id) {
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    @DeleteMapping(value = "/message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }

}
