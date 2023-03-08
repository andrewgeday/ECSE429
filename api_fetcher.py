import requests


def get_data(url):
    return requests.get(url).json()


def post_data(url, data):
    return requests.post(url, data=data).json()


def delete_data(url):
    return requests.delete(url).json()
