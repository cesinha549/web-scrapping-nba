package br.com.scrapper;

import java.util.ArrayList;

import com.jaunt.*;

public class ScrapperNbaTable {
	
	String url = "https://www.basketball-reference.com/players/j/jamesle01.html";
	String playerName;
	ArrayList<String> tableFields = new ArrayList<String>();
	ArrayList<String> tableContent = new ArrayList<String>();
	ArrayList<String> tableYears = new ArrayList<String>();
	//ArrayList<String> tableContentBroken = new ArrayList<String>();
	ArrayList<ArrayList<String> > aList =   new ArrayList<ArrayList<String> >();
	
	
	public ArrayList<String> scrapTableHeaders() throws NotFound {
		
		try{
			  UserAgent userAgent = new UserAgent();
			  userAgent.visit(url);   
			 
			  Elements content = userAgent.doc.findEach("<thead>").findEach("<th>");       //find non-nested tables   
			  Elements header = userAgent.doc.findEach("<tbody>").findEach("<tr>");       //find non-nested tables   
			  Elements name = userAgent.doc.findEach("<h1>").findEach("<span>"); 
			  
			 System.out.println("Found " + header.size() + " tables:");
			 

			 for(Element numbers : content){                               //iterate through search results
				    System.out.print(numbers.getTextContent()+ " ;");      //print each element and its contents
				    tableFields.add(numbers.getTextContent());
				    
			 }     
			 
			 
			 for(Element table : header){                               //iterate through search results
			    System.out.println("\n"+table.getTextContent() +"\n----\n");      //print each element and its contents
			    Elements cells = table.findEvery("<td>");
			    for(Element cell: cells) {			    	
			    	//System.out.println(cell.getTextContent());
			    	//tableContent.add(cell.getTextContent());
			    }
			 }
			 
			 for(Element names : name){                
				 	setPlayerName(names.getTextContent());
				   // System.out.println("========>"+names.getTextContent() +"\n----\n");      //print each element and its contents
				 }  
			 
			 //System.out.println(tableFields);
			 
			  
			}
			catch(ResponseException e){
			  System.out.println(e);
			}
		
		
		return tableFields;

		
	}
	
public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

public ArrayList<ArrayList<String> > scrapTableBody() throws NotFound {
	int count=0;
	int countAntes=0;
		
		try{
			  
			  UserAgent userAgent = new UserAgent();
			  userAgent.visit(url);   
			  Elements years = userAgent.doc.findEach("<tbody>").findEach("<th>").findEach("<a>"); 
			  Elements content = userAgent.doc.findEach("<thead>").findEach("<th>");       //find non-nested tables   
			  Elements header = userAgent.doc.findEach("<tbody>").findEach("<tr>");       //find non-nested tables   
			  Elements name = userAgent.doc.findEach("<h1>").findEach("<span>"); 
			  
			 System.out.println("Found " + header.size() + " tables:");
			 

			 for(Element numbers : years){                               //iterate through search results
			      //print each element and its contents
				    tableYears.add(numbers.getTextContent()+"year");
				
			 }  
			 
			 //System.out.println(tableYears);
			 
			 
			 for(Element table : header){                               //iterate through search results
			    //System.out.println("\n"+table.getTextContent() +"\n----\n");      //print each element and its contents
			    Elements cells = table.findEach("<td>");
			    for(Element cell: cells) {			    	
			    	tableContent.add(cell.getTextContent());
			    }
			    
			   
			 }
			 
			 for (Element year: years) {
				 countAntes = count;
				 count = count+29;
				 ArrayList<String> tableContentBroken = new ArrayList<String>();
				 tableContentBroken.add(year.getTextContent());
				 for(int i=countAntes;i<count; i++) {
				    	tableContentBroken.add(tableContent.get(i));
				    	//System.out.println(tableContent.get(i));
				    }
				System.out.println(tableContentBroken);
				 aList.add(tableContentBroken);
			 }
			 
			// System.out.println(tableContentBroken);
			 for(Element names : name){                               //iterate through search results
				    //System.out.println("========>"+names.getTextContent() +"\n----\n");      //print each element and its contents
				 }  
			 
			
			 
			  
			}
			catch(ResponseException e){
			  System.out.println(e);
			}
		
		
		return aList;

		
	}
}