 Todo Summary Assistant

A full-stack app that integrates with Slack and OpenAI to summarize your todo tasks automatically.

---
Project Structure

todo-summary-assistant/
├── backend/
│ ├── src/
│ ├── .env.example
│ └── (Spring Boot backend code)
├── frontend/
│ ├── src/
│ ├── public/
│ ├── .env.example
│ └── (React frontend code)
├── .gitignore
├── README.md

yaml
Copy
Edit

---

 Setup Instructions

1. Clone the repository

```bash
git clone https://github.com/your-username/todo-summary-assistant.git
cd todo-summary-assistant
2. Backend Setup
Navigate to the backend folder:

bash
Copy
Edit
cd backend
Create a .env file based on .env.example

Run the Spring Boot application (e.g., using your IDE or ./mvnw spring-boot:run)

3. Frontend Setup
Navigate to the frontend folder:

bash
Copy
Edit
cd frontend
Install dependencies:

bash
Copy
Edit
npm install
Create a .env file based on .env.example

Start the frontend:

bash
Copy
Edit
npm start
🧠 LLM and Slack Setup
Slack Bot
Create a Slack App at https://api.slack.com/apps

Enable necessary scopes (chat:write, commands, etc.)

Install the bot to your workspace

Copy your bot token into the backend .env

OpenAI Integration
Create an API key from https://platform.openai.com

Add it to your backend .env as OPENAI_API_KEY

Architecture Decisions
Backend: Spring Boot with MySQL

Frontend: React with Axios for API calls

LLM: OpenAI API

Slack Integration: Incoming Webhooks + Bot Tokens