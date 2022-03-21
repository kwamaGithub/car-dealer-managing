# car-dealer-managing

360 Agency.BACKEND TECH ASSIGNEMENT

Author : Kokou Wama DJIMISSA ( Lead java Back-end )/kokouwamadjimissa@gmail.com 

GitHub_username: kwamaGithub

Github repository : https://github.com/kwamaGithub/car-dealer-managing

Since : 21/03/2022

Requirements :  JDK 1.8+, PostgreSQL 12, 
Spring boot Framework(version: 2.6.4): [ Maven, Spring Data JPA, PostgreSQL Driver, Liquibase,Spring MVC,Junit + Mockito & MockMVC],

IDE : NetBeans && POSTMAN (for Testing)

For deployement: 
All configuration in *application.properties*
Port : 8095
Application context-path: /cardealer-starter


PostgreSQL default port : 5432
Database name: caradversiting
PostgreSQL user: 
username: postgres
password: postgres

Après le *clean and build* du projet récuperer( copier ) l'application sous format archive .jar 
dans ~/NetBeansProject/cardealer-core/target/cardearler-core-0.0.1-SNAPSHOT.jar 

= Faire un déploiement sur machine local ou sur votre serveur cloud ( Tomcat /Widfly) avec cette commande: 
~$ java -jar  cardearler-core-0.0.1-SNAPSHOT.jar

REST SERVICE : 
context & port after local deployement : http://localhost:8095/cardealer-starter

METHOD GET : /api/managing/getAllDealer
Description : get all dealers
Object Return example: {
    "dealers": [
        { "id": "HDAI","code": null,"label": "Hyundai","value": "4"},
        {"id": "HD","code": null,"label": "Honda","value": "10"} 
    	]
}


METHOD GET : /api/managing/getDealerListing/{dealerId}/{stateId}
Description: get all listings of a dealer with a given state
{dealerId}: dealer uid
{stateId}: State uid

METHOD POST : /api/managing/saveListing
Body (Model): { "id":null,"dealerId":null,
	"vehicle":null,"price":null,"stateCode":null,
	"stateLabel":null
      }
Content-type: application/json
Description: Create listing 
Object Return example:{
    "listingDTO": {
        "id": "81ed2f04-a360-4aa0-987e-00d95e541976",
        "dealerId": "HDAI",
        "vehicle": "Hyundai 01",
        "price": 23000,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
    }
}


METHOD PUT : /api/managing/updateListing
Body (Model): { "id":null,"dealerId":null,
	"vehicle":null,"price":null,"stateCode":null,
	"stateLabel":null
      }
Content-type: application/json
Description : Update  a listing if exist
Object Return example: {
    "listingEdited": true,
    "listingDTO": {
        "id": "c84e7c82-60fa-4a21-8de4-1d18ad7ee08e",
 	"dealerId": "HDAI",
        "vehicle": "Hyundai Versus",
        "price": 4000,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
    },
    "listingId": "c84e7c82-60fa-4a21-8de4-1d18ad7ee08e"
}

METHOD PUT : /api/managing/publishedOrUnpublish
Body (Model): { "id":null,"dealerId":null,
	"vehicle":null,"price":null,"stateCode":null,
	"stateLabel":null
      }
Description : Published ou unPublished a listing
Object Return example:
{
    "listingDTO": {
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "PBD",
        "stateLabel": "Published"
    }
}

+++++++++++++++++++++++++++++++++++++++++++++++++++++++POSTMAN TESTS+++++++++++++++++++++++++++++++++++++++++++++++++++++++
GET: Get All Dealer with thier tier limit of published listing (tier limit in field "value")
Content-Type: application/json
URL: http://localhost:8095/cardealer-starter/api/managing/getAllDealer
==Result===:
{
    "dealers": [
        {
            "id": "HDAI", //dealer id
            "code": null,
            "label": "Hyundai", // dealer name
            "value": "4" // dealer tier limit value
        },
        {
            "id": "HD",
            "code": null,
            "label": "Honda",
            "value": "10"
        },
        {
            "id": "GC",
            "code": null,
            "label": "GMC",
            "value": "0"
        },
        {
            "id": "II",
            "code": null,
            "label": "INFINITI",
            "value": "2"
        }
    ]
}


POST : LISTING CREATION 
Content-Type : application/json
URL: http://localhost:8095/cardealer-starter/api/managing/saveListing
** SEND TO CREATE - default State "Draft": DF
{
	"id":null,
	"dealerId":"HDAI",
	"vehicle":"Hyundai",
	"price":4000,
	"stateCode":"DF",
	"stateLabel":null
}
==Result after listing created===:
{
    "listingDTO": {
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai",
        "price": 4000,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
    }
}

PUT: Update  a listing if exist
Content-Type : application/json
URL: http://localhost:8095/cardealer-starter/api/managing/updateListing
** PUT : LISTING UPDATE  : SEND To Edited for change field "vehicle": "Hyundai" to "Hyundai Versus" and price : 4000 to 7800
{
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
}
==Result after listing updated===:
{
    "listingEdited": true,
    "listingDTO": {
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
    },
    "listingId": "045716a2-3570-4e57-aa7d-7fd35748c034"
}

PUT : Published ou unPublished a listing
URL: http://localhost:8095/cardealer-starter/api/managing/publishedOrUnpublish

** PUT TO PUBLISHED LISTING :
{
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
}

==Result after listing published===:
{
    "listingDTO": {
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "PBD",
        "stateLabel": "Published"
    }
}

==PUT Listing to Unpublished ===
{
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "PBD",
        "stateLabel": "Published"
}

==Result after listing unpublished===:
{
    "listingDTO": {
        "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
        "dealerId": "HDAI",
        "vehicle": "Hyundai versus",
        "price": 7800,
        "createdAt": "2022/03/21",
        "stateCode": "DF",
        "stateLabel": "Draft"
    }
}

GET: get all listings of a dealer ("HDAI") with a given state DF ("Draft")
For do this test we create 3 listings for dealer ("HDAI"):
Listing 1 : {"id":null,"dealerId":"HDAI","vehicle":"Hyundai 01","price":23000,"stateCode":"DF","stateLabel":null}
Listing 2 : {"id":null,"dealerId":"HDAI","vehicle":"Hyundai 02","price":53000,"stateCode":"DF","stateLabel":null}
Listing 3 : {"id":null,"dealerId":"HDAI","vehicle":"Hyundai 03","price":29000,"stateCode":"DF","stateLabel":null}

URL for get dealer listings: http://localhost:8095/cardealer-starter/api/managing/getDealerListing/HDAI/DF

==Result for get dealer listing with a given state===:
{
    "listings": [
        {
            "id": "045716a2-3570-4e57-aa7d-7fd35748c034",
            "dealerId": "HDAI",
            "vehicle": "Hyundai versus",
            "price": 7800,
            "createdAt": "2022/03/21",
            "stateCode": "DF",
            "stateLabel": "Draft"
        },
        {
            "id": "108e1b3a-7199-48b5-8ec2-84e9019b0cf8",
            "dealerId": "HDAI",
            "vehicle": "Hyundai 01",
            "price": 23000,
            "createdAt": "2022/03/21",
            "stateCode": "DF",
            "stateLabel": "Draft"
        },
        {
            "id": "3ded03ce-1668-4389-a147-9d291f7bf1ec",
            "dealerId": "HDAI",
            "vehicle": "Hyundai 02",
            "price": 53000,
            "createdAt": "2022/03/21",
            "stateCode": "DF",
            "stateLabel": "Draft"
        },
        {
            "id": "a04bc138-d05d-40f5-ac70-3a74e72bfd44",
            "dealerId": "HDAI",
            "vehicle": "Hyundai 03",
            "price": 29000,
            "createdAt": "2022/03/21",
            "stateCode": "DF",
            "stateLabel": "Draft"
        }
    ]
}
=========================================EXCEPTION HANDLER TESTS=========================================
POST : LISTING CREATION 
Content-Type : application/json
URL: http://localhost:8095/cardealer-starter/api/managing/saveListing

When creating listing, the fields are requiered: dealerId, price is requiered and >0
 
Test creating listing without dealerId:
{
            "id": null,
            "dealerId": null,
            "vehicle": "Hyundai 102",
            "price": 1500,
            "stateCode": "DF",
            "stateLabel": null
}

===Handler Response===: 
{
    "statusCode": 400,
    "reasonPhrase": "Dealer is requiered",
    "errors": []
}


Test creating listing without price or price equal 0:
{
            "id": null,
            "dealerId": "HDAI",
            "vehicle": "Hyundai 102",
            "price": null,
            "stateCode": "DF",
            "stateLabel": null
}

===Handler Response===:
{
    "statusCode": 400,
    "reasonPhrase": "The vehicle price is requiered",
    "errors": []
}

====================Another Exception Handler : When creating listing and dealer tier limit is rached.====================

"dealer": {
            "id": "II",
            "code": null,
            "label": "INFINITI",
            "value": "2"
	  }

the "INFINITI" dealer can published 2 listings. 
let's create 2 listings and publish them. Let's try to make another one:
Listing 1 : {"id": null,"dealerId": "II","vehicle": "INFINITI 01","price": 55000,"stateCode": "DF","stateLabel": null}
Listing 2 : {"id": null,"dealerId": "II","vehicle": "INFINITI 02","price": 6700,"stateCode": "DF","stateLabel": null}

Listing 1 created and published:  {
        "id": "a7b04689-f69f-43ce-a643-44d1ddcbfca2",
        "dealerId": "II",
        "vehicle": "INFINITI 01",
        "price": 55000,
        "createdAt": "2022/03/21",
        "stateCode": "PBD",
        "stateLabel": "Published"
    }
Listing 2 created and published: {
        "id": "8d5d6180-c334-4f21-89fb-6286c33a415c",
        "dealerId": "II",
        "vehicle": "INFINITI 01",
        "price": 55000,
        "createdAt": "2022/03/21",
        "stateCode": "PBD",
        "stateLabel": "Published"
    }

Let create 3 published: {"id": null,"dealerId": "II","vehicle": "INFINITI 03","price": 4400,"stateCode": "DF","stateLabel": null}

Handler Exception Response:
{
    "statusCode": 400,
    "reasonPhrase": "The tier limit to the dealer is rached. you have a choice to unpublish the oldest listing of a dealer",
    "errors": []
}
