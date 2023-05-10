package com.example.vktraineeshipback.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Utils {
    IMAGE_PATH("src/main/resources/image/");
    private final String path ;
}
