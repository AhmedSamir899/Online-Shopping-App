# 🛒 Online Shopping App — Android E-Commerce Application

<p align="center">
  <em>A full-featured Android e-commerce app with separate User and Admin interfaces, barcode scanning, voice search, Google Maps integration, sales analytics charts, and offline SQLite persistence.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Android-API%2021%2B-green.svg" alt="Android API 21+"/>
  <img src="https://img.shields.io/badge/Java-1.8-orange.svg" alt="Java 1.8"/>
  <img src="https://img.shields.io/badge/SQLite-Local%20DB-blue.svg" alt="SQLite"/>
  <img src="https://img.shields.io/badge/UI-Material%20Design-757575.svg" alt="Material Design"/>
  <img src="https://img.shields.io/badge/Status-Academic%20Project-success.svg" alt="Status"/>
</p>

---

## 📖 Overview

**Online Shopping App** is a complete Android e-commerce application demonstrating how a production-style shopping experience is built end-to-end on mobile. The app serves **two distinct user roles** — regular shoppers and an administrator — each with its own navigation drawer, activities, and feature set. It applies three classic Gang-of-Four design patterns (**Factory**, **Singleton**, **Adapter**) to keep the codebase decoupled and extensible.

All persistence runs on a local **SQLite** database (no backend server) so the app is fully functional offline. Shoppers can browse categorized products, search by name, voice, or barcode, manage a cart, complete checkout, and leave feedback. Admins can manage categories and items, delete users, view sales reports filtered by date/time, browse customer feedback, and visualize best-selling products via interactive **pie** and **bar** charts.

The project was built as an academic OOP & Android coursework deliverable at Ain Shams University.

---

## ✨ Features

### 👤 User side

| Feature                              | Description                                                                                                  |
|--------------------------------------|--------------------------------------------------------------------------------------------------------------|
| 🔐 **Sign-up / Login / Recovery**    | Account creation with security question, password recovery flow, and password change.                       |
| 🏠 **Categorized product browsing**  | Browse items across Books, Electronics, Clothes, Games, and Musical Instruments categories.                 |
| 🔍 **Three search modes**            | Type-to-search, **voice search** (Android speech recognizer), and **barcode scan** (ZXing camera scanner).   |
| 📦 **Item details & stock**          | Detailed item view with description, price, stock count, and product image.                                  |
| 🛒 **Shopping cart**                 | Add/remove items, increment/decrement quantities, real-time total price calculation, per-user cart isolation. |
| 💳 **Checkout workflow**             | Place order; sales recorded to `Reports` and `bestselling` tables for admin analytics.                       |
| 💬 **Feedback system**               | Submit text feedback on purchased items; visible to admins via the feedback console.                          |
| 📍 **Google Maps integration**       | Show nearest store / delivery location using FusedLocationProviderClient and Maps SDK.                       |
| 🔄 **Pull-to-refresh**               | SwipeRefreshLayout on item lists for a modern UX.                                                            |

### 🛠 Admin side

| Feature                              | Description                                                                                                  |
|--------------------------------------|--------------------------------------------------------------------------------------------------------------|
| 🗂 **Category management**           | Create, update, and delete product categories with custom icons and background images.                       |
| 📦 **Item management**               | Add, update, and delete items with full attribute set (name, description, price, picture, stock, barcode).   |
| 👥 **User management**               | View all registered users and delete problematic accounts.                                                    |
| 📊 **Sales reports**                 | Filter sales by date and time range; results populate an autocomplete dropdown.                              |
| 📈 **Pie chart — best sellers**      | Visualize best-selling products by units sold using MPAndroidChart.                                          |
| 📉 **Bar chart — sales volume**      | Compare sales volume across products with animated bar chart.                                                |
| 💬 **Feedback console**              | Read all feedback submitted by users across all items.                                                        |

### 🧩 Design patterns applied

| Pattern        | Where                                                                                              |
|----------------|----------------------------------------------------------------------------------------------------|
| **Factory**    | `CategoryFactory.createCategory(...)` returns `CustomCategory` instances — decouples category creation from caller. |
| **Singleton**  | `Session.getInstance()` — thread-safe double-checked locking; holds the logged-in user across activities. |
| **Adapter**    | `itemAdapter`, `itemcartAdapter`, `CategoryAdapter`, `FeedbackAdapter` — bridge between data models and RecyclerView items. |

---

## 🏗 Architecture

```
┌─────────────────────────────────────────────────────────────┐
│  Activities (UserFunctions/ + AdminFunctions/)               │
│  29 Activity classes, each with its own XML layout           │
│  Two navigation drawers: nav_drawer_menu_user / _admin       │
└────────────────────────────┬────────────────────────────────┘
                             │ uses
┌────────────────────────────▼────────────────────────────────┐
│  Adapters & Models (AdaptersAndModels/)                      │
│  itemModel, itemcartAdapter, CategoryModel, FeedbackModel    │
│  RecyclerView adapters + POJOs for list rendering            │
└────────────────────────────┬────────────────────────────────┘
                             │ calls
┌────────────────────────────▼────────────────────────────────┐
│  Data Access (DateBaseSql/)                                  │
│  Login_Register_DBHelper — users, auth, password recovery    │
│  CategoryItemsDataBase — categories, items, cart, feedback,  │
│                          reports, bestsellers                │
└────────────────────────────┬────────────────────────────────┘
                             │ SQLite
┌────────────────────────────▼────────────────────────────────┐
│  SQLite Database (on-device)                                 │
│  Tables: Userdetails, categories, itemes, cartitems,         │
│          Feedback, Reports, bestselling                      │
└──────────────────────────────────────────────────────────────┘
```

### Session & state management

A thread-safe **Singleton** (`Session.getInstance()`) holds the currently logged-in user's username across the entire app lifecycle. Every activity that needs user-scoped data (cart, checkout, feedback) reads from this singleton — no `Intent` extras threading required.

---

## 🗄 Database Schema

Two `SQLiteOpenHelper` subclasses manage a total of **7 tables**:

### `Login_Register_DBHelper` → database `UserData`

| Table          | Columns                                                                                       |
|----------------|-----------------------------------------------------------------------------------------------|
| `Userdetails`  | `username` (PK), `password`, `question` (security Q), `birthdate`, `ssn`, `creditcard`         |

### `CategoryItemsDataBase` → database `CategoryDatabase`

| Table           | Columns                                                                                                            |
|-----------------|--------------------------------------------------------------------------------------------------------------------|
| `categories`    | `id` (PK), `categoryname`, `categoryicon` (drawable res), `categorybackground` (drawable res)                       |
| `itemes`        | `id` (PK), `itemname`, `itemdescription`, `itemprice`, `itempicture`, `itembackground`, `itemCount`, `categorytype`, `barcode` |
| `cartitems`     | Same columns as `itemes` + `username` (per-user cart isolation)                                                    |
| `Feedback`      | `id` (PK), `itemname`, `yourfeedback`, `username`                                                                  |
| `Reports`       | `id` (PK), `username`, `itemname`, `date`, `time`                                                                  |
| `bestselling`   | `id` (PK), `itemname`, `itemcount`                                                                                 |

---

## 🛠 Tech Stack

| Layer                | Technology                                                                                       |
|----------------------|--------------------------------------------------------------------------------------------------|
| Language             | Java 1.8                                                                                         |
| Min SDK              | Android API 21 (Android 5.0 Lollipop)                                                            |
| Target SDK           | Android API 32 (Android 12L)                                                                     |
| UI                   | Material Design Components 1.7.0 + ConstraintLayout 2.1.4                                        |
| Persistence          | SQLite via `SQLiteOpenHelper` (no ORM, raw SQL + `ContentValues`)                                |
| Charts               | MPAndroidChart v3.1.0 (pie + bar charts)                                                         |
| Barcode scanning     | ZXing Android Embedded 4.3.0 (`zxing-android-embedded`)                                          |
| Maps & location      | Google Play Services Maps 18.1.0 + Location 21.0.1 + FusedLocationProviderClient                 |
| Voice search         | Android `RecognizerIntent` (system speech recognizer)                                            |
| Pull-to-refresh      | AndroidX SwipeRefreshLayout 1.1.0                                                                |
| Build                | Gradle + Android Gradle Plugin + Secrets Gradle Plugin (for Maps API key)                        |
| View binding         | Enabled (`viewBinding true`)                                                                     |

---

## 📁 Project Structure

```
Online-Shopping-App/
├── README.md
├── barcode test/                       # Standalone barcode scanner test harness
└── OnlineShopping/                     # Main Android project
    ├── settings.gradle
    ├── app/
    │   ├── build.gradle                # Dependencies + SDK config
    │   └── src/main/
    │       ├── AndroidManifest.xml     # Permissions: CAMERA, INTERNET, LOCATION
    │       ├── java/com/example/onlineshopping/
    │       │   ├── Splash_Online_Shopping.java   # Splash screen → Login
    │       │   ├── Category.java                 # Category interface
    │       │   ├── CustomCategory.java           # Category impl (used by Factory)
    │       │   ├── CategoryFactory.java          # Factory pattern entry point
    │       │   ├── AdaptersAndModels/
    │       │   │   ├── itemModel.java
    │       │   │   ├── itemAdapter.java
    │       │   │   ├── itemcartAdapter.java
    │       │   │   ├── CategoryModel.java
    │       │   │   ├── CategoryAdapter.java
    │       │   │   ├── FeedbackModel.java
    │       │   │   └── FeedbackAdapter.java
    │       │   ├── DateBaseSql/
    │       │   │   ├── Login_Register_DBHelper.java   # User/auth DB (117 lines)
    │       │   │   └── CategoryItemsDataBase.java     # Catalog/cart/reports DB (403 lines)
    │       │   ├── UserFunctions/
    │       │   │   ├── Login.java                     # Login screen (admin: admin/admin)
    │       │   │   ├── SignUp.java
    │       │   │   ├── passwordrecovery.java
    │       │   │   ├── change_password.java
    │       │   │   ├── Session.java                   # Singleton — holds logged-in user
    │       │   │   ├── Categories_Activity.java       # Category list (user side)
    │       │   │   ├── ItemsOfCategories.java         # Items in selected category
    │       │   │   ├── Showitem.java                  # Item detail + add to cart
    │       │   │   ├── ShowItemFromCart.java
    │       │   │   ├── My_Cart.java                   # Cart with total price
    │       │   │   ├── GoogleMap.java                 # Maps w/ FusedLocationProvider
    │       │   │   ├── MapsActivity.java
    │       │   │   ├── MyLocationListener.java
    │       │   │   └── CaptureAct.java                # ZXing barcode scanner host
    │       │   └── AdminFunctions/
    │       │       ├── Admin.java                     # Admin home w/ nav drawer
    │       │       ├── AdminCategory.java             # Manage categories
    │       │       ├── AdminItems.java                # Add items
    │       │       ├── Admin_item_update.java         # Update items
    │       │       ├── Admin_delete_item.java         # Delete items
    │       │       ├── admin_deleteuser_test.java     # Delete users
    │       │       ├── Admin_Reports.java             # Date/time sales reports
    │       │       ├── Admin_Feedbacks.java           # View all feedback
    │       │       ├── graph_sells.java               # Charts hub
    │       │       ├── piechart.java                  # Best-sellers pie chart
    │       │       └── barchart.java                  # Best-sellers bar chart
    │       └── res/
    │           ├── layout/                # 31 XML layouts (activities + list items)
    │           ├── drawable-v24/          # Category backgrounds + product images
    │           ├── menu/                  # nav_drawer_menu.xml + nav_drawer_menu_user.xml
    │           ├── values/                # colors, strings, themes
    │           ├── raw/                   # Sound effects (button click)
    │           └── xml/                   # Backup rules
    └── ...
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Arctic Fox or newer)
- **JDK 8** (bundled with Android Studio)
- **Android device or emulator** running API 21+ (5.0 Lollipop or newer)
- **Google Maps API key** — required only for the Maps activity; the rest of the app works without it

### 1. Clone & open

```bash
git clone https://github.com/AhmedSamir899/Online-Shopping-App.git
```

Open Android Studio → `File → Open` → select the `OnlineShopping` folder. Gradle sync will download all dependencies automatically.

### 2. Configure Maps API key (optional)

If you want the Google Maps activity to work:

1. Follow [Google's guide](https://developers.google.com/maps/documentation/android-sdk/get-api-key) to obtain a Maps API key.
2. In `OnlineShopping/local.properties`, add:
   ```
   MAPS_API_KEY=AIza...your_key_here
   ```
3. The Secrets Gradle Plugin will inject it into the `AndroidManifest.xml` automatically.

> ⚠️ The committed `AndroidManifest.xml` contains a placeholder API key. Replace it before publishing. Remove it from version control if you intend to keep the repo public.

### 3. Run

- Connect an Android device (enable USB debugging) or start an emulator.
- Click ▶️ Run in Android Studio.
- The splash screen launches → tap **Start** → login screen appears.
- **Admin login:** username `admin`, password `admin`.
- **User login:** sign up first via the Sign-up screen, then log in.

---

## 🧪 How to use

### User flow
1. Sign up → log in
2. Browse categories (Books, Electronics, Clothes, Games, Music)
3. Tap a category → see its items
4. Search by name, voice (🎤), or barcode (📷)
5. Tap an item → view details → set quantity → **Add to cart**
6. Open **My Cart** → verify total → checkout
7. Submit feedback on purchased items
8. View your location on Google Maps (delivery scenario)

### Admin flow
1. Log in as `admin` / `admin`
2. Use the navigation drawer to:
   - Add / update / delete categories
   - Add / update / delete items
   - Delete users
   - Filter sales reports by date and time range
   - Read all customer feedback
   - View pie chart and bar chart of best-sellers

---

## 📦 Seed data

On first launch, `ItemsOfCategories.onCreate()` pre-populates the database with **~30 sample items** across 5 categories (Books, Electronics, Clothes, Games, Musical Instruments) so the app is immediately usable. Each item has a name, description, price, drawable resource, stock count, category, and barcode — ready to be scanned via the in-app camera.

---

## 📜 License

This project is licensed under the **MIT License** — feel free to learn from it, fork it, and adapt it.

Product images and category backgrounds in `res/drawable-v24/` are the property of their respective copyright holders and are not covered by the MIT license.

---

## 👤 Author

**Ahmed Samir**
- GitHub: [@AhmedSamir899](https://github.com/AhmedSamir899)
- LinkedIn: [ahmed-samir-b9503a233](https://www.linkedin.com/in/ahmed-samir-b9503a233)
- Email: AhmedSamir8456@gmail.com

---

## 🙏 Acknowledgements

- Built as part of coursework at **Ain Shams University**, Faculty of Computer & Information Science.
- Third-party libraries: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart), [ZXing Android Embedded](https://github.com/journeyapps/zxing-android-embedded), Google Play Services Maps & Location.
