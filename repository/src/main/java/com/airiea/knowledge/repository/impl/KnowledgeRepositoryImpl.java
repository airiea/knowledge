package com.airiea.knowledge.repository.impl;

import com.airiea.knowledge.common.factory.KnowledgeFactory;
import com.airiea.knowledge.model.dao.KnowledgeDAO;
import com.airiea.knowledge.model.dto.KnowledgeDTO;
import com.airiea.knowledge.repository.KnowledgeRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KnowledgeRepositoryImpl implements KnowledgeRepository {
    private static final String INDEX_NAME = "agent_name-entity_id";

    private final DynamoDBMapper dynamoDBMapper;
    private final KnowledgeFactory knowledgeFactory;


    public KnowledgeRepositoryImpl(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = Objects.requireNonNull(dynamoDBMapper, "Mapper cannot be null");
        this.knowledgeFactory = KnowledgeFactory.INSTANCE;
    }

    @Override
    public KnowledgeDTO getKnowledgeDTOById(String knowledgeId) {
        final KnowledgeDAO knowledgeDAO = this.getKnowledgeDAOById(knowledgeId);

        return knowledgeFactory.daoToDto(knowledgeDAO);
    }

    @Override
    public List<KnowledgeDTO> getKnowledgeDTOsByAgentName(String agentName) {
        final List<KnowledgeDAO> knowledgeDAOList = this.getKnowledgeDAOsByAgentName(agentName);

        return knowledgeDAOList.stream()
                .map(knowledgeFactory::daoToDto)
                .collect(Collectors.toList());
    }


    @Override
    public KnowledgeDAO getKnowledgeDAOById(String knowledgeId) {
        return dynamoDBMapper.load(KnowledgeDAO.class, knowledgeId);
    }

    @Override
    public KnowledgeDAO createKnowledgeDAO(KnowledgeDAO knowledgeDAO) {
        if (!Objects.isNull(dynamoDBMapper.load(knowledgeDAO))) {
            throw new IllegalArgumentException("Failed to create knowledgeDAO " + knowledgeDAO + ", item already exists.");
        }

        dynamoDBMapper.save(knowledgeDAO);
        return knowledgeDAO;
    }

    @Override
    public KnowledgeDAO updateKnowledgeDAO(KnowledgeDAO knowledgeDAO) {
        if (Objects.isNull(dynamoDBMapper.load(knowledgeDAO))) {
            throw new IllegalArgumentException("Failed to update knowledgeDAO " + knowledgeDAO + ", item not found.");
        }

        dynamoDBMapper.save(knowledgeDAO);
        return knowledgeDAO;
    }

    @Override
    public List<KnowledgeDAO> getKnowledgeDAOsByAgentName(String agentName) {
        KnowledgeDAO gsiKeys = new KnowledgeDAO();
        gsiKeys.setAgentName(agentName);

        DynamoDBQueryExpression<KnowledgeDAO> queryExpression = new DynamoDBQueryExpression<KnowledgeDAO>()
                .withIndexName(INDEX_NAME)
                .withHashKeyValues(gsiKeys)
                .withConsistentRead(false);

        return dynamoDBMapper.query(KnowledgeDAO.class, queryExpression);
    }

}
