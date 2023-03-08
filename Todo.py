import itertools

class Todo:
    newid = itertools.count().next
    categories = []
    projects = []
    def ___init___(self, title, description, doneStatus):
        self.id = Todo.newid()
        self.title = title
        self.description = description
        self.doneStatus = doneStatus
    

    def __str__(self):
        return self.title + " " + self.description + " " + self.doneStatus
    
    def getTitle(self):
        return self.title
    
    def getDescription(self):
        return self.description
    
    def getDoneStatus(self):
        return self.doneStatus