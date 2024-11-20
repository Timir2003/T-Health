from fastapi import FastAPI, HTTPException, Request
from fastapi.responses import JSONResponse
from fastapi.security import HTTPBasicCredentials

app = FastAPI()

# Список пользователей и паролей
users = {
  "admin": "password",
  "user": "secret"
}

@app.post("/login")
async def login(credentials: HTTPBasicCredentials = None):

  if credentials is None:
    raise HTTPException(status_code=401, detail="Неправильный запрос")

  username = credentials.username
  password = credentials.password

  if username in users and users[username] == password:
    return JSONResponse({"message": "Успешная авторизация"})
  else:
    raise HTTPException(status_code=401, detail="Неверные логин или пароль")

@app.get("/protected")
async def protected_route(request: Request):

  credentials = await request.app.dependency_overrides.get(login)
  if credentials:
    return JSONResponse({"message": "Доступ к защищенному маршруту получен!"})
  else:
    raise HTTPException(status_code=401, detail="Необходимо пройти авторизацию")
