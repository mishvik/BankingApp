package com.ibm.enterprise.rest.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ibm.enterprise.beans.BankAccount;
import com.ibm.enterprise.common.ApplicationProperties;
import com.ibm.enterprise.db.mongo.MongoDBSingleton;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Path("/v1")
public class Account {

	@GET
	@Path("/account")
	public Response responseMsg(@Context UriInfo uriInfo) {

		String parameter1 = uriInfo.getQueryParameters().getFirst(
				ApplicationProperties.getInstance().getParameter1());
		String parameter2 = uriInfo.getQueryParameters().getFirst(
				ApplicationProperties.getInstance().getParameter2());

		if (null == parameter1 || null == parameter2 || parameter1.isEmpty()
				|| parameter2.isEmpty()) {
			String output = "Invalid parameter or Account Number!! Please input correct parameter name and value.";
			return Response.status(400).entity(output).build();
		}
		else {
			DBCursor cursor = null;
			try {
				MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
				DB db = dbSingleton.getTestdb();
				DBCollection coll = db.getCollection(ApplicationProperties
						.getInstance().getMongodbCollection());
				cursor = coll.find(new BasicDBObject(ApplicationProperties
						.getInstance().getParameter1(), parameter1));
			} catch (Exception ex) {
				String output = "Database Error!! Please check the Database is up or connection information is correct";
				return Response.status(500).entity(output).build();
			}
			if (null == cursor || false == cursor.hasNext()) {
				String output = "No Record Found!!! Please check Database or Collection or Account Number is valid.";
				return Response.status(400).entity(output).build();
			}
			BankAccount account = new BankAccount();
			while (cursor.hasNext()) {
				DBObject o = cursor.next();
				account.setAccountNo((String) o.get(ApplicationProperties
						.getInstance().getParameter1()));
				account.setBalance((String) o.get(parameter2));
			}
			String output = "Dear Customer, The Account Balance for your " + ApplicationProperties
					.getInstance().getParameter1() + ":"
					+ account.getAccountNo() + " is: USD "
					+ account.getBalance();
			return Response.status(200).entity(output).build();
		}
	}
}
