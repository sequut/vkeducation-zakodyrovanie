from pydantic import BaseModel

class AppBase(BaseModel):
    name: str
    description: str
    icon: str
    version: str
    downloads: str
    developer: str

class AppCreate(AppBase):
    pass

class App(AppBase):
    id: int

    class Config:
        orm_mode = True
