package com.social.BackEnd.request;

import com.social.BackEnd.models.User;
import lombok.Data;

@Data
public class CreateChatRequest {
    private Integer targetUserId;
}
