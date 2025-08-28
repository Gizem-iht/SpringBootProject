package com.blog.BloggingProject.Core.Mapping.ModelMapperService;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forDto();

    ModelMapper forRequest();
}
