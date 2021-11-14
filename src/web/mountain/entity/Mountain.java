package web.mountain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import web.Core;

@Entity
@Table(name = "mountain")
public class Mountain extends Core {

	private static final long serialVersionUID = 1L;

	private Integer mountainId;
	private Integer mountainDistrict;
	private Double mountainLongitude;
	private Double mountainLatitude;
	private String mountainName;
	private byte[] mountainPic;
	private String picStr;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mountain_id")
	public Integer getMountainId() {
		return mountainId;
	}

	public void setMountainId(Integer mountainId) {
		this.mountainId = mountainId;
	}

	@Column(name = "mountain_district")
	public Integer getMountainDistrict() {
		return mountainDistrict;
	}

	public void setMountainDistrict(Integer mountainDistrict) {
		this.mountainDistrict = mountainDistrict;
	}
	
	@Column(name = "mountain_longitude", columnDefinition = "DECIMAL")
	public Double getMountainLongitude() {
		return mountainLongitude;
	}

	public void setMountainLongitude(Double mountainLongitude) {
		this.mountainLongitude = mountainLongitude;
	}

	@Column(name = "mountain_latitude", columnDefinition = "DECIMAL")
	public Double getMountainLatitude() {
		return mountainLatitude;
	}

	public void setMountainLatitude(Double mountainLatitude) {
		this.mountainLatitude = mountainLatitude;
	}

	@Column(name = "mountain_name")
	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	@Lob
	@Column(name = "mountain_pic")
	public byte[] getMountainPic() {
		return mountainPic;
	}

	public void setMountainPic(byte[] mountainPic) {
		this.mountainPic = mountainPic;
	}

	@Transient
	public String getPicStr() {
		return picStr;
	}

	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}



}
