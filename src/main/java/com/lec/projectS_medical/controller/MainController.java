package com.lec.projectS_medical.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lec.projectS_medical.board.C;
import com.lec.projectS_medical.command.CmtListCommand;
import com.lec.projectS_medical.command.Command;
import com.lec.projectS_medical.command.CommentCommand;
import com.lec.projectS_medical.command.CommentWriteCommand;
import com.lec.projectS_medical.command.MyTestCommand;
import com.lec.projectS_medical.command.ObesityCommand;
import com.lec.projectS_medical.command.ResSelectCommand;
import com.lec.projectS_medical.command.ReservationCommand;
import com.lec.projectS_medical.beans.BoardDTO;
import com.lec.projectS_medical.beans.CommentDTO;
import com.lec.projectS_medical.beans.ReservationDAO;
import com.lec.projectS_medical.beans.ReservationDTO;
import com.lec.projectS_medical.command.DeleteCommand;
import com.lec.projectS_medical.command.DiseaseBeforeCommand;
import com.lec.projectS_medical.command.DiseaseCommand;
import com.lec.projectS_medical.command.HighBP1Command;
import com.lec.projectS_medical.command.HighBP2Command;
import com.lec.projectS_medical.command.HighBPBeforeCommand;
import com.lec.projectS_medical.command.ListCommand;
import com.lec.projectS_medical.command.MyInfoCommand;
import com.lec.projectS_medical.command.ViewCommand;
import com.lec.projectS_medical.command.WriteCommand;

@Controller
@RequestMapping("/KHJ")
public class MainController {

	private Command command;
	//private JdbcTemplate template;
	
	// MyBatis
	private SqlSession sqlSession;
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		C.sqlSession = sqlSession;
	}

	public MainController() {
		System.out.println("MainController ??????");
	}
	
	// ?????? Model ??? ?????? ?????? ????????? ??????
	public void printModel(Model model) {
		Map<String, Object> map = model.asMap();
		
		Set<String> keySet = map.keySet();
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
	}
	
	
	@RequestMapping("/mainUser.do")
	public String mainUser(Model model) {
		
		new DiseaseCommand().execute(model);
		new DiseaseBeforeCommand().execute(model);
		new ObesityCommand().execute(model);
		new HighBPBeforeCommand().execute(model);
		new HighBP1Command().execute(model);
		new HighBP2Command().execute(model);
		
		
		return "main/mainUser";
	}
	
	@RequestMapping(value = "/brandStory.do") 
	public String brandStory(Model model){
		return "main/brandStory";
	}
	
	@RequestMapping(value = "/myTest.do")
	public String myTest(String id, Model model){
		
		model.addAttribute("id", id);  // ?????????????????? Model ??? ?????????..
		new MyTestCommand().execute(model);
		
		printModel(model);
		
		return "main/myTest";
	}
	
	@RequestMapping(value = "/weather.do") 
	public String weather(Model model){
		return "main/weather";
	}
	
	@RequestMapping(value = "/reservation.do") 
	public String reservation(String id, Model model){
		
		model.addAttribute("id", id);  // ?????????????????? Model ??? ?????????..
		new ResSelectCommand().execute(model);
		new MyInfoCommand().execute(model);
		
		return "main/reservation";
	}
	
	@RequestMapping(value = "/reservationOk.do", method = RequestMethod.GET) 
	public String reservationOk(ReservationDTO dto, Model model){
		model.addAttribute("dto", dto);  // ?????????????????? Model ??? ?????????..
		new ReservationCommand().execute(model);
		
		printModel(model);
		
		return "main/reservationOk";
	}
	
	

	
	
	
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		
		new ListCommand().execute(model);
		new CmtListCommand().execute(model);
		
		printModel(model);
		
		return "main/list";
	}
	
	
	@RequestMapping("/write.do")
	public String write(String id, Model model) {
		
		model.addAttribute("id", id);  // ?????????????????? Model ??? ?????????..
		new MyInfoCommand().execute(model);
		
		printModel(model);
		
		return "main/write";
	}
	
	@RequestMapping(value = "/writeOk.do", method = RequestMethod.GET) 
	public String writeOk(BoardDTO dto, Model model){
		model.addAttribute("dto", dto);  // ?????????????????? Model ??? ?????????..
		new WriteCommand().execute(model);
		
		printModel(model);
		
		return "main/writeOk";
	}
	
	@RequestMapping(value = "/view.do")
	public String view(int uid, Model model) {
		model.addAttribute("uid", uid);   // parameter ??? ?????? model ??? ???????????? ??????, request ??? ????????? ????????? ??????.
		new ViewCommand().execute(model);
		return "main/view";
	}
	
	@RequestMapping(value = "/deleteOk.do")
	public String deleteOk(int uid, Model model) {
		model.addAttribute("uid", uid);
		new DeleteCommand().execute(model);
		return "main/deleteOk";
	}
	
	
	
	
	
	
	
	
	
} // end Controller


















