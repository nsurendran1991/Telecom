# Telecom

API Background
We are a Telecom operator. In our database, we are starting to store phone numbers associated to customers (1 customer: N phone numbers) and we will provide interfaces to manage them.
We need to provide the below capabilities:

get all phone numbers
get all phone numbers of a single customer
activate a phone number

Steps to Run:
1. Clone the repository to your local machine.
     git clone https://github.com/nsurendran1991/Telecom.git

2. Go to Telecom folder and execute build the docker container 
  docker build -f src/main/resources/Dockerfile -t [tag-name] .    
  
3. Run the container using below command
  docker run -p 4000:4000 [tag-name]
  
4. Connect to the rest end points by post man or soap UI
  
  http://localhost:4000/phoneNumbers
     
  Edpoints:
     
  /customer/{customerName}
     
  customer/{customerName}/phoneNumbers
