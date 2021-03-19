package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: flyboy
 * @Date: 12/03/2021 14:52
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@NoArgsConstructor
public abstract class BaseResponse implements Serializable {
    private static final long serialVersionUID = -3349983655118453427L;
}
