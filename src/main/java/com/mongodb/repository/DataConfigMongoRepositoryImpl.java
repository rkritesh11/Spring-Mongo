package com.mongodb.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mongodb.domains.DataConfig;
import com.mongodb.domains.DataConfigItem;

public class DataConfigMongoRepositoryImpl implements
		DataConfigCustomMongoRepository {

	private MongoTemplate mongoTemplate;

	@Autowired
	public DataConfigMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public DataConfigMongoRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DataConfigItem> getDataConfigByConfigIdAndItemId(
			String configId, String itemId) {
		MatchOperation matchOperation = Aggregation.match(Criteria.where("_id")
				.is(new ObjectId(configId)));
		ProjectionOperation projectionOperation = Aggregation
				.project()
				.and(ArrayOperators.Filter
						.filter("dataConfigItms")
						.as("dataConfigItem")
						.by(ComparisonOperators.Eq.valueOf("dataConfigItem.itemId")
								.equalToValue(itemId))).as("dataConfigItms").and("dataConfigItms").nested(Aggregation.bind("dataConfigItms", "dataConfigItms"));
		Aggregation agg = Aggregation.newAggregation(matchOperation,
				projectionOperation);
		List<DataConfigItem> dataConfigItems = new ArrayList<DataConfigItem>();
		DataConfig configItems = mongoTemplate.aggregate(agg,
				DataConfig.class, DataConfig.class)
				.getUniqueMappedResult();
		
		if (null!= configItems) {
			dataConfigItems= configItems.getDataConfigItms();
		}
		return dataConfigItems;
	}

	public List<DataConfigItem> getDataConfigByConfigIdAndMinor(String configId,
			Integer minor) {
		MatchOperation matchOperation = Aggregation.match(Criteria.where("_id")
				.is(new ObjectId(configId)));
		ProjectionOperation projectionOperation = Aggregation
				.project()
				.and(ArrayOperators.Filter
						.filter("dataConfigItms")
						.as("dataConfigItem")
						.by(ComparisonOperators.Eq.valueOf("dataConfigItem.minor")
								.equalToValue(minor))).as("dataConfigItms");
		Aggregation agg = Aggregation.newAggregation(matchOperation,
				projectionOperation);
		List<DataConfigItem> dataConfigItems = new ArrayList<DataConfigItem>();
		DataConfig configItems = mongoTemplate.aggregate(agg,
				DataConfig.class, DataConfig.class)
				.getUniqueMappedResult();
		
		if (null!= configItems) {
			dataConfigItems= configItems.getDataConfigItms();
		}
		return dataConfigItems;
	}

	public List<DataConfigItem> getDataConfigByConfigId(String configId) {
		MatchOperation matchOperation = Aggregation.match(Criteria.where("id")
				.is(new ObjectId(configId)));
		ProjectionOperation projectionOperation = Aggregation
				.project("dataConfigItms");
		Aggregation agg = Aggregation.newAggregation(matchOperation,
				projectionOperation);
		List<DataConfigItem> dataConfigItems = new ArrayList<DataConfigItem>();
		DataConfig configItems = mongoTemplate.aggregate(agg,
				DataConfig.class, DataConfig.class)
				.getUniqueMappedResult();
		
		if (null!= configItems) {
			dataConfigItems= configItems.getDataConfigItms();
		}
		return dataConfigItems;
	}
	
	public void deleteByConfigIdAndItemId(String configId,String itemId){
		Query query = new Query(Criteria.where("id").is(new ObjectId(configId)));
		Update update = new Update().pull("dataConfigItms", new Query(Criteria.where("itemId").is(itemId)));
		mongoTemplate.updateMulti(query, update, DataConfig.class);
	}
}
