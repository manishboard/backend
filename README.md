# Manishboard Backend

This repository contains the backend code for **Manishboard**, a Java Spring Boot application that provides APIs for authentication, task management, and health checks. The backend is containerized with Docker and deployed using Helm on Kubernetes.

## 📂 Project Structure

```
.
├── java
│   └── kr
│       └── manish
│           └── manishboard
│               ├── config
│               │   └── SecurityConfig.java
│               ├── controller
│               │   ├── AuthController.java
│               │   ├── HealthController.java
│               │   └── TaskController.java
│               ├── ManishboardApplication.java
│               ├── model
│               │   ├── Task.java
│               │   └── User.java
│               ├── repository
│               │   ├── TaskRepository.java
│               │   └── UserRepository.java
│               ├── security
│               │   ├── JwtRequestFilter.java
│               │   └── JwtTokenUtil.java
│               └── service
│                   ├── TaskService.java
│                   └── UserService.java
└── resources
    ├── application-dev.yaml
    ├── application-global.yaml
    └── application.yaml
```

## 🚀 Features
- User authentication with JWT
- Task management (CRUD operations)
- Secure API endpoints
- Health check API

## 🛠️ Setup and Installation

### Prerequisites
- Java 17+
- Maven
- Docker
- Kubernetes & Helm (for deployment)

### Running Locally

1. Clone the repository:
   ```sh
   git clone https://github.com/manishboard/backend.git
   cd backend
   ```

2. Build the project:
   ```sh
   mvn clean install
   ```

3. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```

4. The API will be available at:
   ```
   http://localhost:8080
   ```

## 📦 Docker & Deployment

### Build and Run Docker Container
```sh
docker build -t manishboard-backend .
docker run -p 8080:8080 manishboard-backend
```

## ⚙️ GitHub Actions CI/CD Pipeline

This repository includes a **CI/CD pipeline** to automate building and deploying the backend using **GitHub Actions**.

### 📌 Build and Push Workflow
- Triggers on `push` to the `main` branch.
- Builds the Docker image.
- Pushes it to **GitHub Container Registry (GHCR).**

### 🚀 Deploy Workflow (Kubernetes Deployment)
The second workflow is **crucial** as it deploys the backend to a **Kubernetes cluster** using **Helm**.

- Triggers automatically after the **Build and Push** workflow completes successfully.
- Uses Helm to deploy the application in a Kubernetes namespace.
- Retrieves the latest image tag and updates the deployment dynamically.

#### Deployment Steps:
1. **Checkout Code:**
   - Fetches the latest repository code.
2. **Install Helm:**
   - Sets up Helm for deployment.
3. **Configure Kubernetes Access:**
   - Uses the provided `KUBE_CONFIG` secret to authenticate.
4. **Update Helm Chart and Deploy:**
   - Adds the Helm repository.
   - Upgrades or installs the application in the `manishboard-ns` namespace.
   - Sets the latest image tag dynamically for the deployment.

### Environment Variables for Deployment
| Variable           | Description                          |
|--------------------|----------------------------------|
| `PROFILE`         | Active Spring profile (`dev`, `prod`) |
| `DB_ENDPOINT`     | Database connection URL         |
| `DB_USERNAME`     | Database username               |
| `DB_PASSWORD`     | Database password               |

## 📄 API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration

### Task Management
- `GET /tasks` - Get all tasks
- `POST /tasks` - Create a task
- `PUT /tasks/{id}` - Update a task
- `DELETE /tasks/{id}` - Delete a task

### Health Check
- `GET /health` - Application health status

## 📜 License
This project is licensed under the MIT License.

## 👨‍💻 Author
**Manish**  
📧 [manish@manishboard.kr](mailto:manish@manishboard.kr)

