package edu.bit.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import edu.bit.board.vo.BoardVO;

@Service
public interface BoardService {
	
	public List<BoardVO> selectBoardList();

//	public void insertBoard(@Param("bName")String bName, @Param("bTitle")String bTitle, 
//			@Param("bContent")String bContent);
//	//파라미터로 받는 값이 두개 이상일때는 어노테이션 @Param 사용하기 ! 
	
	public void insertVOBoard(@Param("boardVO") BoardVO boardVO);
	
	

	public BoardVO contentView(String bId);
	
	public void modify(@Param("bName")String bName, @Param("bTitle")String bTitle, @Param("bContent")String bContent,@Param("bId") String bId);
	

	
	@Delete("delete from mvc_board WHERE bId = #{bId}")
	public void delete(String bId);
	
	public void upHit(String bId);
	
	public void replyShape(@Param("bGroup")String bGroup, @Param("bStep")String bStep);
	
	public void reply(@Param("boardVO") BoardVO boardVO);
	
	public BoardVO reply_view(String bId);
	
	
}
