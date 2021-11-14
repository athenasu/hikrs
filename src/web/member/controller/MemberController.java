package web.member.controller;

import java.io.FileOutputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import web.member.entity.Member;
import web.member.service.MemberService;

@RestController // using Spring RESTful to use this controller, which means it will get JSON information
@RequestMapping("member")
@SessionAttributes({ "member" })
public class MemberController {

	@Autowired
	private MemberService service; // @Autowired here means MemberService service = new MemberService

	@PostMapping(path = "memberUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Member updateMember(@RequestBody Member member) {
		byte[] file = Base64.getDecoder().decode(member.getPicStr());
		service.insertPic(file, member);
		return service.updateMember(member);
	}

	@GetMapping("findMemberById")
	public Member findMemberById() {
		return service.findById(2);
	}

	@PostMapping(path = "register", consumes = { MediaType.APPLICATION_JSON_VALUE }) // tells the front end that we're
																						// sending a JSON type
	public int addMember(@RequestBody Member member) {
		int result = service.registerMember(member);
		return result;
	}

	@PostMapping(path = "upload", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public int upload(@RequestBody Member member) {
		System.out.println(member.getMemberName());
		System.out.println(member.getPicStr());
		// getting and decoding the file to Base64
		byte[] file = Base64.getDecoder().decode(member.getPicStr());

		try (FileOutputStream fos = new FileOutputStream("/Users/athenasu/Downloads/aaa.jpg");) {
			fos.write(file);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
//	@DeleteMapping("/")
//	public void m1() {
//		
//	}
//	
//	@PutMapping("/")
//	public void m2() {
//		
//	}
	// will need to add this method, as soon as i set the user's email and password, i'll be able to set their information in the session
	// and everyone else will be able to get it
	@GetMapping("login")
	public void login(Model model, Member member) {
//		member = service.findByUsernameAndPassword(member);
		member.setMemberId(123);
		member.setMemberUsername("william");
		model.addAttribute("member", member);
	}
}
