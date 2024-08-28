package com.mar.moj.model.dto.question;

import lombok.Data;

@Data
public class FavourRequest {
    private Long questionId;
    private boolean isLike; // true for like, false for dislike
}
