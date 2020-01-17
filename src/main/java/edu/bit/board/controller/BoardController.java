package edu.bit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bit.board.service.BoardService;
import edu.bit.board.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		List<BoardVO> list =  boardservice.selectBoardList();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO) {
		System.out.println("write()");
		boardservice.insertVOBoard(boardVO);
		//넘겨야할 값이 여러개일때 커맨드 객체를 이용해 한번에 넘길수 있다. 
		
		//boardservice.insertBoard(boardVO.getbName(), boardVO.getbTitle(), boardVO.getbContent());
		//인자 세개를 board.xml로 넘겨야한다!
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(BoardVO boardVO, Model model) {
		System.out.println("content_view()");
		int id = boardVO.getbId();
		BoardVO view  = boardservice.contentView(Integer.toString(id));
		model.addAttribute("content_view", view);
		boardservice.upHit(Integer.toString(id));
		
		return "content_view";
	}
	
	
	@RequestMapping("/modify")
	public String modify(BoardVO boardVO) {
		System.out.println("modify()");
		
		boardservice.modify(boardVO.getbName(), boardVO.getbTitle(), boardVO.getbContent(), Integer.toString(boardVO.getbId()));
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		System.out.println("delete()");
		
		boardservice.delete(Integer.toString(boardVO.getbId()));
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO, Model model) {
		System.out.println("reply()");
		
		boardservice.replyShape(Integer.toString(boardVO.getbGroup()), Integer.toString(boardVO.getbStep()));
		boardservice.reply(boardVO);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(BoardVO boardVO, Model model) {
		System.out.println("reply_view()");
		
		int id = boardVO.getbId();
		boardservice.upHit(Integer.toString(id));
		BoardVO view  = boardservice.reply_view(Integer.toString(id));
		model.addAttribute("reply_view", view);

		
		return "reply_view";
	}
	

}
