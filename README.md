# Тестовое задание

### API пользователя:
#### Регистрация новго пользователя - /registration - POST
##### Доступ - неавторизованные и авторизованные пользователи

##### Тело запроса
#
```sh
{
	"username": "viktorvezunchik",
	"email": "vasyu_kolbasit@mail.ru"
}
```
##### Ответ
#
```sh
{
    "secret_key": "bbd07c4fc02c99b97124febf42c7b63b5011c0df28d409fbb486b5a9d2e615ea"
}
```
#### Просмотр баланса - /wallet/balance - GET
##### Доступ - user, admin
##### Тело запроса
#
```sh
{
	"secret_key": "AAFeyWzOnlD-9G4i662PdKn2B-b4BwrCNA"
}
```
##### Ответ
#
```sh
{
    "rub_wallet": 8000.0,
    "ton_wallet": 11.869565217391305,
    "btc_wallet": 1.0
}
```
#### Пополнение баланса - /operation/replenish - POST
##### Доступ - user, admin
##### Тело запроса
#
```sh
{
	"secret_key": "AAFeyWzOnlD-9G4i662PdKn2B-b4BwrCNA",
	"RUB_wallet": "1000"
}
```
##### Ответ
#
```sh
{
    "rub_wallet": 9000.0
}
```
#### Вывод баланса - /operation/withdrawal - POST
##### Доступ - user, admin
##### Тело запроса
#
```sh
{
	"secret_key": "AAFeyWzOnlD-9G4i662PdKn2B-b4BwrCNA",
	"currency": "RUB",
	"count": "1500",
	"credit_card": "1234 5678 9012 3456"
}
```
##### Ответ
#
```sh
{
    "rub_wallet": 9000.0
}
```
#### Посмотреть курс валют - /operation/exchangeRate - GET
##### Доступ - user, admin
##### Тело запроса
#
```sh
{
	"secret_key": "AAFeyWzOnlD-9G4i662PdKn2B-b4BwrCNA",
	"currency": "TON"
}

```
##### Ответ
#
```sh
{
    "rub_wallet": 184.0,
    "btc_wallet": 9.6E-5
}
```
#### Обмен валют - /operation/exchange - POST
##### Доступ - user, admin
##### Тело запроса
#
```sh
{
	"secret_key": "AAFeyWzOnlD-9G4i662PdKn2B-b4BwrCNA",
	"currency_from": "RUB",
	"currency_to": "TON",
	"amount": "2000"
}

```
##### Ответ
#
```sh
{
    "currency_from": "RUB",
    "currency_to": "TON",
    "amount_from": 2000.0,
    "amount_to": 10.869565217391305
}
```
### API администратора:
#### Изменить курс валют - /admin/setrate - POST
##### Доступ - admin
##### Тело запроса
#
```sh
{
	"secret_key": "1071daaabf1cda35d207030c898d07ff16c934b7",
	"base_currency": "TON",
    "BTC": "0.000096",
	"RUB": "184"
}

```
##### Ответ
#
```sh
{
    "rub_wallet": 184.0,
    "btc_wallet": 9.6E-5
}
```
#### Посмотреть общий баланс - /admin/currency - GET
##### Доступ - admin
##### Тело запроса
#
```sh
{
	"secret_key": "1071daaabf1cda35d207030c898d07ff16c934b7",
	"currency": "RUB"
}

```
##### Ответ
#
```sh
{
    "rub_wallet": 13500.0
}
```
#### Посмотреть количество операций - /admin/count - GET
##### Доступ - admin
##### Тело запроса
#
```sh
{
	"secret_key": "1071daaabf1cda35d207030c898d07ff16c934b7",
	"date_from": "02.03.2022",
	"date_to": "02.03.2024"
}


```
##### Ответ
#
```sh
{
    "transaction_count": 15
}
```
# Дополнительные задания

- Подключена PostgreSQL
- Cервис может вернуть json/xml в зависимости от header
- Подключен swagger
- Подключен Spring Security (Вместо jwt-токена используется secret_key)
