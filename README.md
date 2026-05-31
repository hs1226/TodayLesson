# TodayLesson 

> A hobby-sharing platform that connects **creators (seniors)** who want to share their skills with **subscribers** who want to learn something new.

---

## 📌 About the Project

As average life expectancy increases, many people are preparing for new careers or side businesses after retirement. At the same time, there is a growing demand for hobby-based learning and lifestyle enrichment.

**TodayLesson** addresses this need by providing a platform where:
- People with skills and know-how can register as **seniors (instructors)** and share their knowledge through lessons and store products.
- People who want to learn can **subscribe to lessons**, purchase products, and engage with the community.

The platform is designed to be **tablet-friendly and mobile-responsive**, optimized for a wide range of users.

---

## 🗂️ Site Structure

### User (사용자)

```
User Home
├── Cart
├── Login
├── Sign Up
├── My Page
│   ├── Profile Management
│   ├── Order History
│   │   └── Order Details
│   ├── My Lessons
│   │   ├── My Lesson Management
│   │   └── Lesson (Register / Manage / Senior Info)
│   ├── My Lesson Management
│   │   ├── Sales Settlement
│   │   │   ├── Sales Status
│   │   │   ├── Settlement Request
│   │   │   └── Settlement History
│   │   └── Lesson Calendar
│   │       └── Calendar Registration
│   ├── 1:1 Inquiry
│   ├── Likes
│   └── Current Lesson List
├── Early Bird
├── All Lessons
├── Free Board
├── Store
└── Event
    ├── Detail Page
    │   ├── Cart → Checkout → Order Form → Payment
    │   ├── Buy Now → Order Form → Payment
    │   └── Likes
    └── Store Detail Page
        ├── Cart → Checkout → Order Form → Payment
        ├── Buy Now → Order Form → Payment
        └── Likes
```

### Admin (관리자)

```
Admin Home
├── Member Management
│   ├── Member Management
│   ├── Senior Management
│   └── Member Inquiry History
├── Lesson Management
│   ├── Lesson Inquiry
│   ├── New Lesson Review
│   ├── Senior Sales Status
│   └── Senior Sales Settlement
├── Store Management
│   ├── Product Inquiry
│   ├── Product Registration
│   ├── Order History Management
│   └── Order Cancellation Management
├── Operations Management
│   ├── Event Management
│   ├── Community Management
│   ├── Review Management
│   └── 1:1 Inquiry Management
├── Statistics
│   ├── Sales Statistics
│   └── Member Statistics
└── Other Management
    ├── Banner Management
    ├── Popup Management
    └── Admin Settings
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring MVC, MyBatis |
| Frontend | JavaScript, CSS, JSP |
| Database | MySQL |
| Build Tool | Maven |
| IDE | Eclipse (Spring Tool Suite) |

---

## 🗃️ Database (ERD Overview)

Key tables include:

- **member** — user accounts (id, password, name, phone, address, points, level, etc.)
- **senior** — instructor profiles (account, email, phone, nickname, etc.)
- **lesson** — lesson listings (title, content, price, category, capacity, dates, etc.)
- **product** — store products (name, price, category, description, thumbnail, etc.)
- **orderlist** — order records linking member, product, lesson, and cart
- **cart** — shopping cart (product, lesson, quantity, member)
- **freeboard** — community free board posts
- **boardreply** — comments on free board posts
- **notice** — admin notice posts
- **question_1_1** — 1:1 inquiry (title, content, answer)
- **sales** — senior sales records
- **calculate** — settlement records
- **stat_log** — member activity logs
- **lreview / pdreview** — lesson and product reviews
- **tag / like / popup / event** — additional feature tables

---

## 🚀 Getting Started

### Requirements

- Java 8+
- Apache Tomcat 8.5+
- MySQL 5.7+
- Maven 3+

### Setup

```bash
# Clone the repository
git clone https://github.com/hs1226/TodayLesson.git
cd TodayLesson

# Build the project
mvn clean install
```

1. Import the project into Eclipse (as an existing Maven project).
2. Set up your MySQL database and run the provided SQL schema.
3. Update `src/main/resources/db.properties` with your DB credentials.
4. Deploy to a local Tomcat server and run.

---

## 👥 Contributors

| Name |
|---|
| EUNJI LEE    |
| Jinyoung Kim |
| Hyemi Kim    |
| Youngin Jung |
| Hwasoo Ha    |

---

## 📄 License

This project was developed as a team study project. All rights reserved by the contributors.
