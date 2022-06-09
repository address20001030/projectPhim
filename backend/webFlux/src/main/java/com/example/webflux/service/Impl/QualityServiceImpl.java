package com.example.webflux.service.Impl;

import com.example.webflux.mapper.quality.QualityMapper;
import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.entity.Quality;
import com.example.webflux.model.response.quality.QualityResponse;
import com.example.webflux.repository.LinkMovieRepository;
import com.example.webflux.repository.QualityRepository;
import com.example.webflux.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualityServiceImpl implements QualityService {
    private final QualityMapper qualityMapper;
    private final QualityRepository qualityRepository;
    private final LinkMovieRepository linkMovieRepository;

    @Autowired
    public QualityServiceImpl(QualityMapper qualityMapper, QualityRepository qualityRepository, LinkMovieRepository linkMovieRepository) {
        this.qualityMapper = qualityMapper;
        this.qualityRepository = qualityRepository;
        this.linkMovieRepository = linkMovieRepository;
    }


    @Override
    public List<QualityResponse> getAllQuality() {
        List<Quality> qualities = qualityRepository.findAll();
        return qualityMapper.to(qualities);
    }

//    @Override
//    public String getName(long id_link) {
//        Optional<LinkMovie> linkMovie = linkMovieRepository.findById(id_link);
//        Quality quality = qualityRepository.findByLinkMovie(linkMovie.get());
//        return quality.getName_quality();
//    }


}
