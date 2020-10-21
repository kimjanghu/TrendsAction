package com.action.trends.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.Board;
import com.action.trends.dto.Message;
import com.action.trends.dto.NewsBoard;
import com.action.trends.dto.NewsList;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.TwittBoard;
import com.action.trends.dto.TwittList;
import com.action.trends.dto.User;
import com.action.trends.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value="보드 리스트 조회", response = List.class)
	@GetMapping("/board/{userId}")
	public ResponseEntity<Map<String, Object>> getBoardList(@PathVariable int userId) {
		ResponseEntity<Map<String, Object>> entity = null;
		List<Board> list = new ArrayList<Board>();
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = boardService.getBoardList(userId);
			resultMap.put("status", true);
			if (list.size() == 0) {
				resultMap.put("message", "생성된 보드가 없습니다.");
			} else {
				resultMap.put("message", "보드 리스트 조회에 성공했습니다.");
			}
			resultMap.put("data", list);
			entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드원 조회", response = List.class)
	@GetMapping("/board/sharer/{boardId}")
	public ResponseEntity<Map<String, Object>> getSharerList(@PathVariable int boardId) {
		ResponseEntity<Map<String, Object>> entity = null;
		List<Sharer> list = new ArrayList<Sharer>();
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = boardService.getSharerList(boardId);
			resultMap.put("status", true);
			resultMap.put("message", "보드원 조회에 성공했습니다.");
			resultMap.put("data", list);
			entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 내 뉴스 조회", response = String.class)
	@GetMapping("/board/contents/news/{boardId}")
	public ResponseEntity<Map<String, Object>> getNewsList(@PathVariable int boardId) {
		ResponseEntity<Map<String, Object>> entity = null;
		List<NewsList> list = new ArrayList<NewsList>();
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = boardService.getNewsList(boardId);
			resultMap.put("status", true);
			if (list.size() != 0) {
				resultMap.put("message", boardId + " 보드의 뉴스 리스트 조회에 성공했습니다.");
				resultMap.put("data", list);
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} else {
				resultMap.put("message", boardId + " 보드에 담긴 뉴스가 없습니다.");
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 내 트위터 조회", response = String.class)
	@GetMapping("/board/contents/twitter/{boardId}")
	public ResponseEntity<Map<String, Object>> getTwitterList(@PathVariable int boardId) {
		ResponseEntity<Map<String, Object>> entity = null;
		List<TwittList> list = new ArrayList<TwittList>();
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = boardService.getTwitterList(boardId);
			resultMap.put("status", true);
			if (list.size() != 0) {
				resultMap.put("message", boardId + " 보드의 트위터 리스트 조회에 성공했습니다.");
				resultMap.put("data", list);
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} else {
				resultMap.put("message", boardId + " 보드에 담긴 트윗이 없습니다.");
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="초대 메시지 리스트 조회", response = String.class)
	@GetMapping("/message/list/{userId}")
	public ResponseEntity<Map<String, Object>> getMessageList(@PathVariable int userId) {
		ResponseEntity<Map<String, Object>> entity = null;
		List<Message> list = new ArrayList<Message>();
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = boardService.getMessageList(userId);
			resultMap.put("status", true);
			if (list.size() != 0) {
				resultMap.put("message", userId + " 사용자에게 온 보드 초대 메시지 리스트 조회에 성공했습니다.");
				resultMap.put("data", list);
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} else {
				resultMap.put("message", userId + " 사용자에게 온 보드 초대 메시지가 없습니다.");
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 생성")
	@PostMapping("/board")
	public ResponseEntity<Map<String, Object>> createBoard(@RequestBody Map<String, Object> data) {
		ResponseEntity<Map<String, Object>> entity = null;
		int userId = (int) data.get("userId");
		String name = (String) data.get("name");
		String thumbnail = (String) data.get("thumbnail");
		
		try {
			Board board = new Board(name, thumbnail);
			boardService.createBoard(userId, board);
			entity = handleSuccess("보드가 생성됐습니다.");
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드에 트윗 담기")
	@PostMapping("/board/contents/twitter")
	public ResponseEntity<Map<String, Object>> addTwitt(@RequestBody Map<String, Object> data) {
		ResponseEntity<Map<String, Object>> entity = null;
		int boardId = (int) data.get("boardId");
		int twitterId = (int) data.get("twitterId");
		int userId = (int) data.get("userId");
		
		try {
			TwittBoard twittBoard = new TwittBoard(boardId, twitterId, userId);
			if (boardService.addTwitt(twittBoard) == 1) {
				entity = handleSuccess(boardId + " 보드에 " + twitterId + " 트윗이 담겼습니다." + " by " + userId + " 사용자");				
			} else {
				entity = handleSuccess("fail");
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드에 뉴스 담기")
	@PostMapping("/board/contents/news")
	public ResponseEntity<Map<String, Object>> addNews(@RequestBody Map<String, Object> data) {
		ResponseEntity<Map<String, Object>> entity = null;
		int boardId = (int) data.get("boardId");
		int newsId = (int) data.get("newsId");
		int userId = (int) data.get("userId");
		
		try {
			NewsBoard newsBoard = new NewsBoard(boardId, newsId, userId);
			if (boardService.addNews(newsBoard) == 1) {
				entity = handleSuccess(boardId + " 보드에 " + newsId + " 뉴스가 담겼습니다." + " by " + userId + " 사용자");				
			} else {
				entity = handleSuccess("fail");
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="유저 검색(이메일)")
	@PostMapping("/board/searchuser")
	public ResponseEntity<Map<String, Object>> searchUser(@RequestBody String email) {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			User user = boardService.searchUser(email);
			if (user != null) {
				resultMap.put("status", true);
				resultMap.put("message", "유저 검색에 성공했습니다.");
				resultMap.put("data", user);
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} else {
				resultMap.put("status", true);
				resultMap.put("message", "해당하는 유저가 없습니다.");
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}

		return entity;
	}
	
	@ApiOperation(value="보드 초대", response= String.class)
	@PostMapping("/board/invite")
	public ResponseEntity<Map<String, Object>> inviteSharer(@RequestBody Message message) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			if (boardService.inviteSharer(message) == 1) {
				entity = handleSuccess(message.getSendFrom() + " 사용자가 " + message.getSendTo() + " 사용자를 " + message.getBoardId() + " 보드로 초대 완료했습니다.");
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}

	@ApiOperation(value="보드 정보 변경", response = String.class)
	@PutMapping("/board")
	public ResponseEntity<Map<String, Object>> updateBoard(@RequestBody Map<String, Object> data) {
		ResponseEntity<Map<String, Object>> entity = null;
		int boardId = (int) data.get("boardId");
		String name = (String) data.get("name");
		
		try {
			if (boardService.updateBoard(boardId, name) == 0) {
				entity = handleSuccess("fail");
			} else {
				entity = handleSuccess(boardId + " 보드의 이름을 " + name + " (으)로 변경했습니다.");				
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드원 권한 변경", response = String.class)
	@PutMapping("/board/updateauth")
	public ResponseEntity<Map<String, Object>> updateUserAuth(@RequestBody Map<String, Object> data) {
		ResponseEntity<Map<String, Object>> entity = null;
		int userId = (int) data.get("userId");
		int boardId = (int) data.get("boardId");
		String authority = (String) data.get("authority");
		
		try {
			if (boardService.updateUserAuth(userId, boardId, authority) == 0) {
				entity = handleSuccess("fail");
			} else {
				entity = handleSuccess(userId + " 사용자의 권한을 " + authority + " 로 변경했습니다.");				
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 삭제", response = String.class)
	@DeleteMapping("/board/{boardId}")
	public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable int boardId) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			boardService.deleteBoard(boardId);
			entity = handleSuccess(boardId + " 보드가 삭제됐습니다.");
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 탈퇴", response = String.class)
	@DeleteMapping("/board/leave/{userId}/{boardId}")
	public ResponseEntity<Map<String, Object>> leaveBoard(@PathVariable int userId, @PathVariable int boardId) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			if (boardService.leaveBoard(userId, boardId) == 0) {
				entity = handleSuccess("fail");
			} else {
				entity = handleSuccess(userId + " 사용자가 " + boardId + " 보드를 탈퇴했습니다.");				
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 내 뉴스글 삭제", response = String.class)
	@DeleteMapping("/board/contents/news/{boardId}/{newsId}")
	public ResponseEntity<Map<String, Object>> deleteNews(@PathVariable int boardId, @PathVariable int newsId) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			if (boardService.deleteNews(boardId, newsId) == 0) {
				entity = handleSuccess("fail");
			} else {
				entity = handleSuccess(boardId + " 보드에서 " + newsId + " 뉴스를 삭제했습니다.");				
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	@ApiOperation(value="보드 내 트윗 삭제", response = String.class)
	@DeleteMapping("/board/contents/twitter/{boardId}/{twitterId}")
	public ResponseEntity<Map<String, Object>> deleteTwitts(@PathVariable int boardId, @PathVariable int twitterId) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			if (boardService.deleteTwitts(boardId, twitterId) == 0) {
				entity = handleSuccess("fail");
			} else {
				entity = handleSuccess(boardId + " 보드에서 " + twitterId + " 트윗을 삭제했습니다.");				
			}
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		
		return entity;
	}
	
	// ------------------- 예외처리 -----------------------
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("message", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("message", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
