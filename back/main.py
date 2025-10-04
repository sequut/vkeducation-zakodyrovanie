from fastapi import FastAPI, Depends, Form, Request
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
from typing import List

import models, schemas
from database import engine, SessionLocal

models.Base.metadata.create_all(bind=engine)

app = FastAPI(title="App Store API", version="1.1")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

templates = Jinja2Templates(directory="templates")

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/apps", response_model=List[schemas.App])
def get_apps(db: Session = Depends(get_db)):
    return db.query(models.App).all()

@app.post("/apps", response_model=schemas.App)
def create_app(app: schemas.AppCreate, db: Session = Depends(get_db)):
    db_app = models.App(**app.dict())
    db.add(db_app)
    db.commit()
    db.refresh(db_app)
    return db_app

@app.get("/", response_class=HTMLResponse)
def home(request: Request):
    return templates.TemplateResponse("manage.html", {"request": request})

@app.get("/add", response_class=HTMLResponse)
def add_form(request: Request):
    return templates.TemplateResponse("add_app.html", {"request": request})

@app.post("/add")
def add_app(
    request: Request,
    name: str = Form(...),
    description: str = Form(...),
    icon: str = Form(...),
    version: str = Form(...),
    downloads: str = Form(...),
    developer: str = Form(...),
    db: Session = Depends(get_db)
):
    new_app = models.App(
        name=name,
        description=description,
        icon=icon,
        version=version,
        downloads=downloads,
        developer=developer
    )
    db.add(new_app)
    db.commit()
    db.refresh(new_app)
    return RedirectResponse(url="/", status_code=303)
