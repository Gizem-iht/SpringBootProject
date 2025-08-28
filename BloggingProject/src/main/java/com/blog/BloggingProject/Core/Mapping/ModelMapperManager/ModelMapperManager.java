package com.blog.BloggingProject.Core.Mapping.ModelMapperManager;

import com.blog.BloggingProject.Core.Mapping.ModelMapperService.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
/*
ModelMapper, Java’da nesneler arasında otomatik veri aktarımı sağlayan bir kütüphanedir.
Bu kütüphane, nesnelerin özelliklerini otomatik olarak eşleştirir ve bu sayede veri aktarımını kolaylaştırır.
Kullanıcı verilerini bir User nesnesinden UserDTO nesnesine taşımak için ideal bir çözümdür.
*/
@Service
public class ModelMapperManager implements ModelMapperService {

    private final ModelMapper modelMapper;

    public ModelMapperManager() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ModelMapper forDto() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
