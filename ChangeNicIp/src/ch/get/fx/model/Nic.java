package ch.get.fx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nic {

	private final StringProperty nicKind; // NIC��ġ
	private final StringProperty nicName; // ��ī�� �̸�
	private final StringProperty nicIp; // ��ī�� IP
	private final StringProperty nicMask; // �������
	private final StringProperty nicGate; // ����Ʈ ����
	private final StringProperty nicDns01; // DNS 1��
	private final StringProperty nicDns02; // DNS 2��
	private final StringProperty nicInfo; // ����
	
	public Nic() {
		this(null, null, null, null, null, null, null, null);
	}
	
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
	 * PropertyGetter
	 */
	public StringProperty nicNameProperty() {
		return nicName;
	}

	public StringProperty nicIpProperty() {
		return nicIp;
	}

	public StringProperty nicMaskProperty() {
		return nicMask;
	}

	public StringProperty nicGateProperty() {
		return nicGate;
	}
	
	public StringProperty nicDns01Property() {
		return nicDns01;
	}

	public StringProperty nicDns02Property() {
		return nicDns02;
	}

	public StringProperty nicKindProperty() {
		return nicKind;
	}

	public StringProperty nicInfoProperty() {
		return nicInfo;
	}
	
	/*
	 * Getter
	 */
	public String getNicName() {
		return nicName.get();
	}

	public String getNicIp() {
		return nicIp.get();
	}

	public String getNicMask() {
		return nicMask.get();
	}

	public String getNicGate() {
		return nicGate.get();
	}

	public String getNicDns01() {
		return nicDns01.get();
	}

	public String getNicDns02() {
		return nicDns02.get();
	}

	public String getNicKind() {
		return nicKind.get();
	}

	public String getNicInfo() {
		return nicInfo.get();
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
	
	@Override
	public String toString() {
		String content = 
				"�뵵 : "+this.nicKind.getValue()
				+"\n��ī�� ���� : "+this.nicName.getValue()
				+"\n�� �ּ� : "+this.nicIp.getValue();
		
		return content;
	}
}

