# Developer documentation

## Основней стек технологий

* Kotlin
* Coroutines
* Compose
* Kodein
* Ktor
* Sqldelight
* Decompose

## Разбиение по модулям

* [doc](core.md) `core/**` - модули содержат базовый функционал, не относящийся к бизнес логике проекта
* [doc](feature.md) `feature/**` - модули содержат код определенной, в достаточной мере обособленной функциональности
  проекта
* [doc](build-script.md) `build-script` - модуль содержащий сборочные скрипты и соглашения (conventions)

## Структура модулей

Все модули стараемся писать c разделением на слои:

* `repository` - код отвечающий за хранение данных (может использовать android компоненты), код в этих модулях не должен
  заниматься обработкой данны, только хранением.
* `service` - код отвечающий за взаимодействие с системными компонентами, должен асбстрагировать `domain` слой от
  android фреймворка.
* `domain` - код бизнес логики, содержит код отвечающий за основную логику приложения
* `ui` - код пользовательского интерфейса (*View, *ViewModel, *ViewState, *ViewModelArg)