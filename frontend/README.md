# SmartStore Frontend

A modern Next.js frontend for the SmartStore platform. This app connects to a Spring Boot backend, allowing users to
authenticate via Google, create posts, and view a dynamic post feed.

## 🔧 Tech Stack

- **Next.js App Router**
- **TypeScript**
- **Auth.js v5** (Google authentication)
- **shadcn/ui** (Tailwind + Radix UI components)
- **Clean Architecture-inspired structure**
- **Java Spring Boot backend** (REST API)
- **H2 / PostgreSQL backend persistence**

## 🗂 Folder Structure

```bash
.
├── app/                    # App Router pages
├── components/             # UI components (shadcn/ui)
├── features/               # Domain-specific logic (user, posts, etc.)
├── lib/                    # Generic utils (auth, fetch clients)
├── public/                 # Static assets
├── styles/                # Global styles (optional)
└── types/                  # Global type declarations (e.g., next-auth)
```

---

## 🚀 Getting Started

### 1. Install dependencies

```bash
npm install
```

### 2. Set up environment variables

Create a `.env.local` file:

```env
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
NEXTAUTH_SECRET=your-random-secret
NEXTAUTH_URL=http://localhost:3000
```

> Get your Google OAuth credentials from [Google Cloud Console](https://console.cloud.google.com/apis/credentials)

---

### 3. Run the development server

```bash
npm run dev
```

App will be available at [http://localhost:3000](http://localhost:3000)

---

## 🔐 Authentication

- Uses **Auth.js v5** and the `auth()` helper
- Authenticated pages fetch user data via **JWT Bearer tokens**
- Backend validates the token with Google's JWKs

---

## 📬 API Integration

All data (user profiles, posts) is fetched from the **Spring Boot backend** via authenticated `fetch()` calls using the
Google ID token as a `Bearer` token.

Example:

```ts
fetch("http://localhost:8080/api/posts", {
    headers: {
        Authorization: `Bearer ${session.idToken}`,
    }
});
```

---

## ✨ Features

- 🔐 Google Sign-in and secure session handling
- 📝 Create/view/edit/delete personal posts
- 📄 Global feed of all posts
- 🧼 Clean UI with [shadcn/ui](https://ui.shadcn.com/)
- 📦 Backend integration via REST

---

## 📚 Future Improvements

- Role-based access controls (admin dashboard)
- Post reactions (likes, comments)
- Pagination and infinite scrolling
- Image uploads and profile editing

---

## 🛠 Dev Tips

- You can preview authenticated layouts in `/dashboard`
- Backend runs separately (`Spring Boot` on port 8080)
- Use the H2 console to inspect test data: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)