package web.mountain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mountain.dao.MountainDAO;
import web.mountain.entity.Mountain;
import web.mountain.service.MountainService;

@Service
@Transactional
public class MountainServiceImpl implements MountainService {

	@Autowired
	private MountainDAO dao;
	
	@Override
	public int addMountain(Mountain mountain) {
		return dao.addMoutain(mountain);
	}
	
	public int insertPicture(byte[] file, Mountain mountain) {
		return dao.insertMtnPic(file, mountain);
	}

}
