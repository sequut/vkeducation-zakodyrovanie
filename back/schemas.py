from pydantic import BaseModel
from typing import Optional

class AppBase(BaseModel):
    name: str
    description: str
    icon: str
    version: str
    downloads: str
    developer: str
    screenshots: Optional[str] = None

class AppCreate(AppBase):
    pass

class App(AppBase):
    id: int

    class Config:
        orm_mode = True
