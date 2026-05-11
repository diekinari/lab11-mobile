# IntentBrowser — Сторонние приложения и мини-браузер

Android-приложение на **Java** с XML-разметками: вызов сторонних приложений через Intent + встроенный мини-браузер на WebView.

**Практическое занятие №11**, Тема 3 — Мобильная разработка.

---

## Стек

| Компонент | Версия |
|-----------|--------|
| Язык | Java |
| UI | XML layouts |
| AGP | 8.7.3 |
| Gradle | 8.9 |
| compileSdk | 34 |
| minSdk | 24 |
| Material Components | 1.12.0 |
| AppCompat | 1.7.0 |

---

## Структура проекта

```
app/src/main/
├── java/com/example/navigation10/
│   ├── MainActivity.java      ← экран с Intent-кнопками и фонариком
│   └── BrowserActivity.java   ← мини-браузер на WebView
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   └── activity_browser.xml
│   └── values/
│       ├── strings.xml
│       ├── colors.xml
│       └── themes.xml
└── AndroidManifest.xml
```

---

## Функционал

### Экран 1 — Сторонние приложения
- **Позвонить** — открывает звонилку с номером
- **Открыть браузер** — открывает ссылку во внешнем браузере
- **Написать письмо** — открывает почтовый клиент
- **Google Play** — открывает страницу приложения в маркете
- **Фонарик** — переключатель вкл/выкл с Toast-уведомлениями

### Экран 2 — Мини-браузер
- WebView с загрузкой сайтов внутри приложения
- Поле ввода URL + кнопки «Домой» и «Поиск»
- Корректная навигация кнопкой «Назад» (goBack)

---

## Как собрать

1. Открой проект в Android Studio (Koala / Ladybug / Meerkat или новее)
2. **File → Sync Project with Gradle Files**
3. Запусти через **Run** или `Shift + F10`
