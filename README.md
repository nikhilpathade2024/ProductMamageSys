API Endpoints Documentation For Product Management System
Here are the API endpoints that the users can use, including the expected request and response formats.
1. Get All Products
•	Method: GET
•	url : http://localhost:9092/products
•	Description: Retrieves a list of all products.
•	Response:
o	Status Code: 200 OK
o	Body: A list of product objects.
Example Response:
[
    {
        "id": 1,
        "name": "Sample Product",
        "description": "Sample Description",
        "price": 100.0,
        "quantity": 10
    },
    {
        "id": 2,
        "name": "Another Product",
        "description": "Another Description",
        "price": 150.0,
        "quantity": 20
    }
]
2. Get Product By ID
•	Method: GET
•	url : http://localhost:9092/products/{id}
•	Description: Retrieves a product by its ID.
•	Parameters:
o	id (Path Parameter) – The ID of the product to fetch.
•	Response:
o	Status Code: 200 OK
o	Body: A single product object.
Example Response:
{
    "id": 1,
    "name": "Sample Product",
    "description": "Sample Description",
    "price": 100.0,
    "quantity": 10
}
3. Create Product
•	Method: POST
•	url : http://localhost:9092/products
•	Description: Creates a new product.
•	Request Body:
{
    "name": "New Product",
    "description": "New Product Description",
    "price": 120.0,
    "quantity": 15
}
•	Response:
o	Status Code: 200 OK
o	Body: The created product object.
Example Response:
{
    "id": 3,
    "name": "New Product",
    "description": "New Product Description",
    "price": 120.0,
    "quantity": 15
}
4. Update Product
•	Method: PUT
•	url : http://localhost:9092/products/{id}
•	Description: Updates an existing product.
•	Parameters:
o	id (Path Parameter) – The ID of the product to update.
•	Request Body:
{
    "name": "Updated Product",
    "description": "Updated Description",
    "price": 150.0,
    "quantity": 20
}
•	Response:
o	Status Code: 200 OK
o	Body: The updated product object.
Example Response:
{
    "id": 1,
    "name": "Updated Product",
    "description": "Updated Description",
    "price": 150.0,
    "quantity": 20
}
5. Delete Product
•	Method: DELETE
•	url : http://localhost:9092/products/{id}
•	Description: Deletes a product by its ID.
•	Parameters:
o	id (Path Parameter) – The ID of the product to delete.
•	Response:
o	Status Code: 204 No Content
o	Body: Empty body.
________________________________________
3. Authentication and Security
For basic authentication, the ProductController is secured with Spring Security. This allows users to authenticate using Basic Authentication.
Authentication Method:
•	Use the following credentials for authentication:
o	Username: nikhil
o	Password: Nikhil@123
Test Authentication in Swagger UI:
1.	Click on the "Authorize" button in Swagger UI.
2.	Enter the credentials:
o	Username: nikhil
o	Password: Nikhil@123

3.	You will now be authorized to access the endpoints that require authentication.
________________________________________

