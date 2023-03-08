import itertools


class Categories:
    newid = itertools.count().next

    def __init__(self, title, descrpition):
        self.id = Categories.newid()
        self.title = title
        self.description = descrpition

    def __str__(self):
        return self.title + " " + self.description

    def getTitle(self):
        return self.title

    def getDescription(self):
        return self.description
