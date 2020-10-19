"# marlab_anoop" 

1. Clone the project form git repository
2. Build using maven "mvn clean install"
3. Run the application "java -jar <jar location>/marlap-1.0.0.jar
4. Running port number : 9017
5. Ensure the application is running by accessing the below service
   http://localhost:9017/app/live  (get method)
6. Service Details :

  1. http://localhost:9017/marlap/api/v1/security/question
     Request Body: (as a payload) Method : Post
              
              {
    "question": "Hey Service, can you provide me a question with numbers to add?" ,
    "answer": "",
    "createdOn": "2020-10-19T08:40:35.652+00:00",
    "uniqueId": "fa5419da-0e96-4919-a709-4beb70638741"
}
     Response : 
             {
    "question": "Here you go, solve the question: numbers 3,4,3",
    "answer": "",
    "createdOn": "2020-10-19T15:26:14.218+00:00",
    "uniqueId": "a0b30314-383f-4ad4-b682-14b7beee707a"
}
  2. http://localhost:9017/marlap/api/v1/security/answer
     Request Body: (as a payload) Method : Post
                   {
    "question": "Here you go, solve the question: numbers 11,13,51",
    "answer": "",
    "createdOn": "2020-10-19T16:40:34.834+00:00",
    "uniqueId": "3e1a66de-ccfd-438a-b457-ddd226b91788"
}
     Response : 
              {
    "question": "Here you go, solve the question: numbers 11,13,51",
    "answer": "Great! The orginal question wasHere you go, solve the question: numbers 11,13,51 and the answer is 75",
    "createdOn": "2020-10-19T16:40:34.834+00:00",
    "uniqueId": "3e1a66de-ccfd-438a-b457-ddd226b91788"
}
                       
