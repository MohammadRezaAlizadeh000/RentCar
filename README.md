# RentCar

The base Url is = https://www.mo-salim.com/sysproject/api/
Login Endpoint is = login
Get cars list endpoint is = cars
Booking a car = booking


In Response Model of cars we have no car image url to show, and this should change.
After you changed the Response model, should change two class 
  1. Modify 'CarResponseModel' to add new field
  2. Modify 'CarsListResponseMapper' in field 'carImge'
  3. Check the response error

I added two data souce as name of 'RemoteDataSource' and 'ModekDataSource'.
