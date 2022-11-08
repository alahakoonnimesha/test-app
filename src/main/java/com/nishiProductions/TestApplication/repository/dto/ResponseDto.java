package com.nishiProductions.TestApplication.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDto implements Serializable {

    private static final long serialVersionUID = 6953978110388779136L;

    private boolean status = true;
    private int errorCode;
    private String errorDescription;
    private Object responseDto;
}
