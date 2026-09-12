package com.shahid.shopsphere.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.service.RedisPipelineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisPipelineController {

    private final RedisPipelineService redisPipelineService;

    @PostMapping("/pipeline")
    public String testPipeline() {
        redisPipelineService.updateProductViews();
        return "Pipeline executed successfully";
    }
}