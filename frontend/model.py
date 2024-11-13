import json
import random


class Product:
    def __init__(self, jsonData) -> None:
        self.id = jsonData["id"]
        self.name = jsonData["name"]
        self.price = jsonData["price"]
        self.quantity = jsonData["quantity"]
        self.sold=jsonData["sold"]
        self.factory = jsonData["factory"]
        self.image = jsonData["image"]
        self.short_desc = jsonData["shortDesc"]
        self.detail_desc = jsonData["detailDesc"]
        self.json = json.dumps(jsonData)

    def listProduct(data):
        lst = []
        for p in data:
            lst.append(Product(p))
        return lst

class Cart:
    def __init__(self, jsonData) -> None:
        self.id = jsonData["id"]
        self.quantity = jsonData["quantity"]
        self.product_id = jsonData["productId"]
        self.json = json.dumps(jsonData)
        
    def setProduct(self, p):
        self.product = p

    def listCart(data):
        lst = []
        for p in data:
            lst.append(Cart(p))
        return lst


