import os
from fastapi import FastAPI, Depends, Form, Request, UploadFile, File
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
from fastapi.staticfiles import StaticFiles
from sqlalchemy.orm import Session
from typing import List
import shutil

import models, schemas
from database import engine, SessionLocal

models.Base.metadata.create_all(bind=engine)

app = FastAPI(title="App Store API", version="1.2")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

templates = Jinja2Templates(directory="templates")
app.mount("/static", StaticFiles(directory="static"), name="static")

UPLOAD_DIR = "static/uploads"
os.makedirs(UPLOAD_DIR, exist_ok=True)

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
def home(request: Request, db: Session = Depends(get_db)):
    apps = db.query(models.App).all()
    return templates.TemplateResponse("manage.html", {"request": request, "apps": apps})

@app.get("/add", response_class=HTMLResponse)
def add_form(request: Request):
    return templates.TemplateResponse("add_app.html", {"request": request})

@app.post("/add")
async def add_app(
    request: Request,
    name: str = Form(...),
    description: str = Form(...),
    icon: str = Form(...),
    version: str = Form(...),
    downloads: str = Form(...),
    developer: str = Form(...),
    screenshots: List[UploadFile] = File(None),
    db: Session = Depends(get_db)
):
    saved_files = []
    if screenshots:
        for file in screenshots:
            file_path = os.path.join(UPLOAD_DIR, file.filename)
            with open(file_path, "wb") as buffer:
                shutil.copyfileobj(file.file, buffer)
            saved_files.append(f"/static/uploads/{file.filename}")

    new_app = models.App(
        name=name,
        description=description,
        icon=icon,
        version=version,
        downloads=downloads,
        developer=developer,
        screenshots=",".join(saved_files) if saved_files else None
    )
    db.add(new_app)
    db.commit()
    db.refresh(new_app)
    return RedirectResponse(url="/", status_code=303)
