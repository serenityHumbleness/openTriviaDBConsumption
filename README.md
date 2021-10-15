# openTriviaDBConsumption
Consuming Open Trivia DB

How to build:
1. Check the JSON response that you get from https://opentdb.com/api.php?amount=5&type=multiple.
2. The JSON structure that we need to give to the frontend for a GET request:
{
"questionId": UUID,
"question": String,
"answerChoices": String []
}
3. We can achieve this by serializing the JSON response we retrieved into Java Object.
4. Once we have done this, it becomes easier to create Java Object with only UUID, String and List.
5. Use Postman as client for the Get-Request and you will get the response above.
6. The JSON structure we need to give frontend should only be one String, namely the correct answer { "answer": String }
7. Since we have identified each question with a { "questionId": UUID } the PostMapping value becomes "/questions/{ questionId }/answer".
8. Use Postman as client for the Post-Request and you will get this response.
9. The application is run on port 2424.
