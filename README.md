## Project Overview
LinkedHU_CENG is a student portal designed to facilitate course management, surveys, and user interactions. The project follows a structured **Model-View-Controller (MVC)** architecture and employs various design principles to ensure maintainability, scalability, and user-friendliness.

## Features
- **User Registration & Authentication**: Users must register to access the platform.
- **Course Management**: Add, drop, and delete courses.
- **Survey Creation**: Users can create and manage surveys.
- **User Profile Management**: View and edit account details.
- **Post Publishing**: Users can publish posts within the system.

## Technologies Used
- **Backend**: Java Spring Framework
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Development Tools**: IntelliJ, MySQL Workbench, GitHub, ARC for testing

## Installation
### Prerequisites
Ensure you have the following installed on your system:
- Java 8+
- MySQL Database
- Node.js (if frontend development is required)
- IntelliJ IDEA (recommended for Java development)
- Git

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/LinkedHU_CENG.git
   ```
2. Navigate to the project directory:
   ```sh
   cd LinkedHU_CENG
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/linkedhu_ceng
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
4. Build and run the backend:
   ```sh
   mvn spring-boot:run
   ```
5. Open the frontend files in a browser or deploy using a web server.

## Usage
- Access the system via `http://localhost:8080`.
- Register an account and log in.
- Navigate through courses, surveys, and user profiles.
- Manage your account and interact with other users.

## System Design
### Architecture
The system follows the **MVC (Model-View-Controller) pattern**:
- **Model**: Defines database entities and logic.
- **View**: Handles the frontend UI and user interactions.
- **Controller**: Manages API endpoints and business logic.

### Key Components
- **Class Diagram**: Defines the relationships between major classes.
- **State Diagram**: Represents the different states of a user within the system.
- **Sequence Diagrams**:
  - Course Management
  - Survey Creation
  - Post Publishing

## Database
The system utilizes **MySQL** as its database. The key tables include:
- `users`: Stores user credentials and profiles.
- `courses`: Stores course details and enrollment records.
- `surveys`: Stores user-created surveys and responses.
- `posts`: Stores published posts.
