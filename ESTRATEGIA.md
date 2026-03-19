# Automation Strategy Document / Documento de Estrategia de Automatización

## English Version 🇺🇸
# Automation Strategy Document

## 1. Current State Assessment (First Week)

### Key Questions:
- What are the most critical business flows (e.g., transaction creation, login) that generate the most bugs?
- How often are regression tests executed?
- What is the current technical knowledge level of the team (4 people)?
- What does the current CI/CD pipeline in Jenkins look like? What triggers a build?
- How are requirements managed and prioritized? (e.g., User Stories in Jira)

### Technical Audit:
- Review the current test environment and databases.
- Analyze bug reports in Jira to identify the most unstable areas (to prioritize automation).

## 2. Tool Selection and Justification

- **Language (Java 21):** Stability, maturity, wide community support, and excellent integration with chosen frameworks. Version 21 is LTS (Long-Term Support).
- **Core Framework (Serenity BDD 4.2.34):** Chosen for its ability to generate living documentation and automated reports, its native integration with the Screenplay pattern (maintainable code), and its support for both UI and API testing .
- **BDD Tool (Cucumber):** Allows writing tests in Gherkin language, facilitating collaboration between manual team members, business analysts, and developers .
- **API Testing (Rest Assured):** The standard tool in the Java ecosystem for testing REST APIs, perfectly integrated with Serenity.
- **Dependency Manager (Maven):** For predictable and stable dependency management and build lifecycle.
- **Version Control (Git) and CI/CD (GitHub Actions):** To host the code and run tests automatically with each change, integrating into the DevOps flow .

## 3. Test Pyramid Proposal

- **Objective:** Follow the test pyramid model to maximize speed and stability.
- **Proposal:**
    - **Unit Tests (60-70%):** Responsibility of the development team. We will ensure they exist and run in the pipeline.
    - **Integration and API Tests (20-25%):** Coverage of critical services (like the 4 API tests on Parabank). They are faster than E2E tests.
    - **E2E (UI) Tests (10-15%):** For critical business flows (like the SauceDemo tests in the project). They will focus on the user experience.

## 4. Automation Roadmap (First 6 Months)

- **Month 1 (Foundation):** Set up the repository, the CI/CD pipeline, and the base framework. Automate critical flows (e.g., login, successful transaction). This provides immediate tangible value.
- **Months 2-3 (API Coverage):** Automate the financial services APIs (core of the business). This allows for fast and reliable testing of business logic.
- **Months 4-6 (UI Expansion and Negative Tests):** Automate secondary E2E flows and negative cases (like those implemented in checkout and login). Integrate tests into the Jenkins pipeline to run with every deployment.

## 5. Team Transition Plan

- **Objective:** Transform them into automation engineers.
- **Approach:**
    - **Mentorship and Pair Programming:** Work side-by-side with them on writing the first tests, focusing first on business logic and then on technical details.
    - **Layered Training:** Don't overwhelm them with everything at once. First, have them write features in Gherkin. Then, have them understand the Steps. Finally, have them create Tasks and Questions in the Screenplay pattern.
    - **Code Ownership:** Assign ownership of different modules or features to different team members.

## 6. Governance Model

- **Standardization:** Adopt the Screenplay pattern. Define naming conventions for features, steps, and tasks (use the team's language, e.g., Spanish or English).
- **Code Reviews (PRs):** Establish that all test code must go through a Pull Request and be reviewed by at least one other team member, using the same rigor as with production code.
- **Maintenance:** Define who is responsible for fixing broken tests (the person who created them or the team that introduced the change). Failed tests must be a priority.

## 7. Metrics and KPIs to Demonstrate ROI

### Progress KPIs:
- **Automation Coverage:** Percentage of manual test cases automated.
- **Number of Automated Tests:** Evolution over time (from 0 to 14, and increasing).

### Efficiency and ROI KPIs:
- **Regression Execution Time:** Reduction in execution time of a test suite (e.g., from 2 manual days to 1 automated hour).
- **Feedback Cycle Time:** Time between a developer pushing code and receiving the results of the automated tests.
- **Defects Detected in Early Stages:** Number of bugs found by automated tests before reaching manual testing or production.


## Versión en Español 🇪🇸

### 1. Evaluación del Estado Actual (La Primera Semana)

Preguntas Clave:

¿Cuáles son los flujos más críticos para el negocio (ej. creación de transacción, login) que generan más bugs?

¿Con qué frecuencia se ejecutan las regresiones?

¿Cuál es el nivel de conocimiento técnico del equipo actual (4 personas)?

¿Cómo es el pipeline de CI/CD actual en Jenkins? ¿Qué desencadena una build?

¿Cómo se gestionan y priorizan los requisitos? (ej. Historias de usuario en Jira)

Auditoría Técnica:

Revisar el entorno de pruebas actual y las bases de datos.

Analizar los informes de bugs de Jira para identificar las áreas más inestables (para priorizar la automatización).

### 2. Selección de Herramientas y Justificación

Lenguaje (Java 21): Estabilidad, madurez, amplia comunidad de soporte y excelente integración con los frameworks elegidos. La versión 21 es LTS (soporte a largo plazo).

Framework Principal (Serenity BDD 4.2.34): Elegido por su capacidad de generar reportes vivos y documentación automatizada, su integración nativa con el patrón Screenplay (código mantenible) y su soporte para pruebas tanto de UI como de API .

Herramienta BDD (Cucumber): Permite escribir pruebas en lenguaje Gherkin, facilitando la colaboración entre los miembros del equipo manual, los analistas de negocio y los desarrolladores .

*Pruebas de API (Rest Assured): La herramienta estándar en el ecosistema Java para probar APIs REST, perfectamente integrada con Serenity.

*Gestor de Dependencias (Maven): Para una gestión de dependencias y ciclo de vida de construcción predecible y estable.

*Control de Versiones (Git) y CI/CD (GitHub Actions): Para alojar el código y ejecutar las pruebas de forma automática con cada cambio, integrándose en el flujo DevOps .

### 3. Propuesta de Pirámide de Automatización

Objetivo: Seguir el modelo de la pirámide de pruebas para maximizar la velocidad y la estabilidad.

Propuesta:

Pruebas Unitarias (60-70%): Responsabilidad del equipo de desarrollo. Nosotros nos aseguraremos de que existan y se ejecuten en el pipeline.

Pruebas de Integración y API (20-25%): Cobertura de servicios críticos (como las 4 pruebas de API en Parabank). Son más rápidas que las E2E.

Pruebas E2E (UI) (10-15%): Para flujos críticos de negocio (como los de SauceDemo en el proyecto). Se centrarán en la experiencia del usuario.

### 4. Roadmap de Automatización (Primeros 6 Meses)

Mes 1 (Fundación): Configurar el repositorio, el pipeline de CI/CD y el framework base. Automatizar los flujos críticos (ej. login, transacción exitosa). Esto ya da un primer valor tangible.

Mes 2-3 (Cobertura API): Automatizar las APIs de servicios financieros (core del negocio). Esto permite probar la lógica de negocio de forma rápida y fiable.

Mes 4-6 (Expansión UI y Negativos): Automatizar flujos E2E secundarios y casos negativos (como los implementados en checkout y login). Integrar las pruebas en el pipeline de Jenkins para que se ejecuten con cada despliegue.

### 5. Plan de Transición del Equipo

Objetivo: Convertirlos en ingenieros de automatización.

Enfoque:

Mentoría y Pair Programming: Trabajar codo a codo con ellos en la escritura de los primeros tests, enfocándose primero en la lógica de negocio y luego en los detalles técnicos.

Formación en Capas: No abrumarlos con todo a la vez. Primero, que escriban features en Gherkin. Luego, que entiendan los Steps. Finalmente, que creen Tasks y Questions en el patrón Screenplay.

Propiedad del Código: Asignar la propiedad de diferentes módulos o features a diferentes miembros del equipo.

### 6. Modelo de Gobernanza

Estandarización: Adoptar el patrón Screenplay. Definir naming conventions para features, steps y tasks (usar el idioma del equipo, por ejemplo, español o inglés).

Revisiones de Código (PRs): Establecer que todo código de prueba debe pasar por una Pull Request y ser revisado por al menos otro miembro del equipo, usando la misma rigurosidad que con el código de producción.

Mantenimiento: Definir quién es responsable de arreglar las pruebas rotas (el que las creó o el equipo que introdujo el cambio). Las pruebas fallidas deben ser una prioridad.

### 7. Métricas y KPIs para Demostrar el ROI

KPIs de Progreso:

Cobertura de Automatización: Porcentaje de casos de prueba manuales automatizados.

Número de Pruebas Automatizadas: Evolución en el tiempo (de 0 a 14, y en aumento).

KPIs de Eficiencia y ROI:

Tiempo de Ejecución de Regresión: Reducción del tiempo de ejecución de un conjunto de pruebas (ej. de 2 días manuales a 1 hora automatizada).

Tiempo de Retroalimentación (Feedback Cycle): Tiempo entre que un desarrollador sube código y recibe el resultado de las pruebas automáticas.

Defectos Detectados en Fases Tempranas: Número de bugs encontrados por las pruebas automáticas antes de llegar a pruebas manuales o producción.


**Document prepared by:** Jean C Caro N  
**Date:** March 18, 2026  
**Repository:** [https://github.com/jeancarls-t/saucedemo-final](https://github.com/jeancarls-t/saucedemo-final)