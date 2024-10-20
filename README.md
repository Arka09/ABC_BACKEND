# ABC_BACKEND

Approach taken : created a microservice 

Appropriate comments have been added for better understanding

For the test cases DemoApllicationTests.java is present where every test case is tested and is passed

Apart from that QA testing is done where the following steps are followed
i. DemoApplication.java file is opened and is run
ii. Using ThunderClient I've used POST request with the following inputs
    --> http://localhost:8080/api/classes
    --> {
      "name": "Yoga",
      "startDate": "2024-01-01",
      "endDate": "2024-01-10",
      "capacity": 15,
      "totalClasses":10
    }
  --> http://localhost:8080/api/bookings
  --> {
    "name": "Arkadeep",
    "date": "2024-01-10"
  }

where both the cases is passed successfully and class created succesfully and bookings created message are shown
![image](https://github.com/user-attachments/assets/5cdaabbe-ea4c-4237-9e9c-6d992da6da48)

![image](https://github.com/user-attachments/assets/9eddd376-d6cf-4ab9-a9af-878d15179fa9)

