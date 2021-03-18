package com.mz.aclasser.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Getter
@Configuration
public class ModelConfig {
    @Value("${aclasser.model.inputSize}")
    private Integer inputSize;

    @Value("${aclasser.model.imageMean}")
    private Integer imageMean;

    @Value("${aclasser.model.imageStd}")
    private Float imageStd;

    @Value("${aclasser.model.inputLayerName}")
    private String inputLayerName;

    @Value("${aclasser.model.outputLayerName}")
    private String outputLayerName;

    @Value("${aclasser.model.path}")
    private String modelPath;

    @Value("${aclasser.model.labelsResource}")
    private Resource labelsResource;

    @Value("${aclasser.model.threshold}")
    private Float threshold;
}
