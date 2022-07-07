package com.zgz.cmsservice.api;

import com.zgz.cmsservice.pojo.CrmBanner;
import com.zgz.cmsservice.service.CrmBannerService;
import com.zgz.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author willie
 * @date 2021/11/29
 */
@Api(description = "前台banner展示")
@RestController
@RequestMapping("/cmsservice/crm-banner")
@CrossOrigin
public class CrmBannerApiController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "查询所有banner信息")
    @GetMapping("/getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> bannerList = crmBannerService.getAllBanner();
        return Result.succ().data("bannerList",bannerList);
    }
}






