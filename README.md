# Canteen


вот здесь кмк максимально простой сайтик https://www.mealty.ru/catalog/

тестовые данные от оргов в формате .ods открыать через TextEdit.app - 
там ебола какая-то, надо попробовать перегнать хотя бы в txt

## API

- список всех блюд `/api/dishes`
- список всех столовых `/api/canteens`
- список блюд в столовой `/api/canteens/<id>/dishes`

никакой пагинации и hateoas нет :)


## TODO

- где сбор заказа будет? (подсчет цены и калорий) - фронт?ъ
- распиание работы столовой
- картинки блюд
- картинки стооловй
- монга 
- нафигачить тестовых данных
- задеплоить на AWS (машина, куда задеплоить есть)
- добавить поиск по блюду
- трэвис?
- ...
- админка (просто отправлять форму блюда с basicAuth?)


## Intellij

file -> open -> build.gradle

или

file -> new proj -> from existing sources


## VSCode / Webstorm

TBD


## Запуск бекенда

```bash
#из корня
./gradlew :backend:bootrun

curl http://localhost:8080/api/dishes
```