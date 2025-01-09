

# Java Приложение для Взаимодействия с API Wildberries

Это небольшое Java приложение демонстрирует использование нескольких паттернов проектирования для взаимодействия с API маркетплейса Wildberries (WB) и вывода запрашиваемой пользователем информации на экран.

## Используемые Паттерны Проектирования

### 1. Singleton

**Описание**: Гарантирует, что у класса есть только один экземпляр, и предоставляет глобальную точку доступа к этому экземпляру.

**Пример в коде**: Класс `ApiClient` обеспечивает единственный экземпляр для взаимодействия с API.

```java
public class ApiClient {
    private static ApiClient instance;
    private String apiUrl = "https://api.wildberries.ru";

    private ApiClient() {}

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
```

**Почему выбран**: Singleton используется для централизованного управления доступом к API и экономии ресурсов, так как создание множества экземпляров класса для взаимодействия с API неэффективно.

### 2. Factory Method

**Описание**: Определяет интерфейс для создания объекта, но позволяет подклассам изменять тип создаваемого объекта.

**Пример в коде**: Класс `ApiRequestFactory` создаёт различные типы запросов к API на основе переданного типа.

```java
public interface ApiRequest {
    String execute();
}

public class ProductRequest implements ApiRequest {
    private String productId;

    public ProductRequest(String productId) {
        this.productId = productId;
    }

    @Override
    public String execute() {
        // Здесь будет логика для выполнения запроса к API
        return "Product info for ID: " + productId;
    }
}

public class ApiRequestFactory {
    public static ApiRequest createRequest(String type, String productId) {
        if (type.equalsIgnoreCase("product")) {
            return new ProductRequest(productId);
        }
        // Можно добавить другие типы запросов
        throw new IllegalArgumentException("Unknown request type");
    }
}
```

**Почему выбран**: Factory Method упрощает создание различных типов запросов и делает код более гибким для добавления новых типов запросов в будущем.

### 3. Strategy

**Описание**: Определяет семейство алгоритмов, инкапсулирует каждый из них и делает их взаимозаменяемыми.

**Пример в коде**: Интерфейс `ResponseHandler` и его реализация `ProductResponseHandler` позволяют обрабатывать различные типы ответов от API.

```java
public interface ResponseHandler {
    void handle(String response);
}

public class ProductResponseHandler implements ResponseHandler {
    @Override
    public void handle(String response) {
        System.out.println("Handling product response: " + response);
    }
}

public class ResponseHandlerContext {
    private ResponseHandler handler;

    public void setHandler(ResponseHandler handler) {
        this.handler = handler;
    }

    public void handleResponse(String response) {
        handler.handle(response);
    }
}
```

**Почему выбран**: Strategy позволяет легко добавлять новые стратегии обработки ответов без изменения существующего кода, что делает систему более гибкой и расширяемой.

## Основной Класс Приложения

```java
public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = ApiClient.getInstance();
        ApiRequest request = ApiRequestFactory.createRequest("product", "12345");
        String response = request.execute();

        ResponseHandlerContext context = new ResponseHandlerContext();
        context.setHandler(new ProductResponseHandler());
        context.handleResponse(response);
    }
}
```

## Заключение

Использование паттернов Singleton, Factory Method и Strategy в этом приложении помогает решить конкретные задачи, связанные с созданием объектов, управлением доступом к ресурсам и обработкой различных типов данных. Эти паттерны делают код более модульным, гибким и легко поддерживаемым.