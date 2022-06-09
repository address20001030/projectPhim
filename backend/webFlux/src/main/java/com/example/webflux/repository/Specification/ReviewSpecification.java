package com.example.webflux.repository.Specification;

import com.example.webflux.model.entity.CommentSection;
import com.example.webflux.model.entity.ReviewSection;
import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {
    public static Specification<ReviewSection> filterAll(Long mv_id){
        return Specification.where(withMovieId(mv_id));
    }

    public static Specification<ReviewSection> withMovieId(Long mv_id){
        if (mv_id == 0 || mv_id == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("movie").get("id_movie"),mv_id);
    }
}
