package com.mongodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.config.SimpleMongoConfig;
import com.mongodb.domains.DataConfig;
import com.mongodb.domains.DataConfigItem;
import com.mongodb.repository.DataConfigMongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SimpleMongoConfig.class})
@EnableMongoRepositories(basePackages = "com.mongodb.repository")
public class DataConfigMongoRepositoryTest {
	
	    @Autowired
	    private DataConfigMongoRepository dataConfigMongoRepository;
	    
	    
	   // @Test
	    public void whenInsertingUser_thenUserIsInserted() {
	    	for(int i =0 ; i< 10; i++){
	    		 DataConfig dataConfig = new DataConfig();
	    		 dataConfig.setMajor("17.0"+ i);
	    		 List<DataConfigItem> dataConfigItems = new ArrayList<DataConfigItem>();
	    		 for(int j=0 ; j<4; j++){
	    			 DataConfigItem configItem = new DataConfigItem();
		    		 configItem.setItemId(String.valueOf(j));
		    		 configItem.setMinor(i+1);
		    		 dataConfigItems.add(configItem);
	    		 }
	    		 //dataConfig.setDataConfigItms(dataConfigItems);
	 	         dataConfigMongoRepository.insert(dataConfig);
	    	}
	    }
	    
	    @Test
	    public void findDataConfigById(){
	    	DataConfig config= this.dataConfigMongoRepository.findById("5b733149118e7e03aa6c8faf");
	    	System.out.println(config);
	    }
	    
	    @Test
	    public void getDataConfigByConfigId(){
	    	List<DataConfigItem> config= this.dataConfigMongoRepository.getDataConfigByConfigId("5b733149118e7e03aa6c8fa8");
	    	for (DataConfigItem dataConfigItem : config) {
				System.out.println(dataConfigItem);
			}
	    }
	    
	    @Test
	    public void getDataConfigByConfigIdAndMinor(){
	    	List<DataConfigItem> config= this.dataConfigMongoRepository.getDataConfigByConfigIdAndMinor("5b733149118e7e03aa6c8fa8", 1);
	    	System.out.println(config);
	    }
	    
	    @Test
	    public void getDataConfigByConfigIdAndItemId(){
	    	List<DataConfigItem> config= this.dataConfigMongoRepository.getDataConfigByConfigIdAndItemId("5b733149118e7e03aa6c8fa8", "0");
	    	System.out.println(config);
	    }
	    
	    @Test
	    public void deleteById(){
	    	this.dataConfigMongoRepository.delete("5b733149118e7e03aa6c8fb0");
	    }
	    
	    @Test
	    public  void deleteByConfigIdAndItemId(){
	    	this.dataConfigMongoRepository.deleteByConfigIdAndItemId("5b733149118e7e03aa6c8fae", "2");
	    }
}
