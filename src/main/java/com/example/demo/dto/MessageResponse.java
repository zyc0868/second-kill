package com.example.demo.dto;

import com.example.demo.util.ErrorConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: flyboy
 * @Date: 12/03/2021 12:52
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse <T extends BaseResponse> implements Serializable {

    private static final long serialVersionUID = -6995158904797481270L;

    @Valid
    private T data;

    @NotNull
    @JsonProperty("code")
    private Integer code;

    @NotNull
    @JsonProperty("msg")
    private String msg;

    public MessageResponse(@NonNull ErrorConstants error) {
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

}
