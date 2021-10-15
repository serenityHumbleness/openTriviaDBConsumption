# openTriviaDBConsumption
Consuming Open Trivia DB

How to build:
Check the JSON response that you get from https://opentdb.com/api.php?amount=5&type=multiple.
The JSON structure that we need to give to the frontend for a GET request:
{
"questionId": UUID,
"question": String,
"answerChoices": String []
}
We can achieve this by serializing the JSON response we retrieved into Java Object.
Once we have done this, it becomes easier to create Java Object with only UUID, String and List.
Use Postman as client for the Get-Request and you will get the response above.
The JSON structure we need to give frontend should only be one String, namely the correct answer { "answer": String }
Since we have identified each question with a { "questionId": UUID } the PostMapping value becomes "/questions/{ questionId }/answer".
Use Postman as client for the Post-Request and you will get this response.
The application is run on port 2424.
