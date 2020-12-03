package ch.get.fx.common;

public enum CommandError {
	ERROR_PERMIT(0, "������"),
	ERROR_NIC_NAME(1, "����"),
	ERROR_ALREADY_DHCP(2, "DHCP");
	
	private int index;
	private String errorCode;
	
	private CommandError(int index, String errorCode) {
		this.index = index;
		this.errorCode = errorCode;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}