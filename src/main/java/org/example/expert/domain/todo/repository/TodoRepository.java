package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long>, QTodoRepository {

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u WHERE DATE_FORMAT(t.modifiedAt, '%Y-%m-%d') BETWEEN :startDate AND :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(
            Pageable pageable,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u WHERE t.weather = :weather AND DATE_FORMAT(t.modifiedAt, '%Y-%m-%d') BETWEEN :startDate AND :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeatherOrderByModifiedAtDesc(
            Pageable pageable,
            @Param("weather") String weather,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN t.user " +
//            "WHERE t.id = :todoId")
//    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
