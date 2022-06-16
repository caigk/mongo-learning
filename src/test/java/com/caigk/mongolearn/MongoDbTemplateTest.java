package com.caigk.mongolearn;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import lombok.extern.slf4j.Slf4j;

//@SpringBootTest
@DataMongoTest
@Slf4j
public class MongoDbTemplateTest {

	@Autowired
	MongoTemplate template;

	/**
	 * @see https://www.mongodb.com/docs/manual/reference/command/find/#mongodb-dbcommand-dbcmd.find
	 */
	@Test
	public void jsonCommantTest() {

		String jsonCommand = """
				{
					"find":"base_crane_data"
				}
				""";

		//对应db.runcommand()
		Document result = template.executeCommand(jsonCommand);
		System.out.println(result);
	}


	/**
	 * @see https://www.mongodb.com/docs/manual/reference/command/aggregate/
	 */
	@Test
	public void aggregateJsonCommantTest() {

		String jsonCommand = """
				{
					"aggregate":"base_crane_data",
					"pipeline":[
						{"$unwind":"$craneXModal"},
							{
								"$project": {
									"ageKey": {
										"$switch": {
											"branches": [
												{
													"case": {
														"$lt": ["$craneXModal.xSpeed", 0]
													},
													"then": "0-20"
												},
												{
													"case": {
														"$and": [{
															"$gte": ["$craneXModal.xSpeed", 2]
														}, {
															"$lt": ["$craneXModal.xSpeed", 300]
														}]
													},
													"then": "20-30"
												}
											],
											"default": "30以上"
										}
									}
								}
							},
							{
								"$group": {
									"_id": "$ageKey",
									"count": {
										"$sum": 1
									}
								}
							}
						],
					"cursor":{}
				}
				""";

		//对应db.runcommand()
		Document result = template.executeCommand(jsonCommand);
		System.out.println(result.toJson());
	}


	@Test
	public void streamCommantTest()
	{
		// Query q = new Query(Criteria.where("craneId").lt(0));
		//  = template.stream(q,Document);
		// System.out.println(result);
	}
}
