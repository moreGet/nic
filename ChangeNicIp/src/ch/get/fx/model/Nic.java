package ch.get.fx.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nic {

	private final StringProperty nicKind; // NIC위치
	private final StringProperty nicName; // 랜카드 이름
	private final StringProperty nicIp; // 랜카드 IP
	private final StringProperty nicMask; // 서브넷팅
	private final StringProperty nicGate; // 게이트 웨이
	private final StringProperty nicDns01; // DNS 1번
	private final StringProperty nicDns02; // DNS 2번
	private final StringProperty nicInfo; // 설명
	private final BooleanProperty selNic; // 현재 적용 되어있는 인터페이스 정보
	
	public Nic(String nicKind, String nicName, String nicIp, String nicMask, String nicGate,
			String nicDns01, String nicDns02, String nicInfo, Boolean selNic) {
		
		this.nicKind = new SimpleStringProperty(nicKind);
		this.nicName = new SimpleStringProperty(nicName);
		this.nicIp = new SimpleStringProperty(nicIp);
		this.nicMask = new SimpleStringProperty(nicMask);
		this.nicGate = new SimpleStringProperty(nicGate);
		this.nicDns01 = new SimpleStringProperty(nicDns01);
		this.nicDns02 = new SimpleStringProperty(nicDns02);
		this.nicInfo = new SimpleStringProperty(nicInfo);
		this.selNic = new SimpleBooleanProperty(selNic);
	}

	/*
	 * Getter
	 */
	public StringProperty getNicName() {
		return nicName;
	}

	public StringProperty getNicIp() {
		return nicIp;
	}

	public StringProperty getNicMask() {
		return nicMask;
	}

	public StringProperty getNicGate() {
		return nicGate;
	}

	public StringProperty getNicDns01() {
		return nicDns01;
	}

	public StringProperty getNicDns02() {
		return nicDns02;
	}

	public StringProperty getNicKind() {
		return nicKind;
	}

	public StringProperty getNicInfo() {
		return nicInfo;
	}
	
	public BooleanProperty getSelNic() {
		return selNic;
	}

	/*
	 * Setter
	 */
	public void setNicName(String nicName) {
		this.nicName.set(nicName);
	}
	
	public void setNicIp(String nicIp) {
		this.nicIp.set(nicIp);
	}
	
	public void setNicMask(String nicMask) {
		this.nicMask.set(nicMask);
	}
	
	public void setNicGate(String nicGate) {
		this.nicGate.set(nicGate);
	}
	
	public void setNicDns01(String nicDns01) {
		this.nicDns01.set(nicDns01);
	}
	
	public void setNicDns02(String nicDns02) {
		this.nicDns02.set(nicDns02);
	}
	
	public void setNicKind(String nicKind) {
		this.nicKind.set(nicKind);
	}
	
	public void setNicInfo(String nicInfo) {
		this.nicInfo.set(nicInfo);
	}
	
	public void setSelNic(boolean selNic) {
		this.selNic.setValue(selNic);
	}
	
	@Override
	public String toString() {
		String content = 
				"용도 : "+this.nicKind.getValue()
				+"\n랜카드 종류 : "+this.nicName.getValue()
				+"\n논리 주소 : "+this.nicIp.getValue();
		
		return content;
	}
}

