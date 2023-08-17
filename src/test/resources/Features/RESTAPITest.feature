Feature: Create Booking

  Scenario: User creates a booking
    Given the user wants to create a booking
    When the user sends a POST request to create a booking
    Then the user should receive a response with status code 200 for create booking

   Scenario: User gets a booking
    Given the user wants to get a booking
    When the user sends a GET request to get a booking with ID 1
    Then the user should receive a response with status code 200 for Get Request
    
    Scenario: Update a booking
    Given the user wants to update a booking
    When the user sends a PUT request to update a booking with ID 1
    Then the user should receive a response with status code 403 for update booking
    
    Scenario: User deletes a booking
    Given the user wants to delete a booking with valid authentication
    When the user sends a DELETE request to delete the booking
    Then the user should receive a response with status code 403