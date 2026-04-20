# 🔹 Selenium Automation Framework

A maintainable UI automation framework built using **Java**, **Selenium**, and **TestNG**, following best practices for scalability and CI integration.

---

## 🚀 Tech Stack

| Tool | Purpose |
|------|---------|
| Java | Core language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution & reporting |
| Maven | Build & dependency management |
| Page Object Model | Design pattern |
| GitHub Actions | CI/CD pipeline |

---

## 📂 Project Structure

```
selenium-framework/
├── src/
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java
│           ├── pages/
│           │   ├── LoginPage.java
│           │   ├── ProductPage.java
│           │   ├── CartPage.java
│           │   └── CheckoutPage.java
│           ├── tests/
│           │   ├── LoginTest.java
│           │   ├── OrderTest.java
│           │   └── NegativeTest.java
│           └── utils/
├── testng.xml
└── pom.xml
```

---

## ▶️ How to Run Tests Locally

### Prerequisites
- Java 17+
- Maven 3.8+
- Google Chrome (latest)

### Steps

**1. Clone the repository:**
```bash
git clone https://github.com/narasimha123gopal-dev/selenium-framework.git
```

**2. Navigate to project:**
```bash
cd selenium-framework
```

**3. Run tests using Maven:**
```bash
mvn clean test
```

---

## ⚙️ Continuous Integration (CI)

This project is integrated with **GitHub Actions** to automatically run TestNG tests on every push and pull request.

### 🔁 CI Workflow Includes

- ✅ Code checkout
- ✅ Java 17 setup
- ✅ Dependency installation via Maven
- ✅ Test execution using TestNG
- ✅ Build status reporting

### 📄 GitHub Actions Workflow

```yaml
name: Selenium Tests CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Code
        uses: actions/checkout@v3

      - name: Set up Java
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'

      - name: Install Dependencies
        run: mvn clean install -DskipTests

      - name: Run Tests
        run: mvn test
```

---

## ✅ Features

- 🧩 Clean Page Object Model (POM) design
- 🔁 Reusable page utilities
- 🧪 TestNG-based test execution
- 🤖 CI-enabled via GitHub Actions
- 📦 Easy to scale and maintain

---

## 🧪 Test Coverage

| Test Class | Scenario |
|------------|----------|
| `LoginTest` | Valid login redirects to inventory page |
| `OrderTest` | Full end-to-end purchase flow |
| `NegativeTest` | Invalid login, locked user, empty cart |

---

## 📌 Future Enhancements

- 📊 Reporting — Extent Reports / Allure
- ⚡ Parallel execution
- 🌐 Cross-browser testing
- 🔧 Data-driven testing with Excel/JSON

---

## 👤 Author

**Narasimha**
[GitHub](https://github.com/narasimha123gopal-dev)
