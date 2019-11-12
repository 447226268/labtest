package com.gooalgene.labtest.controller;


import com.gooalgene.labtest.entity.Homepage_links;
import com.gooalgene.labtest.response.BaseResponse;
import com.gooalgene.labtest.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinksService linksService;

    @GetMapping("/getLinks")
    public BaseResponse<List<Homepage_links>> getNewsFigure() {
        BaseResponse<List<Homepage_links>> response = new BaseResponse<>();
        response.setResult(linksService.findAll());
        return response;
    }

    @GetMapping("/insertLink")
    public BaseResponse<String> insertLink(@RequestParam List<Homepage_links> homepage_links) {
        BaseResponse<String> response = new BaseResponse<>();
        for (int i = 0; i < homepage_links.size(); i++) {
            linksService.insertLink(homepage_links.get(i));
        }
        response.setResult("插入成功");
        return response;
    }

    @GetMapping("/deleteLink")
    public BaseResponse<String> deleteLink(@RequestParam("id") int id) {
        BaseResponse<String> response = new BaseResponse<>();
        linksService.deleteLink(id);
        response.setResult("删除成功");
        return response;
    }

    @GetMapping("/updateLink")
    public BaseResponse<String> updateLink(@RequestParam Homepage_links homepage_links) {
        BaseResponse<String> response = new BaseResponse<>();
        linksService.updateLink(homepage_links);
        response.setResult("更改成功");
        return response;
    }
}