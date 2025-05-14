package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.manager.entity.QManager;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QTodoRepositoryImpl implements QTodoRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {

        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        Todo result = jpaQueryFactory.selectFrom(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne(); // 단일 결과를 가져올때 사용

        // 결과가 없으면 null반환 ofNullable 메소드 사용
        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoSearchResponse> findBySearch(String title, LocalDate startDate, LocalDate endDate, String nickname, Pageable pageable) {
        QTodo todo = QTodo.todo;
        QManager manager = QManager.manager;
        QComment comment = QComment.comment;
        QUser user = QUser.user;

        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : LocalDateTime.MIN;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : LocalDateTime.MAX;

        // 본 쿼리 ( 실제 결과 조회 )
        List<TodoSearchResponse> content = jpaQueryFactory
                .select(Projections.constructor(
                        TodoSearchResponse.class, // 생성자 매핑
                        todo.title,
                        manager.id.countDistinct(), // Todo 하나에 연결된 고유한 매니저수
                        JPAExpressions.select(comment.count()) // 서브쿼리로 todo에 해당하는 댓글수 계산
                                .from(comment)
                                .where(comment.todo.id.eq(todo.id))
                ))
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(manager.user, user) // 담당자 정보와 닉네임 조건 매칭
                .where(
                        titleContains(title),
                        todo.createdAt.between(startDateTime, endDateTime),
                        nicknameContains(nickname) // 제목, 생성일자, 닉네임 조건
                )
                .groupBy(todo.id, todo.title) // count, 서브쿼리 등으로 인한 그룹핑 필요
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) // 페이징 처리 (offset, limit)
                .fetch();

        // 페이징 처리를 위한 전체 결과 개수 조회 쿼리
        Long total = jpaQueryFactory
                .select(todo.countDistinct())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(manager.user, user)
                .where(
                        titleContains(title),
                        todo.createdAt.between(startDateTime, endDateTime),
                        nicknameContains(nickname)
                )
                .fetchOne();

        // Spring Data Page<T> 객체로 반환 t otal이 null이면 0 처리
        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    private BooleanExpression titleContains(String title) {
        return (title == null || title.isBlank()) ? null : QTodo.todo.title.containsIgnoreCase(title);
    }

    private BooleanExpression nicknameContains(String nickname) {
        return (nickname == null || nickname.isBlank()) ? null: QUser.user.nickname.containsIgnoreCase(nickname);
    }
}
