package com.navneetreddy.ahashivavishnutemple;


/**
 * Builds a query URL to query the MongoDB.
 *
 * @author Navneet Reddy
 */
public class MongoQueryBuilder {

    /**
     * Constructor for the query builder to query MongoDB.
     */
    public MongoQueryBuilder() {}

    /**
     * Gets the Mongo Database Name.
     *
     * @return Name of the MongoDB
     */
    public String getDatabaseName() {
        return "aha_app_db";
    }

    /**
     * Gets the MongoLab API Key.
     *
     * @return MongoLab API Key
     */
    public String getApiKey() {
        return "0BGGmoyesiKIknfuQ6t8YM2iwkayxkCF";
    }

    /**
     * Constructs the URL to manage the Mongo database, collections, and documents.
     *
     * @return URL to get the collections from
     */
    public String getBaseUrl() {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Formats the MongoLab API Key for a URL.
     *
     * @return Formatted MongoLab API Key
     */
    public String docApiKeyUrl() {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the name of the requested collection.
     *
     * @return Name of the collection to get from Mongo
     */
    public String documentRequest() {
        return "Events";
    }

    /**.
     * Builds a complete URL to query MongoDB.
     *
     * @return URL to get the query from MongoDB
     */
    public String buildQueryURL() {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }
}