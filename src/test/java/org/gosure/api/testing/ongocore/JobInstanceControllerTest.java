package org.gosure.api.testing.ongocore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class JobInstanceControllerTest {

    final static String baseUrl ="http://localhost:8080";

    final static  String token= "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJvbmdvLWp3dCIsInN1YiI6ImFkbWluQHB1bmFybmF2YS5jb20iLCJhdXRob3JpdGllcyI6W10sInRlbmFudCI6InB1bmFybmF2YSIsIm9yZ0lkIjoiNjU4ODAyYTY5NTgxMjgwNThiNTc5NTJjIiwicm9sZU5hbWUiOiJTdWIgQWRtaW4iLCJ1c2VySWQiOiI2NTg4MDJhNzk1ODEyODA1OGI1Nzk1MmYiLCJpYXQiOjE3MDkxMzU2MDIsImV4cCI6MTcwOTc0MDQwMn0.HRzuAsPSnrhsSDqHwVLARXbCI1wpuQmsuP81Mq8fCK6JM-1jrYd2wt0_STDYXzZlb8sSzwE06FkgpdhMQtMo7A";
    
    @Test
    public void getAllJobInstancesTest(){
        Response response =given().log().all().when().header("Authorization","Bearer "+token,"Content-type", "application/json","Accept","application/json")
                .get(baseUrl+"/api/v1/job-instances")
                .then().log().all().extract().response();
        System.out.println(response.getBody().print());
        Assert.assertEquals(200,response.getStatusCode());

    }

    @Test
    public void retrieveJobInstanceUsingIdTest(){
        Response response =given().log().all()
                .when().headers("Authorization","Bearer "+token,"Content-type", ContentType.JSON,"Accept",ContentType.JSON)
                .get(baseUrl+"/api/v1/job-instances/"+"658802ab958128058b57953a")
                .then().log().all().assertThat().statusCode(200).extract().response();
        Assert.assertEquals(200,response.getStatusCode());
    }


    @Test
    public void createJobInstancesTest(){
        Response response = given().log().all()
                .when().body(getJobInstanceRequest(UUID.randomUUID().toString().replace("-",""))).headers("Authorization","Bearer "+token,"Content-type", ContentType.JSON,"Accept",ContentType.JSON)
                .post(baseUrl+"/api/v1/job-instances")
                .then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void updateJobInstancesTest(){
        Response response = given().body(getJobInstanceRequest("6575dd2094306c19d6c21860"))
                .when().headers("Authorization","Bearer "+token,"Content-type", ContentType.JSON,"Accept",ContentType.JSON)
                .put(baseUrl+"/api/v1/job-instances")
                .then().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void deleteJobInstancesTest(){
        //CreateJobInstance
        Response response = given().log().all()
                .when().body(getJobInstanceRequest("6575dd2094306c19d6c21100")).headers("Authorization","Bearer "+token,"Content-type", ContentType.JSON,"Accept",ContentType.JSON)
                .post(baseUrl+"/api/v1/job-instances")
                .then().log().all().extract().response();
        Assert. assertEquals(response.getStatusCode(),200);

        Response deleteResponse = given().log().all()
                .when().headers("Authorization","Bearer "+token,"Content-type", ContentType.JSON,"Accept",ContentType.JSON)
                .delete(baseUrl+"/api/v1/job-instances/"+"6575dd2094306c19d6c21100")
                .then().log().all()
                .extract().response();
        Assert. assertEquals(deleteResponse.getStatusCode(),200);
    }


    private String getJobInstanceRequest(String jobInstanceId) {
        return "{\n" +
                "    \"id\": \"" + jobInstanceId + "\",\n" +
                "    \"ItemCode\": \"e7b1fe18-81a6-4a83-8b67-1c412ffa711a\",\n" +
                "    \"dateCreated\": \"10/12/23, 9:15 pm\",\n" +
                "    \"lastUpdated\": \"10/12/23, 9:15 pm\",\n" +
                "    \"createdById\": null,\n" +
                "    \"jobTypeId\": \"658802a9958128058b579532\",\n" +
                "    \"jobTypeName\": \"emails\",\n" +
                "    \"createdByFullName\": \"657449dbbaf4ec44036cc040\",\n" +
                "    \"publicURL\": \"https://admin.insuranceportfolio.in/app/180/Modules;ModuleConstants;32727;_;SingleProduct\",\n" +
                "    \"Category_Mall\": \"Insurance\",\n" +
                "    \"PackageName\": null,\n" +
                "    \"receipt\": \"https://admin.insuranceportfolio.in/RenderCsvFile/receipt?id=32727\",\n" +
                "    \"certificate\": \"https://admin.insuranceportfolio.in/RenderCsvFile/renewed?id=32727\",\n" +
                "    \"masterPolicy\": \"https://admin.insuranceportfolio.in/RenderCsvFile/receipt?id=32727\",\n" +
                "    \"renewalNotice\": \"https://admin.insuranceportfolio.in/RenderCsvFile/generateRenewalNotice?id=32727\",\n" +
                "    \"Additional_Details\": null,\n" +
                "    \"Current_Job_Status\": \"Active\",\n" +
                "    \"Current_Job_StatusId\": null,\n" +
                "    \"Next_Seq_Nos\": \"2\",\n" +
                "    \"Insights\": \"\",\n" +
                "    \"overallRating\": \"0.0\",\n" +
                "    \"totalReviews\": \"0\",\n" +
                "    \"malldetails\": {\n" +
                "        \"id\": \"657449dabaf4ec44036cc03d\",\n" +
                "        \"fullname\": \"Punarnava Insurance Broking Pvt Ltd\",\n" +
                "        \"email\": \"punarnavaadmin_sog@gosure.ai\",\n" +
                "        \"website\": \"\",\n" +
                "        \"subdomain\": \"yolv_180\"\n" +
                "    },\n" +
                "    \"favouritesCount\": 0,\n" +
                "    \"hrsOfOperation\": [],\n" +
                "    \"Attachments\": [],\n" +
                "    \"jobComments\": [],\n" +
                "    \"CreatedSubJobs\": [],\n" +
                "    \"Next_Job_Statuses\": [\n" +
                "        {\n" +
                "            \"sequenceNo\": 2,\n" +
                "            \"primaryStatus\": null,\n" +
                "            \"secondaryStatus\": \"Inactive\",\n" +
                "            \"roles\": null,\n" +
                "            \"subJobType\": null\n" +
                "        }\n" +
                "    ],\n" +
                "    \"favourites\": [],\n" +
                "    \"data\": {\n" +
                "        \"msgBody\": \"Claim process is initiated for the policy no ${data.Lead ID} for company name: ${data.Company Name} by ${LoginDetail.email}\",\n" +
                "        \"subject\": \"Claim Process initiated with claim no ${data.Lead ID}\",\n" +
                "        \"recipient\": \"sgokul55@gmail.com\"\n" +
                "    }\n" +
                "}";
    }

}
