from pydantic import BaseModel

class AppBase(BaseModel):
    name: str
    description: str
    icon: str
    version: str
    downloads: str
    developer: str
    age: str
    tags: str
    screenshots: str

class AppCreate(AppBase):
    pass

class App(AppBase):
    id: int

    class Config:
        orm_mode = True
