package com.mongodb.domains;

public class DataConfigItem {
	
	private String itemId;
	private int minor;

	public String getItemId() {
		return itemId;
	}

	

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}
	
	@Override
	public String toString() {
		return "DataConfigItem [itemId=" + itemId + ", minor=" + minor + "]";
	}
}
