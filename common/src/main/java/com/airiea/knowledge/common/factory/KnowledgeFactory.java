package com.airiea.knowledge.common.factory;

import com.airiea.knowledge.model.dao.KnowledgeDAO;
import com.airiea.knowledge.model.dto.KnowledgeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KnowledgeFactory {
    KnowledgeFactory INSTANCE = Mappers.getMapper(KnowledgeFactory.class);
    KnowledgeDTO daoToDto(KnowledgeDAO knowledgeDAO);
    KnowledgeDAO dtoToDao(KnowledgeDTO knowledgeDTO);
}
