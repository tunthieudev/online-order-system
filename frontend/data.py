import requests

serverAddr = ""  # "http://localhost:8080"


def login(username, password):
    if username.lower() == "admin" and password == "1":
        return ("Nguyen Van A", 1)
    if username.lower() == "admin" and password == "0":
        return ("Xxx Yyy Zzz", 0)


def get(addr):
    try:
        return requests.get(serverAddr + addr).json()
    except Exception:
        return


def post(addr, obj=None):
    return requests.post(url=serverAddr + addr, json=obj)
