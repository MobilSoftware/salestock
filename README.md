# Sale Stock

This application was built for assessment purpose with case study as follows:
Product and Category management with hierarchical tree data for Category, enable filter based on product size, color and price range

## Entity Relationship Diagram

This ERD Diagram described what database structure that translated from requirements above:

![](https://raw.githubusercontent.com/irfanr/salestock/master/erd.png)
*ERD*

## Operation Guide

Cloud infrastructure is provided with address as follows:

http://188.166.235.127

- Go to cloud by typing the address into browser:
![](https://raw.githubusercontent.com/irfanr/salestock/master/1-home-web.png)

- Login to Cloud using user "admin" and password "admin":
![](https://raw.githubusercontent.com/irfanr/salestock/master/2-login-web.png)

- If success, it will be routed to welcome homepage:
![](https://raw.githubusercontent.com/irfanr/salestock/master/3-login-web-success.png)

- You can check list of API Docs by clicking "API Docs" menu in ADMIN section. Here is API Docs for Category:
![](https://raw.githubusercontent.com/irfanr/salestock/master/4-api-docs-category.png)

- And here is API Docs for Category:
![](https://raw.githubusercontent.com/irfanr/salestock/master/5-api-docs-product.png)

For testing purpose, let's use REST Client provided as addon by Mozilla Firefox (you can use similiar tools with Chrome or other standalone REST Client tools):

https://addons.mozilla.org/en-US/firefox/addon/restclient/

After successful install, make sure that you've logged as admin in cloud site since the REST client will use the cookies for REST resource calls.

It is important also to add Custom Header for HTTP Request related with the testing:
- Add X-CSRF-TOKEN, the value can be taken after you've access the cloud site. After the site is completely loaded, press F12 (I'm using Firefox with Firebug Addon), choose "Console" tab. Click one of the request and you'll see "x-csrf-token" with value on the right. Copy the value and added it as Custom Header value for X-CSRF-TOKEN.

![](https://raw.githubusercontent.com/irfanr/salestock/master/6-get-token.png)

- Add Content-Type with value of "application/json" to make sure that request and response will be handled as json type. 

![](https://raw.githubusercontent.com/irfanr/salestock/master/7-x-csrf-token-input.png)

### Category

- Category Listing and Search

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-list.png)

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-list-output.png)

- Category Create

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-create.png)

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-create-output.png)

- Category Detail

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-detail.png)

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-detail-output.png)

- Category Update

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-update.png)

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-update-output.png)

- Category Delete

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-delete.png)

![](https://raw.githubusercontent.com/irfanr/salestock/master/category-delete-output.png)

### Product

- Product Listing and Search
- Product Create
- Product Update
- Product Delete

