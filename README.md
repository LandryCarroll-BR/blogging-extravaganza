# SmartStore Project

A full-stack web application built with a **Java Spring Boot backend** and a **Next.js frontend**, designed using *
*Clean Architecture** principles and enhanced with **Generative AI-assisted development**.

---

## 📦 Tech Stack

- **Backend:** Java 17, Spring Boot, H2 (dev) / PostgreSQL (prod), JWT-based Auth, Clean Architecture
- **Frontend:** Next.js (App Router), TypeScript, shadcn/ui, Auth.js (Google provider)
- **Infrastructure:** RESTful API, monorepo structure
- **Dev Tools:** Java Faker (data seeding), Next.js Server Actions, React Server Components

---

## 🧠 How AI Was Used

This project incorporated Generative AI (ChatGPT) to:

- Scaffold backend models, services, and repositories
- Implement frontend server actions and UI layout
- Integrate JWT token validation between Next.js and Spring
- Seed mock data using `CommandLineRunner` and Faker

See the included [Generative_AI_Report.pdf](./Generative_AI_Report.pdf) for details.

---

## 🚀 Monorepo Structure

/
├── backend/ # Spring Boot App
│ └── src/main/java/... # Domain, Application, Infrastructure, Web
├── frontend/ # Next.js App Router
│ └── app/ # Routes and Pages
├── README.md
└── Generative_AI_Report.pdf

yaml
Copy
Edit

---

## ⚙️ Setup

### 🔧 Prerequisites

- Java 17+
- Node.js 18+
- PostgreSQL (for prod)
- Yarn or npm

### ▶️ Running the Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

By default uses an H2 file database (smartstoredb.mv.db).
Visit http://localhost:8080/h2-console to explore seeded data.

### ▶️ Running the Frontend (Next.js)

``` bash
Copy
Edit
cd frontend
npm install
npm run dev
```

Runs on http://localhost:3000

## 🔐 Authentication

- Google Sign-In via Auth.js (NextAuth v5)
- ID token from frontend is passed to the backend and verified using a custom JwtDecoder
- Backend protects all API routes using JWT and Spring Security

## 🧪 Data Seeding

- On startup, the backend seeds:
- 5 static users + 50 sample posts
- Additional users/posts generated using Java Faker

## 📚 Features

- Create, edit, view, and delete posts
- Authenticated dashboard
- Form submission using Next.js server actions
- Type-safe fetch integration between frontend and backend

## 📄 License

- MIT — for educational use.

