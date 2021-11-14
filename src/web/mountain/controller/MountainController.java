package web.mountain.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.mountain.entity.Mountain;
import web.mountain.service.MountainService;

@RestController
@RequestMapping("mountain")
public class MountainController {

	@Autowired
	private MountainService service;

	@PostMapping(path = "addMountain", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public int addMountain(@RequestBody Mountain mountain) {
		byte[] file = Base64.getDecoder().decode(mountain.getPicStr());
//		int mtnPic = service.insertPicture(file, mountain);
		mountain.setMountainPic(file);
		int result = service.addMountain(mountain);
		if (result < 0) {
			mountain.setMessage("Unable to save");
			mountain.setSuccessful(false);
			return result;
		}
		mountain.setMessage("Mountain saved");
		mountain.setSuccessful(true);
		return result;
	}

}
