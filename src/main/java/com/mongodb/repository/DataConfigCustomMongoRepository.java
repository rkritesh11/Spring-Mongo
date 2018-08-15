package com.mongodb.repository;

import java.util.List;

import com.mongodb.domains.DataConfigItem;

public interface DataConfigCustomMongoRepository {

	List<DataConfigItem> getDataConfigByConfigIdAndItemId(String configId,
			String itemId);

	List<DataConfigItem> getDataConfigByConfigIdAndMinor(String configId,
			Integer minor);

	List<DataConfigItem> getDataConfigByConfigId(String configId);

	void deleteByConfigIdAndItemId(String configId, String itemId);

}
