package com.airiea.knowledge.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeDTO {
    @JsonProperty("knowledge_id")
    String knowledgeId;

    @JsonProperty("entity_id")
    String entityId;

    @JsonProperty("agent_name")
    String agentName;

    String content;

    @JsonProperty("content_max_tokens")
    String contentMaxTokens;

    @JsonProperty("created_date")
    Date createdDate;

    @JsonProperty("updated_date")
    Date updatedDate;
}
