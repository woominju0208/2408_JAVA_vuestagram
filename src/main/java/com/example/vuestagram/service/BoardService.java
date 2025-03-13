package com.example.vuestagram.service;

import com.example.vuestagram.model.Board;
import com.example.vuestagram.model.QBoard;
import com.example.vuestagram.repogitory.BoardRepogitory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepogitory boardRepogitory;
    private final JPAQueryFactory queryFactory;

    public Board test() {
//        Optional<Board> board = boardRepogitory.findById(60L);

        //  QueryDSL(쿼리빌더와 비슷)
        QBoard qBoard = QBoard.board;  // QueryDSL이 자동으로 생성해주는 Board 엔티티 기반의 클래스
        JPAQuery<Board> query = queryFactory.selectFrom(qBoard);

        // (false) 를 쓰면 제일 첫번째 글을 가져옴
        if(true) {
            query.where(
                    qBoard.boardId.eq(60L)
            );
        }

        return query.fetchFirst();
    }
}
