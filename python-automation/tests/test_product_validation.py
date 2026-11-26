
def test_product_name():
    product_name = "Laptop"
    assert product_name != ""
    print("Product Name Validation PASSED")


def test_product_price():
    price = 55000
    assert price > 0
    print("Product Price Validation PASSED")


def test_product_stock():
    stock = 10
    assert stock >= 0
    print("Product Stock Validation PASSED")


def test_invalid_product_price():
    price = -500
    assert not (price > 0)
    print("Invalid Price Validation PASSED")


def test_out_of_stock():
    stock = 0
    assert stock == 0
    print("Out of Stock Validation PASSED")
