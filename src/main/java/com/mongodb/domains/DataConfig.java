package com.mongodb.domains;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection= DataConfig.COLLECTION_NAME)
public class DataConfig {

	public final static String COLLECTION_NAME = "DataConfig";
	@Id
	@Indexed
	private String id;
	
	private String major;
	@Field("dataConfigItms")
	private List<DataConfigItem> dataConfigItms;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public List<DataConfigItem> getDataConfigItms() {
		return dataConfigItms;
	}
	public void setDataConfigItms(List<DataConfigItem> dataConfigItms) {
		this.dataConfigItms = dataConfigItms;
	}
	
	@Override
	public String toString() {
		return "DataConfig [id=" + id + ", major=" + major
				+ ", dataConfigItms=" + dataConfigItms + "]";
	}
	
}
