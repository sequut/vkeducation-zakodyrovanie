from sqlalchemy import Column, Integer, String
from database import Base

class App(Base):
    __tablename__ = "apps"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    description = Column(String)
    icon = Column(String)
    version = Column(String)
    downloads = Column(String)
    developer = Column(String)
