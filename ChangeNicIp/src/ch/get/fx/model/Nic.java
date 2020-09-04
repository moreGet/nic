package ch.get.fx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nic {

	private final StringProperty nicKind;
	private final StringProperty nicName;
	private final StringProperty nicIp;
	private final StringProperty nicMask;
	private final StringProperty nicGate;
	private final StringProperty nicDns01;
	private final StringProperty nicDns02;
	private final StringProperty nicInfo;
	
	public Nic(String nicKind, String nicName, String nicIp, String nicMask, String nicGate,
			String nicDns01, String nicDns02, String nicInfo) {
		
		this.nicKind = new SimpleStringProperty(nicKind);
		this.nicName = new SimpleStringProperty(nicName);
		this.nicIp = new SimpleStringProperty(nicIp);
		this.nicMask = new SimpleStringProperty(nicMask);
		this.nicGate = new SimpleStringProperty(nicGate);
		this.nicDns01 = new SimpleStringProperty(nicDns01);
		this.nicDns02 = new SimpleStringProperty(nicDns02);
		this.nicInfo = new SimpleStringProperty(nicInfo);
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
}

