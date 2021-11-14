package web.mountain.dao;

import web.mountain.entity.Mountain;

public interface MountainDAO {

	public int addMoutain(Mountain mountain);
	public int insertMtnPic(byte[] file, Mountain mountain);
	public Mountain updateMountain(Mountain mountain);
}
