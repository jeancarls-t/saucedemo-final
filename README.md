# SauceDemo Automation Framework - Final Version

## 📋 Description
Test automation framework for [SauceDemo](https://www.saucedemo.com) and [Parabank API](https://parabank.parasoft.com) using:
- **Java 21**
- **Serenity BDD 4.2.34** with Screenplay pattern
- **Cucumber 7.21.1** for BDD
- **JUnit 4** for test runner (stable with Serenity)
- **JUnit 5** for additional support
- **Maven** for dependency management
- **Rest Assured** for API testing

## 🎯 Implemented Test Cases (14 scenarios)

### UI Tests (SauceDemo)
| Feature | Scenarios | Tags |
|---------|-----------|------|
| Login | 3 (2 negative) | `@login` |
| Shopping Cart | 4 | `@cart` |
| Checkout | 2 (1 negative) | `@checkout` |

### API Tests (Parabank)
| Scenario | Method | Expected Code | Tags |
|----------|--------|---------------|------|
| Available account | GET | 200 | `@api` |
| Customer accounts list | GET | 200 | `@api` |
| Failed transfer (insufficient funds) | POST | 400 | `@api` |
| Non-existent account | GET | 400 | `@api` |

## 🚀 Prerequisites
- Java JDK 21
- Maven 3.9+
- Git
- Chrome or Firefox browser

## 🔧 Installation and Setup

### 1. Clone the repository
```bash
git clone [https://github.com/jeancarls-t/saucedemo-final](https://github.com/jeancarls-t/saucedemo-final)
cd serenity-final
```

### 2. Run all tests
```bash
mvn clean verify
```

### 3. Run specific tests by tag
```bash
# UI tests only
mvn clean verify -Dcucumber.filter.tags="not @api"

# API tests only
mvn clean verify -Dcucumber.filter.tags="@api"

# Cart tests only
mvn clean verify -Dcucumber.filter.tags="@cart"

# Login tests only
mvn clean verify -Dcucumber.filter.tags="@login"
```

## 📊 Reports

Reports are generated at:
- **Serenity Report**: `target/site/serenity/index.html`
- **Cucumber Report**: `target/cucumber-html-report.html`
- **Surefire Report**: `target/site/surefire-report.html`

To view reports after execution:
```bash
# On Windows
Start-Process "target/site/serenity/index.html"

# On Linux/Mac
open target/site/serenity/index.html
```

## 🏗️ Project Structure

```
src/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── saucedemo/
│   │           ├── runners/
│   │           │   └── CucumberTestSuite.java
│   │           ├── steps/
│   │           │   ├── CommonSteps.java
│   │           │   ├── LoginSteps.java
│   │           │   ├── CartSteps.java
│   │           │   ├── CheckoutSteps.java
│   │           │   └── api/
│   │           │       └── ParabankApiSteps.java
│   │           ├── tasks/
│   │           │   ├── Login.java
│   │           │   ├── AddToCart.java
│   │           │   ├── RemoveFromCart.java
│   │           │   ├── NavigateToCart.java
│   │           │   └── Checkout.java
│   │           ├── questions/
│   │           │   ├── LoginQuestions.java
│   │           │   ├── CartQuestions.java
│   │           │   └── CheckoutQuestions.java
│   │           ├── user_interfaces/
│   │           │   ├── LoginPage.java
│   │           │   ├── ProductListPage.java
│   │           │   ├── CartPage.java
│   │           │   └── CheckoutPage.java
│   │           └── helpers/
│   │               └── BrowserHelper.java
│   └── resources/
│       ├── features/
│       │   ├── login.feature
│       │   ├── shopping_cart.feature
│       │   ├── checkout.feature
│       │   └── api/
│       │       └── parabank_api.feature
│       └── junit-platform.properties
├── .github/
│   └── workflows/
│       └── ci.yml
├── serenity.properties
├── pom.xml
└── README.md
```

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming language |
| Serenity BDD | 4.2.34 | Automation framework |
| Cucumber | 7.21.1 | BDD and Gherkin |
| **JUnit 4** | 4.13.2 | **Test runner** (stable with Serenity) |
| JUnit 5 | 5.11.4 | Additional support |
| Rest Assured | 5.5.0 | API testing |
| Maven | 3.9+ | Dependency management |
| GitHub Actions | - | Continuous Integration |

## 🔄 CI/CD with GitHub Actions

The project includes a CI pipeline that:
- Runs on every `push` and `pull_request`
- Executes all tests on Ubuntu with Java 21
- Generates and stores reports as artifacts

## 📈 Screenplay Pattern Implementation

- **Tasks**: User actions (Login, AddToCart, Checkout)
- **Questions**: Verifications (itemCount, priceOf, errorMessage)
- **Page Objects**: UI elements (LoginPage, CartPage)
- **Actors**: Users performing the tasks

## 📝 Test Strategy

### Why this stack:
- **Serenity BDD**: Detailed reports and traceability
- **Screenplay**: Maintainable and readable code
- **Cucumber**: Collaboration with non-technical stakeholders
- **JUnit 4**: Stable runner for Serenity + Cucumber

### Test Pyramid distribution:
- **Unit**: N/A (framework level)
- **Integration**: 30% (API tests)
- **E2E**: 70% (UI tests)

## 🤝 Contributing

1. Follow Screenplay pattern
2. Keep features in readable Gherkin
3. Run tests locally before PR
4. Ensure coverage of positive and negative cases

## 📄 License
This project is part of a technical assessment.

## ✨ Author
**Jean Caro**  
GitHub: [@jeancarls-t](https://github.com/jeancarls-t)  
Email: jeancarlst28@gmail.com