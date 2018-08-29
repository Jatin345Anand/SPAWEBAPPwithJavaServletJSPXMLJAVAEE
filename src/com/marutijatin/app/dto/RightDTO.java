package com.marutijatin.app.dto;

public class RightDTO {
public RightDTO(String name, String screename) {
		super();
		this.name = name;
		this.screename = screename;
	}
private String name;
private String screename;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getScreename() {
	return screename;
}
public void setScreename(String screename) {
	this.screename = screename;
}

}
