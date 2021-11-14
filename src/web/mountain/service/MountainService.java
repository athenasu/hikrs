package web.mountain.service;

import web.mountain.entity.Mountain;

public interface MountainService {
	
	public int addMountain(Mountain mountain);
	public int insertPicture(byte[] file, Mountain mountain);

}
