package com.example.demo.dto;

import com.example.demo.util.ErrorConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import static com.example.demo.util.ErrorConstants.MESSAGE_STATUS_OK;

/**
 * @author: flyboy
 * @Date: 12/03/2021 12:52
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest<T extends BaseRequest> implements Serializable {

    private static final long serialVersionUID = 5943375748416847308L;

    @Valid
    @NotNull
    private T data;

    @JsonIgnore
    private transient String method;

    public <RES extends BaseResponse> MessageResponse<RES> response(RES response) {
        return response(MESSAGE_STATUS_OK, response);
    }
    public <RES extends BaseResponse> MessageResponse<RES> response(ErrorConstants error, RES response) {
        MessageResponse<RES> messageResponse = MessageResponse.<RES>builder()
                .data(response)
                .code(error.getCode())
                .msg(error.getMsg())
                .build();
        return messageResponse;
    }

}
