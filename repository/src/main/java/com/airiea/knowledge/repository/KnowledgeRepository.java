package com.airiea.knowledge.repository;

import com.airiea.knowledge.model.dao.KnowledgeDAO;
import com.airiea.knowledge.model.dto.KnowledgeDTO;

import java.util.List;

/**
 * Data Access Object (DAO) interface for tasks.
 * This provides methods to perform operations on tasks.
 */
public interface KnowledgeRepository {
    KnowledgeDTO getKnowledgeDTOById(String knowledgeId);
    List<KnowledgeDTO> getKnowledgeDTOsByAgentName(String agentName);

    KnowledgeDAO getKnowledgeDAOById(String knowledgeId);
    KnowledgeDAO createKnowledgeDAO(KnowledgeDAO knowledgeDAO);
    KnowledgeDAO updateKnowledgeDAO(KnowledgeDAO knowledgeDAO);
    List<KnowledgeDAO> getKnowledgeDAOsByAgentName(String agentName);
}
