package com.example.webflux.repository.Specification;

import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.request.movie.MovieFilterSearchRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;



public class MovieSpecification {

    public static Specification<Movie> filterAll(MovieFilterSearchRequest filter){
        return Specification.where(withCategoryId(filter.getId())
                .or(withDirector(filter.getDirector()))
                .or(withActor(filter.getActor())))
                .or(withImgQuality(filter.getImg()))
                .or(withMin(filter.getMin()))
                .or(withYear(filter.getYear()))
                .or(withKeyword(filter.getKeyWork()));
    }

    public static Specification<Movie> withCategoryId(Long id){
        if(id == 0 || id == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("category").get("id_category"),id);
    }

    public static Specification<Movie> withDirector(String director){
        if (StringUtils.hasLength(director)) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("director"),"%"+director+"%");
    }

    public static Specification<Movie> withActor(String actor){
        if (StringUtils.hasLength(actor)) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("actor"),"%"+actor+"%");
    }

    public static Specification<Movie> withImgQuality(String img){
        if (StringUtils.hasLength(img)) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("imgQuality"),img);
    }

    public static Specification<Movie> withMin(Integer min){
        if(min == 0 || min == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("time"),min);
    }

    public static Specification<Movie> withYear(Integer year){
        if(year == 0 || year == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("year"),year);
    }

    public static Specification<Movie> withKeyword(String keyword){
        if (StringUtils.hasLength(keyword)) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"% "+keyword);
    }



}
