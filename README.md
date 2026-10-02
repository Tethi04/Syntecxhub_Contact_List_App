<div align="center">

  # 🌸 Pastel Glassmorphic Contact List App 🌸
  
  *An aesthetically pleasing, soft pastel glassmorphic contact management application built for Web and Native Android.*

  [![Live Demo](https://img.shields.io/badge/Live-Demo_Link-cyan?style=for-the-badge&logo=github)](https://tethi04.github.io/Syntecxhub_Contact_List_App/)
  [![GitHub stars](https://img.shields.io/github/stars/Tethi04/Syntecxhub_Contact_List_App?style=for-the-badge&color=purple)](https://github.com/Tethi04/Syntecxhub_Contact_List_App)
  [![License](https://img.shields.io/badge/License-MIT-pink?style=for-the-badge)](LICENSE)

  <br />

  <!-- App Screenshot Preview -->
  <img width="100%" alt="App Preview" src="YOUR_SCREENSHOT_LINK_HERE" />

</div>

---

## 📖 Table of Contents
- [✨ Overview](#-overview)
- [🎨 Design Concept](#-design-concept)
- [⚡ Key Features](#-key-features)
- [📂 Comprehensive Project Structure](#-comprehensive-project-structure)
- [🌐 Live Demo & Web Deployment](#-live-demo--web-deployment)
- [🛠️ Detailed Step-by-Step Installation Guide](#️-detailed-step-by-step-installation-guide)
  - [1. Web Edition Setup](#1-web-edition-setup)
  - [2. Android Native Setup](#2-android-native-setup)
- [📱 How to Use & Features](#-how-to-use--features)
- [💻 Tech Stack](#-tech-stack)
- [👩‍💻 Author](#-author)

---

## ✨ Overview

**Pastel Glassmorphic Contact List App** is a fully functional contact management application built as part of the **Syntecxhub Virtual Internship (Android Development - Week 2 Task)**. 

The application is engineered with a dual-platform approach:
1. **Native Android Application:** Developed using Kotlin, Material Design Components 3, ViewBinding, and `RecyclerView`.
2. **Web Edition (GitHub Pages):** Built using Vanilla HTML5, CSS3 Glassmorphism, and ES6 JavaScript, replicating the exact visual feel and functionality of the Android app directly in any desktop or mobile browser.

---

## 🎨 Design Concept

The UI visual architecture is centered around modern **Glassmorphism**:
- **Frosted Glass Containers:** High-translucency cards utilizing `backdrop-filter: blur()` on Web and custom semi-transparent XML shape drawables with crisp white borders on Android.
- **Pastel Gradient Background:** A soothing multi-layered mesh background shifting between Soft Cyan (`#A5F3FC`), Pastel Purple (`#C084FC`), and Light Pink (`#F472B6`).
- **Dynamic Colored Avatars:** Initial-based circular avatars with pastel color fills generated dynamically per contact.
- **Floating 3D Ambient Orbs:** Soft blurred background spheres creating depth refraction and realistic lighting behind translucent containers.

---

## ⚡ Key Features

- 🌸 **Pre-loaded Sample Contacts:** Comes pre-populated with default contacts for immediate testing.
- ➕ **Add New Contacts:** Interactive glassmorphic modal dialog allowing users to save new contacts with custom names, phone numbers, and email addresses.
- 🗑️ **Delete Contact Functionality:** Quick-delete action directly from contact cards as well as a full deletion button from the Contact Detail screen.
- 🔍 **Real-Time Search & Filtering:** Instant filter response as you type in the search bar by contact name or phone number.
- 📱 **Runtime Contact Permissions:** Syncs and reads existing contacts from your Android device using `READ_CONTACTS` runtime permission flow.
- 📞 **Direct Action Integration:** One-tap action buttons to trigger native Phone Dialing (`tel:`) and Messaging (`sms:`) apps.
- ⚡ **Fast Scrolling & Responsive:** Smooth `RecyclerView` performance on mobile devices and responsive web layout.

---

## 📂 Comprehensive Project Structure

Here is the complete project hierarchy along with inline file role descriptions:

```text
Syntecxhub_Contact_List_App/
│
├── app/                                  # Android Native Application Module
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── syntexhub/
│   │       │           └── contactlist/
│   │       │               ├── adapter/
│   │       │               │   └── ContactAdapter.kt      # RecyclerView adapter handling list rendering & filtering
│   │       │               ├── model/
│   │       │               │   └── Contact.kt             # Data class model representing contact entities
│   │       │               ├── ContactDetailActivity.kt   # UI controller for contact details screen & delete action
│   │       │               └── MainActivity.kt            # Main activity controlling list, search, add, & permissions
│   │       │
│   │       ├── res/                      # Android UI Resources
│   │       │   ├── drawable/             # Custom glass drawables & shapes
│   │       │   │   ├── bg_avatar_circle.xml    # Circular avatar shape drawable
│   │       │   │   ├── bg_glass_card.xml       # Translucent frosted glass card background
│   │       │   │   ├── bg_glass_input.xml      # Translucent text input field background
│   │       │   │   ├── bg_glass_button.xml     # Soft gradient glass button background
│   │       │   │   ├── bg_pastel_gradient.xml  # Linear pastel mesh background gradient
│   │       │   │   ├── bg_search_bar.xml       # Rounded translucent search container
│   │       │   │   └── ic_search.xml           # Vector icon resource for search
│   │       │   │
│   │       │   ├── layout/
│   │       │   │   ├── activity_contact_detail.xml # Detailed contact info screen XML layout
│   │       │   │   ├── activity_main.xml           # Main contact list screen XML layout
│   │       │   │   ├── dialog_add_contact.xml      # Modal dialog layout for saving new contacts
│   │       │   │   └── item_contact.xml            # Individual contact row card XML layout
│   │       │   │
│   │       │   └── values/
│   │       │       ├── colors.xml        # Color hex design tokens
│   │       │       ├── strings.xml       # String localization resources
│   │       │       └── styles.xml        # Theme and glassmorphic style definitions
│   │       │
│   │       └── AndroidManifest.xml       # Application components & READ_CONTACTS permission declaration
│   │
│   └── build.gradle                      # App-level build & dependency configuration
│
├── docs/                                 # Web Edition for GitHub Pages Deployment
│   ├── index.html                        # Web layout structure matching native Android app
│   ├── style.css                         # CSS Glassmorphic design, gradients, & responsive layout
│   └── script.js                         # Web interactivity, LocalStorage state, search, & modals
│
├── .gitignore                            # Git ignore rule file
├── README.md                             # Comprehensive project documentation
├── build.gradle                          # Top-level project build configuration
└── settings.gradle                       # Gradle project settings & repository definitions
```
## 🌐 Live Demo & Web Deployment

You can test the Web Edition live directly from your browser:  
🔗 **[Launch Pastel Glassmorphic Contact List App](https://tethi04.github.io/Syntecxhub_Contact_List_App/)**

---

## 🛠️ Detailed Step-by-Step Installation Guide

### 1. Web Edition Setup
*No local compiler required.*

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Tethi04/Syntecxhub_Contact_List_App.git
   ```
2. **Open the project folder:**
   ```bash
   cd Syntecxhub_Contact_List_App
   ```
3. **Run Web Edition:**  
   Navigate to the `docs/` directory and open `index.html` in any web browser.

---

### 2. Android Native Setup
*Requirements: Android Studio (Ladybug / Jellyfish or newer) & JDK 17+.*

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Tethi04/Syntecxhub_Contact_List_App.git
   ```
2. **Open in Android Studio:**
   - Launch Android Studio.
   - Click **Open** and select the cloned `Syntecxhub_Contact_List_App` root directory.
3. **Gradle Sync & Build:**  
   Allow Gradle to sync project dependencies automatically.
4. **Run on Emulator / Device:**  
   - Select your Android Virtual Device (AVD) or connect a physical Android device via USB debugging.
   - Click **Run (▶)** or press `Shift + F10`.

---

## 📱 How to Use & Features

| Feature | Trigger / UI Element | Description |
| :--- | :--- | :--- |
| **Search Contacts** | Top Search Bar | Type any name or phone number to filter contacts in real time. |
| **View Details** | Tap Contact Card | Opens the Detail View showing full name, phone, email, and avatar. |
| **Add Contact** | Floating `+` FAB Button | Opens a glassmorphic dialog to enter name, phone, and email to save a new contact. |
| **Delete Contact** | Red Trash Icon / Delete Button | Removes the selected contact immediately from the list. |
| **Call Contact** | "Call" Button in Detail View | Triggers native phone dialer with pre-filled phone number. |
| **Message Contact** | "Message" Button in Detail View | Opens native SMS app with pre-filled recipient number. |

---

## 💻 Tech Stack

- **Android Native App:** Kotlin, ViewBinding, Material Design 3, `RecyclerView`, Custom XML Vector Drawables, Android Contacts Provider API.
- **Web Application:** HTML5, CSS3 (Custom Backdrop Filters, Flexbox, CSS Grid), ES6+ JavaScript, `LocalStorage` API.
- **Design Architecture:** Glassmorphism, Pastel Visual Tokens.

---

## 👩‍💻 Author

**Tethi Biswas**  
- GitHub: [@Tethi04](https://github.com/Tethi04)  

---

<div align="center">
  <i>Crafted with 💖, attention to detail, and modern design principles for Syntecxhub Internship.</i>
</div>

