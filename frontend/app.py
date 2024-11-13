from functools import wraps
import time
import json
from flask import (
    Flask,
    render_template,
    request,
    redirect,
    session,
    flash,
)
import data
import model

app = Flask(__name__)
app.secret_key = "SOA"

# all


@app.errorhandler(404)
def notFound(e):
    return render_template("404.html")


@app.route("/")
def home():
    if session.__contains__("role"):
        if session["role"] == 1:
            return redirect("/cart")
    return render_template("base.html")


@app.route("/login", methods=["GET", "POST"])
def login():
    session.clear()
    msg = ""
    if request.method == "POST":
        username = request.form["username"]
        password = request.form["password"]
        user = data.login(username, password)
        if user:
            session["name"] = user[0]
            session["role"] = user[1]
            return redirect("/")
        else:
            flash("Đăng nhập thất bại!", "warning")
            msg = "Tài khoản hoặc mật khẩu không chính xác"
    return render_template("login.html", msg=msg)


@app.route("/logout")
def logout():
    session.clear()
    return redirect("/")


# cart


@app.route("/cart")
def cart():
    cartLst = model.Cart.listCart(data.get("http://localhost:8080/api/cart/get"))
    for cartItem in cartLst:
        productData = data.get("http://localhost:8080/api/product/" + str(cartItem.product_id))
        cartItem.setProduct(model.Product(productData))
    return render_template("/cart.html", cartLst=cartLst)

# order


@app.route("/take-order", methods=["GET", "POST"])
def orders():
    if request.method == "POST":
        dataJson = json.loads(request.form["json"])
        data.post("http://localhost:8080/api/take-order", dataJson)
        flash("Đặt hàng thành công", "success")
    return redirect("/")

# main

if __name__ == "__main__":
    app.run(debug=True)
