package org.example.expert.domain.todo.dto.response;

import lombok.Getter;
import org.example.expert.domain.common.entity.Timestamped;

@Getter
public class TodoSearchResponse {

    private final String title;
    private final Long countManager;
    private final Long countComment;

    public TodoSearchResponse( String title, Long countManager, Long countComment) {
        this.title = title;
        this.countManager = countManager;
        this.countComment = countComment;
    }
}
