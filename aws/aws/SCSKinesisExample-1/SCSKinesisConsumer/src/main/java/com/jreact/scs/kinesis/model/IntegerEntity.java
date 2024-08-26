package com.jreact.scs.kinesis.model;

public class IntegerEntity {
	
	private Integer number;
	private Integer remainder;
	private String  type;

	
	@Override
	public String toString() {
		return "IntegerEntity [number=" + number + ", remainder=" + remainder + ", type=" + type + "]";
	}

	public IntegerEntity() {
		super();
	}
	
	public IntegerEntity(Integer number) {
		super();
		this.number = number;
		this.remainder = number % 2;
		this.type = this.remainder == 0 ? "EVEN" : "ODD";
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getRemainder() {
		return remainder;
	}
	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
