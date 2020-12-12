package ch.get.fx.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nicInfo")
public class NicInfoListWrapper {

	private List<Nic> nicInfo;
	
	@XmlElement(name = "nic")
	public List<Nic> getNicInfo() {
		return nicInfo;
	}
	
	public void setNicInfo(List<Nic> nicInfo) {
		this.nicInfo = nicInfo;
	}
}
