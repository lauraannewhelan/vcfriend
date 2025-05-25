+++markdown

# VCFriend

**VCFriend** is a secure, user-friendly web application designed for genomic data analysis. It facilitates the upload and parsing of VCF files, enabling users to explore variants, manage shortlists, and analyze individual genomic profiles. The application is built with a React frontend and a Spring Boot backend, ensuring a seamless and robust user experience.

---

## 🚀 Features

* **User Authentication**: Secure login system with session-based authentication.
* **VCF File Upload**: Upload and parse VCF files for variant analysis.
* **Variant Browser**: Interactive interface to explore and filter genomic variants.
* **Shortlist Management**: Save and manage a shortlist of significant variants.
* **Individual Profiles**: View detailed genomic information for individual samples.
* **Responsive Design**: Clean and intuitive UI with Bootstrap styling.

---

## 🛠️ Tech Stack

* **Frontend**: React, TypeScript, React Router, Bootstrap
* **Backend**: Spring Boot, Java, Hibernate (JPA), PostgreSQL
* **Authentication**: Session-based authentication using React Context API
* **Styling**: Custom CSS with Bootstrap for responsive design

---

## 📦 Installation

### Prerequisites

* Node.js (v14 or above)
* Java 17+
* PostgreSQL

---

### Backend Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/lauraannewhelan/vcfriend.git
   cd vcfriend/backend
   ```

2. **Configure the database**:

   Ensure PostgreSQL is running and create a database named `vcfriend_db`.
   Then, update `src/main/resources/application.properties` with your credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/vcfriend_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

   vcf.upload.dir=uploads/vcf
   ```

3. **Run the backend**:

   ```bash
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`.

---

### Frontend Setup

1. **Navigate to the frontend directory**:

   ```bash
   cd ../frontend
   ```

2. **Install dependencies**:

   ```bash
   npm install
   ```

3. **Start the frontend**:

   ```bash
   npm start
   ```

   The frontend will run on `http://localhost:3000`.

---

## 🔐 Authentication

VCFriend uses session-based authentication. Upon successful login, the session is stored using `sessionStorage`. This protects all routes via React Context and redirects unauthenticated users back to the login page.

* ✅ Login persists during a tab/browser session
* 🔒 Protected routes can't be accessed directly via URL without logging in
* 🔓 Logging out or closing the tab ends the session

---

## 📁 Project Structure

```text
vcfriend/
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/vcfriend/backend/
│   │       │   ├── controller/
│   │       │   ├── model/
│   │       │   ├── repository/
│   │       │   └── service/
│   │       └── resources/
│   │           └── application.properties
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── App.tsx
│   │   └── index.tsx
```

---

## 🧪 Testing

### Backend

Use tools like Postman or curl to test:

* `POST /auth/login`
* `GET /api/individuals/{id}`
* `POST /api/individuals/{id}/upload-vcf`

### Frontend

* Open `http://localhost:3000`
* Try logging in with a valid username/password
* Verify that protected pages redirect without login
* Upload a VCF file and browse variants

---

## 🤝 Contributing

Contributions are welcome!
To contribute:

1. Fork the repo
2. Create a feature branch
3. Submit a pull request

---

## 📄 License

This project is licensed under the MIT License.

---

## 📬 Contact

For questions or feedback, reach out via [github.com/lauraannewhelan](https://github.com/lauraannewhelan)
+++
