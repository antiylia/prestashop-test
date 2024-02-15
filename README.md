# Selenide + JUnit5 + Allure + javafaker

## To run tests:

* run `mvn clean test`
* run `mvn allure:serve` - to generate and open the report

## What was done:

1. implemented e2e tests, to cover requirements:
    - User able to add to Cart more than 1 product [userCanAddMoreThatOneProductToCart](https://github.com/antiylia/prestashop-test/blob/39e92ee62525ab13c913eb793fdf7cdd8bcd8c04/src/test/java/org/prestashop/AddingAndCheckoutMoreThatOneProductTest.java#L23)
    - User able to checkout order from the Cart [userCanProceedToCheckoutOrder](https://github.com/antiylia/prestashop-test/blob/antiylia/prestashop-tests/src/test/java/org/prestashop/AddingAndCheckoutMoreThatOneProductTest.java#L42)
   
2. `Selenide + JUnit5 + Allure + javafaker` used tech stack

3. Currently, within `userCanProceedToCheckoutOrder` tests products added to Cart via UI (as precondition), 
it weak place and should be improved using API. It can be done using:
```
curl -X POST 'https://famous-foot.demo.prestashop.com/en/cart' \
  -H 'authority: famous-foot.demo.prestashop.com' \
  -H 'cookie: ajs_anonymous_id=c40f676d-4096-48e2-8e36-0a6551a8da4f; axeptio_cookies={%ue...._ga=9975be4d6' \
  -H 'origin: https://famous-foot.demo.prestashop.com' \
 ....
  --data-raw 'token=d7e7cc81ffddba8e19ef2b668074a64e&id_product=2&id_customization=0&group%5B1%5D=1&qty=1&add=1&action=update' \
  --compressed

```

The tricky things here:

- exact shop `famous-foot.demo.prestashop.com` domain is dynamic, and changing after some time, need to investigate how it's sourced

- added products are displayed in Cart within one browser session, so to be able to do this in precondition through code 
it's required to pass `ajs_anonymous_id=c40f676d-4096-48e2-8e36-0a6551a8da4f` within `Cookie` header, so also need to know how `ajs_anonymous_id` is sourced.
Alternative way is to execute test script under logged User

- request sends Form data:
```
token: d7e7cc81ffddba8e19ef2b668074a64e
id_product: 2
id_customization: 0
group[1]: 1
qty: 1
add: 1
action: update
```
need to know, what is source for `token: d7e7cc81ffddba8e19ef2b668074a64e`, and is there any API for shop' products 
to get following information `id_product, id_customization, group[1]` and etc

4. Test data is hardcoded [products](https://github.com/antiylia/prestashop-test/blob/39e92ee62525ab13c913eb793fdf7cdd8bcd8c04/src/test/java/org/prestashop/AddingAndCheckoutMoreThatOneProductTest.java#L18).
Need to clarify, are there any product' API or other ways to get actual products list dynamically

5. [placeOrderBtn.click();](https://github.com/antiylia/prestashop-test/blob/39e92ee62525ab13c913eb793fdf7cdd8bcd8c04/src/main/java/org/prestashop/pages/checkout/steps/PaymentStep.java#L16) disabled for now and test [validates](https://github.com/antiylia/prestashop-test/blob/39e92ee62525ab13c913eb793fdf7cdd8bcd8c04/src/test/java/org/prestashop/AddingAndCheckoutMoreThatOneProductTest.java#L53), 
that "PLACE ORDER" button is enabled after checkout steps done. 
Need to check how generated Order can be clean up after test, to proceed with the next steps: to place Order and check "YOUR ORDER IS CONFIRMED" component appeared





