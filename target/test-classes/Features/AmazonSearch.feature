Feature: Amazon Search and Wishlist Functionality

  Scenario: User searches for products and adds to wishlist
    Given the user launches the browser
    When the user searches for "amazon" on Google
    Then the user should see search results

    When the user clicks on the link to Amazon India
    When the user searches for "dell computers"
    When the user applies the price range filter Rs 30000 to 50000
    Then the user should see search results within the price range

    When the user validates products on the first 2 pages
    When the user adds the first product with 5 out of 5 rating to the wishlist
    Then the product should be added to the wishlist

    When the user closes the browser

  Scenario: User validates product addition to cart
    Given the user launches the browser
    When the user searches for "amazon" on Google
    Then the user should see search results

    When the user clicks on the link to Amazon India
    When the user searches for "dell computers"
    When the user applies the price range filter Rs 30000 to 50000
    Then the user should see search results within the price range

    When the user validates products on the first 2 pages
    When the user adds the first product with 5 out of 5 rating to the wishlist
    Then the product should be added to the wishlist

    When the user should see the "Added to Cart" message

    When the user closes the browser
