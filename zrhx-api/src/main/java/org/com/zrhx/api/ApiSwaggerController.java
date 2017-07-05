package org.com.zrhx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.com.zrhx.model.ApiUserModel;
import org.com.zrhx.utill.R;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gs on 2017/7/4.
 */
@Api(value = "Swagger接口测试",description = "Swagger接口测试")
@RestController
@RequestMapping("api")
public class ApiSwaggerController {

    @ApiOperation(value = "测试接口",notes = "测试接口",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("test")
    public String Test(@ApiParam(value = "用户ID",required = true) String userid){

        return "ssss";
    }

    @ApiOperation(value = "对象接口测试",notes = "对象接口测试",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("testmodel")
    public R TestModel(@ApiParam(value = "用户ID",required = true) String userid){
        return R.ok().put("user",new ApiUserModel());

    }

    @ApiOperation(value = "对象接口测试",notes = "对象接口测试",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("TestModelRes")
//     ModelAttribute 是Spring mvc的注解，这里Swagger可以解析这个注解，获得User的属性描述
    public R TestModelRes(@ModelAttribute ApiUserModel userModel){
        return R.ok().put("user",userModel);

    }
    @ApiOperation(value = "对象接口测试",notes = "对象接口测试",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("Test2")
    public R Test2( @ApiParam(required = true, value = "group data") @RequestBody ApiUserModel userModel){
        return R.ok().put("user",userModel);

    }
}
