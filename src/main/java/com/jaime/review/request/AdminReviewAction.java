package com.jaime.review.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminReviewAction {

    private Boolean acceptReview;  // True if the admin accepts the review, false if rejected
}
