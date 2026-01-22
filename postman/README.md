# Quiz API Postman Collection

This directory contains Postman files for testing the Quiz Management System backend API.

## Files

- `QuizAPI.postman_collection.json` - Complete collection with all API endpoints
- `QuizAPI.Local.postman_environment.json` - Environment variables for local development

## Setup Instructions

1. **Import the Collection:**
   - Open Postman
   - Click "Import" button
   - Select "File" tab
   - Choose `QuizAPI.postman_collection.json`

2. **Import the Environment:**
   - Click "Import" button again
   - Select "File" tab
   - Choose `QuizAPI.Local.postman_environment.json`
   - Select the "Quiz API - Local" environment from the environment dropdown

3. **Start Testing:**
   - Make sure your backend is running on `http://localhost:8080`
   - Use the requests in the collection to test the API

## API Endpoints

### Admin Endpoints
- **GET** `/api/quiz/admin/questions` - Get all questions
- **POST** `/api/quiz/admin/questions` - Add new question
- **DELETE** `/api/quiz/admin/questions/{id}` - Delete question by ID

### User Endpoints
- **GET** `/api/quiz/questions` - Get questions for quiz
- **POST** `/api/quiz/submit` - Submit quiz answers

## Sample Data

### Adding a Question (POST /api/quiz/admin/questions)
```json
{
  "questionText": "What is the capital of Japan?",
  "option1": "Seoul",
  "option2": "Tokyo",
  "option3": "Beijing",
  "option4": "Bangkok",
  "correctOption": 2
}
```

### Submitting Quiz Answers (POST /api/quiz/submit)
```json
{
  "answers": [
    {
      "questionId": 1,
      "selectedOption": 2
    },
    {
      "questionId": 2,
      "selectedOption": 3
    }
  ]
}
```

## Response Examples

### Quiz Result
```json
{
  "score": 1,
  "totalQuestions": 2
}
```

## Notes

- `correctOption` and `selectedOption` use 1-based indexing (1 = option1, 2 = option2, etc.)
- Make sure PostgreSQL is running and the `quiz_system` database exists
- The backend runs on port 8080 by default