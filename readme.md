# Репозиторий для кейса ВК 
## О командe:
Название команды: *Закодированные*
Состав команды:
- Овчаров Владимир (заполни роль)
- Орлов Александр (заполни роль)
- Пилюк Дарья (заполни роль)
- Костин Андрей (разработчик)

## О репозитории:
### Запуск
для запуска необходимо скачать репозиторий к себе при помощи *git*:

```bash
git clone git@github.com:sequut/vkeducation-zakodyrovanie.git
cd vkeducation-zakodyrovanie
```

### Запуск API
создание виртуального окружения и установка зависимостей:
```bash
python -m venv venv
source venv/bin/activate

pip install -r requirements.txt
```

запуск API для наполнения базы данных, а также для подгрузки приложений:
```bash
uvicorn main:app --reload --port 8001
```

наполнение бд происходит по адресу (http://127.0.0.1:8001/add), там необходимо будет заполнить форму

### Добавить!!

также проведено [исследование](вставить url)

[макеты](https://www.figma.com/design/al0R4FfZw5vmKjJEeKnJwM/Case-Study?node-id=9-439&t=0IDLOCbTDDNdxsV4-1)