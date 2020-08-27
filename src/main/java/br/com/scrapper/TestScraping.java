package br.com.scrapper;

import br.com.connectoDynamo.AmazonDynamoDBSample;

public class TestScraping {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		AmazonDynamoDBSample amazonDb = new AmazonDynamoDBSample();
		ScrapperNbaTable dbAtt = new ScrapperNbaTable();
		amazonDb.dynamoConfig(dbAtt.scrapTableHeaders(), dbAtt.scrapTableBody(), dbAtt.getPlayerName());
	}

}
